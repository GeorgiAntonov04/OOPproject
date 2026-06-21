package hotel.commands;

import hotel.HotelContext;

public class ExitCommand implements Command {
    @Override
    public String getName() { return "exit"; }

    @Override
    public String getUsage() { return "exit                                  изход от програмата"; }

    @Override
    public void execute(String[] tokens, HotelContext context) {
        System.out.println("Приятен ден!");
        context.stop();
    }
}
