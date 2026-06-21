package hotel.commands;

import hotel.HotelContext;
import hotel.Reservation;
import hotel.Room;
import java.time.LocalDate;

public class FindUrgentCommand implements Command {
    @Override
    public String getName() { return "find!"; }

    @Override
    public String getUsage() {
        return "find! <beds> <from> <to>            спешно търсене с преместване";
    }

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


        for (Room r : context.getRooms()) {
            if (r.getBeds() >= beds && r.isAvailableForPeriod(from, to)) {
                System.out.println("Намерена свободна стая: Стая " + r.getNumber());
                return;
            }
        }

        System.out.println("Няма директно свободна стая. Търсене на опции за преместване...");


        for (Room targetRoom : context.getRooms()) {
            if (targetRoom.getBeds() < beds) continue;

            Reservation blocking = null;
            for (Reservation res : targetRoom.getReservations()) {
                if (!res.isUnavailable() && res.overlapsWith(from, to)) {
                    blocking = res;
                    break;
                }
            }
            if (blocking == null) continue;

            // Търсене на алтернативна стая за настоящите гости
            for (Room altRoom : context.getRooms()) {
                if (altRoom != targetRoom && altRoom.getBeds() >= blocking.getGuests() &&
                        altRoom.isAvailableForPeriod(blocking.getFrom(), blocking.getTo())) {

                    System.out.println("Предложение: Преместете гостите от Стая " + targetRoom.getNumber()
                            + " в Стая " + altRoom.getNumber() + ", след което използвайте Стая "
                            + targetRoom.getNumber() + " за новия гост.");
                    return;
                }
            }
        }
        System.out.println("Не е намерено решение с преместване.");
    }
}
