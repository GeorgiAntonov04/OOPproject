package hotel.commands;

import hotel.HotelContext;
import hotel.Reservation;
import hotel.Room;

import java.time.LocalDate;

public class CheckoutCommand implements Command {

    @Override
    public String getName() { return "checkout"; }

    @Override
    public String getUsage() { return "checkout <room>                       освобождава стаята (напускане на гостите)"; }

    @Override
    public void execute(String[] tokens, HotelContext context) throws Exception {
        context.requireFile();

        if (tokens.length < 2) {
            System.out.println("Употреба: " + getUsage());
            return;
        }

        int roomNum = Integer.parseInt(tokens[1]);
        Room room = context.findRoom(roomNum);

        if (room == null) {
            System.out.println("Стая " + roomNum + " не съществува.");
            return;
        }

        LocalDate today = LocalDate.now();
        Reservation activeReservation = null;

        for (Reservation res : room.getReservations()) {
            // Търсим резервация, която НЕ е ремонт и днешната дата се пада вътре в нейния период
            if (!res.isUnavailable() && !res.getFrom().isAfter(today) && res.getTo().isAfter(today)) {
                activeReservation = res;
                break;
            }
        }

        if (activeReservation != null) {
            room.getReservations().remove(activeReservation);
            System.out.println("Стая " + roomNum + " беше освободена успешно.");
        } else {
            System.out.println("В стая " + roomNum + " няма регистрирани гости към днешна дата (" + today + ").");
        }
    }
}
