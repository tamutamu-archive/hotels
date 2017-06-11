package com.mnazarenka.dao;

import com.mnazarenka.dao.entity.Adress;
import com.mnazarenka.dao.entity.Hotel;
import com.mnazarenka.dao.entity.Role;
import com.mnazarenka.util.TestDataImporter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertThat;

public class HotelDaoTest {
    private static SessionFactory sessionFactory;

    @BeforeClass
    public static void init() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Before
    public void initDb() {
        TestDataImporter.getInstance().importTestData(sessionFactory);
    }

    @Test
    public void findAllHotelsTest() {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();

            List<Hotel> hotels = session.createQuery("select h from Hotel h", Hotel.class)
                    .getResultList();

            List<String> hotelNames = hotels.stream().map(Hotel::getName)
                    .collect(toList());
            List<String> hotelAdresses = hotels.stream().map(Hotel::getAdress).collect(toList())
                    .stream().map(Adress::getFullAdress).collect(toList());

            assertThat(hotels, hasSize(3));
            assertThat(hotelNames, containsInAnyOrder("FirstHotel", "SecondHotel", "ThirdHotel"));
            assertThat(hotelAdresses, containsInAnyOrder("FirstCity FirstStreet", "SecondCity SecondStreet", "ThirdCity ThirdStreet"));

            session.getTransaction().commit();
        }

    }

    @AfterClass
    public static void destroy() {
        sessionFactory.close();
    }
}