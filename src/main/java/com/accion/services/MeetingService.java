package com.accion.services;


import com.accion.model.Booking;
import com.accion.model.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MeetingService {
    Page<Room> getAllRooms(int page, int size, String sortBy, String sortOrder) throws Exception;
    Room saveRooms(Room room)throws Exception;
    Page<Room> findByRoomPartialName(String name, int page, int size, String sortBy, String sortOrder) throws Exception;
    Room findOneRoom(String id);
    Room updateRoom(Room room)throws Exception;
    List<Room> nameValidation(String name, String elementType)throws Exception;
    Page<Booking> getAllBookings(int page, int size, String sortBy, String sortOrder) throws Exception;
    Booking saveBookings(Booking room)throws Exception;
    Page<Booking> findByBookingPartialName(String name, int page, int size, String sortBy, String sortOrder) throws Exception;
    Booking findOneBooking(String id);
    Booking updateBooking(Booking room)throws Exception;
    List<Room> nameValidationBooking(String name, String elementType)throws Exception;

}
