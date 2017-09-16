package com.accion.dao;


import com.accion.model.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookingRepository extends MongoRepository<Booking,String> {

    Page<Booking> findByNameIgnoreCaseContaining(Pageable pageRequest);

}
