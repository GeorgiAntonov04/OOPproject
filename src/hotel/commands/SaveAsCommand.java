package hotel.commands;

import hotel.HotelContext;
import hotel.Room;
import hotel.Reservation;

import java.io.PrintWriter;

public class SaveAsCommand implements Command {

    @Override
    public String getName() { return "saveas"; }

    @Override
    public String getUsage() { return "saveas <file>                         записва данните в нов посочен файл"; }

    @Override
    public void execute(String[] tokens, HotelContext context) throws Exception {
        context.requireFile();

        if (tokens.length < 2) {
            System.out.println("Употреба: " + getUsage());
            return;
        }

        String filename = tokens[1];

        try (PrintWriter writer = new PrintWriter(filename)) {
            for (Room r : context.getRooms()) {
                writer.println("ROOM " + r.getNumber() + " " + r.getBeds());

                for (Reservation res : r.getReservations()) {
                    if (res.isUnavailable()) {
                        writer.println("UNAVAILABLE " + r.getNumber() + " " + res.getFrom() + " " + res.getTo() + " " + res.getNote());
                    } else {
                        writer.println("CHECKIN " + r.getNumber() + " " + res.getFrom() + " " + res.getTo() + " " + res.getGuests() + " " + res.getNote());
                    }
                }
            }
            // Сменяме текущия работен файл да бъде новият
            context.setCurrentFile(filename);
            System.out.println("Файлът беше записан успешно като: " + filename);
        } catch (Exception e) {
            System.out.println("Грешка при запис: " + e.getMessage());
        }
    }
}
