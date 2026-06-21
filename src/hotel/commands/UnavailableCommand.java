package hotel.commands;

import hotel.HotelContext;
import hotel.Reservation;
import hotel.Room;

import java.time.LocalDate;

public class UnavailableCommand implements Command {

    @Override
    public String getName() { return "unavailable"; }

    @Override
    public String getUsage() { return "unavailable <room> <from> <to> <note> обявява стая за недостъпна (ремонт)"; }

    @Override
    public void execute(String[] tokens, HotelContext context) throws Exception {
        context.requireFile();

        if (tokens.length < 5) {
            System.out.println("Употреба: " + getUsage());
            return;
        }

        int roomNum = Integer.parseInt(tokens[1]);
        LocalDate from = LocalDate.parse(tokens[2]);
        LocalDate to = LocalDate.parse(tokens[3]);

        if (!to.isAfter(from)) {
            System.out.println("Грешка: Крайната дата трябва да е след началната.");
            return;
        }

        StringBuilder noteBuilder = new StringBuilder();
        for (int i = 4; i < tokens.length; i++) {
            noteBuilder.append(tokens[i]).append(" ");
        }
        String note = noteBuilder.toString().trim();

        Room room = context.findRoom(roomNum);
        if (room == null) {
            System.out.println("Стая " + roomNum + " не съществува.");
            return;
        }

        if (!room.isAvailableForPeriod(from, to)) {
            System.out.println("Грешка: Стаята вече има резервации в този период и не може да се затвори за ремонт.");
            return;
        }

        // Създаваме обект Резервация с 0 гости и флаг isUnavailable = true
        room.addReservation(new Reservation(from, to, note, 0, true));
        System.out.println("Стая " + roomNum + " беше маркирана като недостъпна за периода.");
    }
}
