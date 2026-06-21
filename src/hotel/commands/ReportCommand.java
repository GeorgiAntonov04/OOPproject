package hotel.commands;

import hotel.HotelContext;
import hotel.Reservation;
import hotel.Room;

import java.time.LocalDate;

public class ReportCommand implements Command {

    @Override
    public String getName() { return "report"; }

    @Override
    public String getUsage() { return "report <from> <to>                    справка за заетостта на хотела в период"; }

    @Override
    public void execute(String[] tokens, HotelContext context) throws Exception {
        context.requireFile();

        if (tokens.length < 3) {
            System.out.println("Употреба: " + getUsage());
            return;
        }

        LocalDate from = LocalDate.parse(tokens[1]);
        LocalDate to = LocalDate.parse(tokens[2]);

        if (!to.isAfter(from)) {
            System.out.println("Грешка: Крайната дата трябва да е след началната.");
            return;
        }

        System.out.println("=== Справка за използване на стаите от " + from + " до " + to + " ===");

        for (Room r : context.getRooms()) {
            System.out.print("Стая " + r.getNumber() + " (" + r.getBeds() + " легла): ");

            boolean hasBookings = false;
            for (Reservation res : r.getReservations()) {
                // Показваме само реални гости (не ремонти), чийто престой се засича с търсения прозорец
                if (!res.isUnavailable() && res.overlapsWith(from, to)) {
                    if (!hasBookings) System.out.println(); // минаваме на нов ред след заглавието на стаята

                    System.out.println("    -> От " + res.getFrom() + " до " + res.getTo()
                            + " | Гости: " + res.getGuests() + " | Бележка: " + res.getNote());
                    hasBookings = true;
                }
            }

            if (!hasBookings) {
                System.out.println("Няма резервации в този период.");
            }
        }
    }
}
