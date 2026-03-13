public class Room{
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

    // setters will be needed for the checkin function

    public void setRoomNumber(short roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setNumberOfGuests(short numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public void setNumberOfBeds(short numberOfBeds) { // this will be fixed to each room, so maybe no need for that
        this.numberOfBeds = numberOfBeds;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void checkin(){
        System.out.println("Enter the required data: ");
        System.out.printf("Date from: "); // so that the input is on the same line
        setDateFrom(dateFrom);
        System.out.println();
        System.out.printf("Date to: ");
        setDateTo(dateTo);
        System.out.println();
        System.out.printf("Add a note stating your family name: ");
        setNote(note);
        System.out.println();
        System.out.printf("Would you like to specifically state the number of guests are visiting? ");
        // If yes - state how many, if no - number of guests = number of beds
            System.out.printf("State how many guests are visiting: ");
            setNumberOfGuests(numberOfGuests);
        System.out.println();
        System.out.println("Here is a table of the rooms we can offer you: "); // here a table with a structure: "roomNumber-numberOfBeds" should appear
        System.out.println();
        System.out.println("Your choice is room number: ");
        setRoomNumber(roomNumber);
        System.out.println("Checkin " + getRoomNumber() + getDateFrom() + getDateTo() + getNote() + getNumberOfGuests());
        // write all of that into a file
    }

    public void availability() {
        // prints out a list of the rooms, available on a certain date (if date is not mentioned, today's date is the default)
    }

    public void checkout() {
        // 'checkout' + room; makes the room available
    }

    public void report() {
        // date from + date to; prints out a list of all the rooms, used in that period + the number of days each room has been used
    }

    public void find() {
        // find a room with needed number of beds from a date to a date; when multiple rooms are available, the smaller number of beds - the better
    }

    public void findImportant() {
        // when no room is available for an important guest, move around guests from maximum 2 rooms in order to make a room free for the important guest
    }

    public void unavailable() {
        // 'unavailable' + room number + date from + date to + note; no guests in the room and noone can stay there in the time period
    }

    // check out the 3 additional comments at the bottom of the pdf
}
