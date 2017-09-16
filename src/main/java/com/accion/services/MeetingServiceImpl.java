package com.accion.services;


import com.accion.dao.BookingRepository;
import com.accion.dao.RoomRepository;
import com.accion.model.Booking;
import com.accion.model.Room;
import com.accion.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("meetingService")
@Transactional(readOnly = false, propagation = Propagation.SUPPORTS)
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public Page<Room> getAllRooms(int page, int size, String sortBy, String sortOrder) throws Exception {
        CommonUtils commonUtils=new CommonUtils();
        PageRequest pageRequest=commonUtils.getPageDetails(page,size,sortBy,sortOrder);
        Page<Room> rooms = roomRepository.findAll(pageRequest);
        return rooms;
    }

    @Override
    public Room saveRooms(Room room) throws Exception {
        Room roomResult = roomRepository.save(room);
        return roomResult;
    }


    public Page<Room> findByRoomPartialName(String name,  int page, int size, String sortBy, String sortOrder) throws Exception {
        CommonUtils commonUtils=new CommonUtils();
        PageRequest pageRequest=commonUtils.getPageDetails(page,size,sortBy,sortOrder);
        Page<Room> rooms = roomRepository.findByNameIgnoreCaseContaining(name,pageRequest);
        return rooms;
    }

    @Override
    public Room findOneRoom(String id) {
        return roomRepository.findOne(id);
        }

    @Override
    public Room updateRoom(Room room) throws Exception {
        Room roomResult = roomRepository.save(room);
        return roomResult;
    }

    @Override
    public List<Room> nameValidation(String name, String elementType) throws Exception {
        return null;
    }

    @Override
    public Page<Booking> getAllBookings(int page, int size, String sortBy, String sortOrder) throws Exception {
        CommonUtils commonUtils=new CommonUtils();
        PageRequest pageRequest=commonUtils.getPageDetails(page,size,sortBy,sortOrder);
        Page<Booking> rooms = bookingRepository.findAll(pageRequest);
        return rooms;
    }

    @Override
    public Booking saveBookings(Booking room) throws Exception {
        return bookingRepository.save(room);}


    public Page<Booking> findByBookingPartialName(String name, int page, int size, String sortBy, String sortOrder) throws Exception {
        CommonUtils commonUtils=new CommonUtils();
        PageRequest pageRequest=commonUtils.getPageDetails(page,size,sortBy,sortOrder);
        Page<Booking> rooms = bookingRepository.findByNameIgnoreCaseContaining(pageRequest);
        return rooms;
    }

    @Override
    public Booking findOneBooking(String id) {
        return bookingRepository.findOne(id);
    }

    @Override
    public Booking updateBooking(Booking room) throws Exception {
     return bookingRepository.save(room);}

    @Override
    public List<Room> nameValidationBooking(String name, String elementType) throws Exception {
        return null;
    }
}
