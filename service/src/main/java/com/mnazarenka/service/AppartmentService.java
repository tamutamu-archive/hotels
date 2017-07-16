package com.mnazarenka.service;

import com.mnazarenka.dao.entity.Appartment;
import com.mnazarenka.dao.entity.EconomAppartment;
import com.mnazarenka.dao.entity.LuxAppartment;
import com.mnazarenka.dao.entity.StandartAppartment;
import com.mnazarenka.service.common.BaseService;

import java.util.List;

public interface AppartmentService extends BaseService<Appartment> {

    <T extends Appartment> List<T> findAllAppartments(Class<T> clazz);

    void createAppartmentWithHotelId(Appartment appartment, long hotelId);

    void updateAppartmentWithHotelId(Appartment appartment, long hotelId);

    <T extends Appartment> List<T> getAppartmentsByRange(int pageNumber, int pageCounts, Class<T> clazz);

    <T extends Appartment> T findAppartment(long id, Class<T> clazz);

    Long getAppartmentsCount(Class<? extends Appartment> appartmentClass);
}
