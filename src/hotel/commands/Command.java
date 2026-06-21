package hotel.commands;

import hotel.HotelContext;

public interface Command {
    String getName();
    String getUsage();
    void execute(String[] tokens, HotelContext context) throws Exception;
}
