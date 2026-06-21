package hotel.commands;

import hotel.HotelContext;
import hotel.Reservation;
import hotel.Room;
import java.time.LocalDate;

public class CheckinCommand implements Command {
    @Override
    public String getName() { return "checkin"; }

    @Override
    public String getUsage() {
        return "checkin <room> <from> <to> <note> [<guests>]";
    }

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

        int guests = -1;
        StringBuilder noteBuilder = new StringBuilder();

        // Проверка дали последният токен е число (брой гости)
        String lastToken = tokens[tokens.length - 1];
        if (lastToken.matches("\\d+")) {
            guests = Integer.parseInt(lastToken);
            for (int i = 4; i < tokens.length - 1; i++) {
                noteBuilder.append(tokens[i]).append(" ");
            }
        } else {
            for (int i = 4; i < tokens.length; i++) {
                noteBuilder.append(tokens[i]).append(" ");
            }
        }
        String note = noteBuilder.toString().trim();

        Room room = context.findRoom(roomNum);
        if (room == null) {
            System.out.println("Стая " + roomNum + " не съществува.");
            return;
        }

        if (!room.isAvailableForPeriod(from, to)) {
            System.out.println("Стая " + roomNum + " е заето в този период.");
            return;
        }

        int finalGuests = (guests == -1) ? room.getBeds() : guests;
        room.addReservation(new Reservation(from, to, note, finalGuests, false));
        System.out.println("Успешно настаняване в стая " + roomNum);
    }
}
