package hotel.commands;

import hotel.HotelContext;
import hotel.Room;

import java.time.LocalDate;

public class FindCommand implements Command {

    @Override
    public String getName() { return "find"; }

    @Override
    public String getUsage() { return "find <beds> <from> <to>               намира най-подходящата свободна стая"; }

    @Override
    public void execute(String[] tokens, HotelContext context) throws Exception {
        context.requireFile();

        if (tokens.length < 4) {
            System.out.println("Употреба: " + getUsage());
            return;
        }

        int beds = Integer.parseInt(tokens[1]);
        LocalDate from = LocalDate.parse(tokens[2]);
        LocalDate to = LocalDate.parse(tokens[3]);

        if (!to.isAfter(from)) {
            System.out.println("Грешка: Крайната дата трябва да е след началната.");
            return;
        }

        Room bestRoom = null;

        for (Room r : context.getRooms()) {
            if (r.getBeds() >= beds && r.isAvailableForPeriod(from, to)) {
                // Ако това е първата намерена стая ИЛИ броят ѝ легла е по-малък от досегашния фаворит
                if (bestRoom == null || r.getBeds() < bestRoom.getBeds()) {
                    bestRoom = r;
                }
            }
        }

        if (bestRoom != null) {
            System.out.println("Намерена е подходяща свободна стая: Стая " + bestRoom.getNumber() + " (" + bestRoom.getBeds() + " легла)");
        } else {
            System.out.println("Няма свободна стая с поне " + beds + " легла за посочения период.");
        }
    }
}
