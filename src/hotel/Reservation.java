package hotel;

import java.time.LocalDate;

public class Reservation {
    private LocalDate from;
    private LocalDate to;
    private String note;
    private int guests;
    private boolean isUnavailable; // true, ако това е маркер за ремонт/недостъпност

    public Reservation(LocalDate from, LocalDate to, String note, int guests, boolean isUnavailable) {
        this.from = from;
        this.to = to;
        this.note = note;
        this.guests = guests;
        this.isUnavailable = isUnavailable;
    }

    public LocalDate getFrom() { return from; }
    public LocalDate getTo() { return to; }
    public String getNote() { return note; }
    public int getGuests() { return guests; }
    public boolean isUnavailable() { return isUnavailable; }

    // Проверка дали тази резервация се засича по дати с друг търсен период
    public boolean overlapsWith(LocalDate checkFrom, LocalDate checkTo) {
        return !(to.isBefore(checkFrom) || to.isEqual(checkFrom) ||
                from.isAfter(checkTo) || from.isEqual(checkTo));
    }
}
