package hotel.commands;

import hotel.HotelContext;
import hotel.Room;
import hotel.Reservation;

import java.io.PrintWriter;

public class SaveCommand implements Command {

    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getUsage() {
        return "save                                  записва промените в текущия файл";
    }

    @Override
    public void execute(String[] tokens, HotelContext context) throws Exception {
        context.requireFile();

        String filename = context.getCurrentFile();

        // Класически запис чрез PrintWriter
        try (PrintWriter writer = new PrintWriter(filename)) {
            // Обикновен вложен цикъл (без Streams)
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
            System.out.println("Промените бяха записани успешно във файл: " + filename);
        } catch (Exception e) {
            System.out.println("Грешка при запис на файла: " + e.getMessage());
        }
    }
}
