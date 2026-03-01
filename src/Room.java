public class Room {
    private short roomNumber; // might need to be an int
    private short numberOfGuests;
    private short numberOfBeds;
    private String dateFrom; // not sure about the data type
    private String dateTo;
    private String note; // maybe StringBuilder will be better

    // numberOfGuests is optional, if no number is written,
    // it is set to the number of beds the room has (numberOfGuests = numberOfBeds)


    public Room(short roomNumber, short numberOfGuests, short numberOfBeds, String dateFrom, String dateTo, String note) {
        this.roomNumber = roomNumber;
        this.numberOfGuests = numberOfBeds; // by default, we set this to the number of beds
        this.numberOfBeds = numberOfBeds;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.note = note;
    }

    public short getRoomNumber() {
        return roomNumber;
    }

    public short getNumberOfGuests() {
        return numberOfGuests;
    }

    public short getNumberOfBeds() {
        return numberOfBeds;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getNote() {
        return note;
    }

    public void checkin(){

    }
}
