package hotel.commands;

import hotel.HotelContext;
import hotel.Room;
import hotel.Reservation;

import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

public class OpenCommand implements Command {

    @Override
    public String getName() {
        return "open";
    }

    @Override
    public String getUsage() {
        return "open <file>                           отваря файл с данни за хотела";
    }

    @Override
    public void execute(String[] tokens, HotelContext context) throws Exception {
        if (tokens.length < 2) {
            System.out.println("Употреба: " + getUsage());
            return;
        }

        String filename = tokens[1];
        // Нулираме старото състояние в паметта при отваряне на нов файл
        context.getRooms().clear();
        context.setCurrentFile(filename);

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Файлът не съществува. Ще бъде създаден нов автоматично при команда 'save'.");
            context.setFileLoaded(true);
            return;
        }

        // Класическо четене с конструкции за ресурс try-with-resources
        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                String type = parts[0];

                if (type.equals("ROOM")) {
                    int number = Integer.parseInt(parts[1]);
                    int beds = Integer.parseInt(parts[2]);
                    context.getRooms().add(new Room(number, beds));

                } else if (type.equals("CHECKIN")) {
                    int roomNum = Integer.parseInt(parts[1]);
                    LocalDate from = LocalDate.parse(parts[2]);
                    LocalDate to = LocalDate.parse(parts[3]);
                    int guests = Integer.parseInt(parts[4]);

                    // Сглобяваме бележката (всички оставащи думи до края на реда)
                    StringBuilder noteBuilder = new StringBuilder();
                    for (int i = 5; i < parts.length; i++) {
                        noteBuilder.append(parts[i]).append(" ");
                    }
                    String note = noteBuilder.toString().trim();

                    Room r = context.findRoom(roomNum);
                    if (r != null) {
                        r.addReservation(new Reservation(from, to, note, guests, false));
                    }

                } else if (type.equals("UNAVAILABLE")) {
                    int roomNum = Integer.parseInt(parts[1]);
                    LocalDate from = LocalDate.parse(parts[2]);
                    LocalDate to = LocalDate.parse(parts[3]);

                    StringBuilder noteBuilder = new StringBuilder();
                    for (int i = 4; i < parts.length; i++) {
                        noteBuilder.append(parts[i]).append(" ");
                    }
                    String note = noteBuilder.toString().trim();

                    Room r = context.findRoom(roomNum);
                    if (r != null) {
                        // Понеже е ремонт, гостите са 0, а последното поле е true (isUnavailable)
                        r.addReservation(new Reservation(from, to, note, 0, true));
                    }
                }
            }
            context.setFileLoaded(true);
            System.out.println("Успешно зареден файл: " + filename);
        } catch (Exception e) {
            System.out.println("Грешка при четене на файла: " + e.getMessage());
        }
    }
}
