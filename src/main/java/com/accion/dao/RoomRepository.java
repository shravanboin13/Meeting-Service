package com.accion.dao;



import com.accion.model.Room;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoomRepository extends MongoRepository<Room,String> {
    Page<Room> findByNameIgnoreCaseContaining(String name, Pageable pageRequest);

}
