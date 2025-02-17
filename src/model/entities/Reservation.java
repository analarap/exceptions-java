package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Reservation {
    private Integer roomNumber;
    private LocalDate checkIn;
    private LocalDate checkOut;

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation(){
    }

    public Reservation(Integer roomNumber, LocalDate checkIn, LocalDate checkOut){
        if (!checkOut.isAfter(checkIn)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public long duration() {
        return checkOut.toEpochDay() - checkIn.toEpochDay();
    }

    public void updateDates(LocalDate checkIn, LocalDate checkOut){

        Date now = new Date();
        LocalDate nowLocalDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (checkIn.isBefore(nowLocalDate) || checkOut.isBefore(nowLocalDate)){
            throw new DomainException("Reservation dates for update must be future dates");
        }
        if (!checkOut.isAfter(checkIn)){
            throw new DomainException("Check-out date must be after check-in date");
        }
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString(){
        return "Room " + roomNumber + ", check-in: " + formatter.format(checkIn) + ", check-out: " + formatter.format(checkOut) + ", " + duration() + " nights";
    }

}

