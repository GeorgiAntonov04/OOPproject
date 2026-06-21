package hotel;

import java.util.ArrayList;
import java.util.List;

public class HotelContext {
    private List<Room> rooms = new ArrayList<>();
    private String currentFile = null;
    private boolean fileLoaded = false;
    private boolean running = true;

    public List<Room> getRooms() { return rooms; }
    public String getCurrentFile() { return currentFile; }
    public void setCurrentFile(String currentFile) { this.currentFile = currentFile; }
    public boolean isFileLoaded() { return fileLoaded; }
    public void setFileLoaded(boolean fileLoaded) { this.fileLoaded = fileLoaded; }

    public boolean isRunning() { return running; }
    public void stop() { this.running = false; }

    public void requireFile() throws Exception {
        if (!fileLoaded) {
            throw new Exception("Няма отворен файл! Използвайте 'open <file>' първо.");
        }
    }

    public Room findRoom(int number) {
        for (Room r : rooms) {
            if (r.getNumber() == number) return r;
        }
        return null;
    }
}
