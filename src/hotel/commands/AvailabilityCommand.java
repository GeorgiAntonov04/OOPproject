package hotel.commands;

import hotel.HotelContext;
import hotel.Room;

import java.time.LocalDate;

public class AvailabilityCommand implements Command {

    @Override
    public String getName() { return "availability"; }

    @Override
    public String getUsage() { return "availability [date]                   показва списък със свободните стаи за дата"; }

    @Override
    public void execute(String[] tokens, HotelContext context) throws Exception {
        context.requireFile();

        LocalDate targetDate;
        if (tokens.length > 1) {
            targetDate = LocalDate.parse(tokens[1]);
        } else {
            targetDate = LocalDate.now();
        }

        // Проверяваме заетостта за период от точно 1 нощувка: от targetDate до следващия ден
        LocalDate nextDay = targetDate.plusDays(1);

        System.out.println("Свободни стаи за дата " + targetDate + ":");
        boolean foundAny = false;

        for (Room r : context.getRooms()) {
            if (r.isAvailableForPeriod(targetDate, nextDay)) {
                System.out.println("  Стая " + r.getNumber() + " (" + r.getBeds() + " легла)");
                foundAny = true;
            }
        }

        if (!foundAny) {
            System.out.println("  Няма нито една свободна стая за тази дата.");
        }
    }
}
