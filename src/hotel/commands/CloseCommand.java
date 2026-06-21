package hotel.commands;

import hotel.HotelContext;

public class CloseCommand implements Command {

    @Override
    public String getName() {
        return "close";
    }

    @Override
    public String getUsage() {
        return "close                                 затваря текущо отворения файл";
    }

    @Override
    public void execute(String[] tokens, HotelContext context) throws Exception {
        // Подсигурява, че не можем да затворим файл, преди изобщо да сме заредили такъв
        context.requireFile();

        context.getRooms().clear();
        context.setCurrentFile(null);
        context.setFileLoaded(false);
        System.out.println("Файлът беше затворен успешно.");
    }
}
