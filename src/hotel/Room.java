package hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Room {
    private int number;
    private int beds;
    private List<Reservation> reservations;

    public Room(int number, int beds) {
        this.number = number;
        this.beds = beds;
        this.reservations = new ArrayList<>();
    }

    public int getNumber() { return number; }
    public int getBeds() { return beds; }
    public List<Reservation> getReservations() { return reservations; }

    public void addReservation(Reservation r) {
        this.reservations.add(r);
    }

    // Класически обхождащ цикъл (заместител на сложните Streams)
    public boolean isAvailableForPeriod(LocalDate from, LocalDate to) {
        for (Reservation res : reservations) {
            if (res.overlapsWith(from, to)) {
                return false; // Стаята е заета
            }
        }
        return true; // Стаята е свободна
    }
}
