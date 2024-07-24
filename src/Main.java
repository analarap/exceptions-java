import model.entities.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            System.out.print("Room number: ");
            int number = sc.nextInt();
            sc.nextLine();

            System.out.print("Check-in date (DD/MM/YYYY): ");
            String checkInDateStr = sc.nextLine();
            LocalDate checkIn = LocalDate.parse(checkInDateStr, formatter);

            System.out.print("Check-out date (DD/MM/YYYY): ");
            String checkOutDateStr = sc.nextLine();
            LocalDate checkOut = LocalDate.parse(checkOutDateStr, formatter);

            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reservation: " + reservation);

            System.out.println();
            System.out.println("Enter data to update the reservation: ");
            System.out.print("Check-in date (DD/MM/YYYY): ");
            String checkInDateStrUpdated = sc.nextLine();
            checkIn = LocalDate.parse(checkInDateStrUpdated, formatter);

            System.out.print("Check-out date (DD/MM/YYYY): ");
            String checkOutDateStrUpdated = sc.nextLine();
            checkOut = LocalDate.parse(checkOutDateStrUpdated, formatter);

            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reservation: " + reservation);
        } catch (DateTimeException e){
            System.out.println("Invalid date format");
        } catch (DomainException e){
            System.out.println("Error in reservation: " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println("Unexpected error");
        }


        sc.close();
    }
}