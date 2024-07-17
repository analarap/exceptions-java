import model.entities.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Room number: ");
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Check-in date (DD/MM/YYYY): ");
        String checkInDateStr = sc.nextLine();
        LocalDate checkIn = LocalDate.parse(checkInDateStr, formatter);

        System.out.print("Check-out date (DD/MM/YYYY): ");
        String checkOutDateStr = sc.nextLine();
        LocalDate checkOut = LocalDate.parse(checkOutDateStr, formatter);

        if (!checkOut.isAfter(checkIn)){
            System.out.println("Error in reservation: Check-out date must be after check-in date");
        } else {
            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reservation: " + reservation.toString());

            System.out.println();
            System.out.println("Enter data to update the reservation: ");
            System.out.print("Check-in date (DD/MM/YYYY): ");
            String checkInDateStrUpdated = sc.nextLine();
            checkIn = LocalDate.parse(checkInDateStrUpdated, formatter);

            System.out.print("Check-out date (DD/MM/YYYY): ");
            String checkOutDateStrUpdated = sc.nextLine();
            checkOut = LocalDate.parse(checkOutDateStrUpdated, formatter);


            Date now = new Date();
            LocalDate nowLocalDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (checkIn.isBefore(nowLocalDate) || checkOut.isBefore(nowLocalDate)){
                System.out.println("Error in reservation: Reservation dates for update must be future dates");
            } else if (!checkOut.isAfter(checkIn)){
                System.out.println("Error in reservation: Check-out date must be after check-in date");
            } else {
                reservation.updateDates(checkIn, checkOut);
                System.out.println("Reservation: " + reservation);
            }
        }

        sc.close();
    }
}