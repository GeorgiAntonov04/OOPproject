package hotel.commands;

import hotel.HotelContext;

public class HelpCommand implements Command {
    private final CommandRegistry registry;

    public HelpCommand(CommandRegistry registry) {
        this.registry = registry;
    }

    @Override
    public String getName() { return "help"; }

    @Override
    public String getUsage() { return "help                                  показва списъка с команди"; }

    @Override
    public void execute(String[] tokens, HotelContext context) {
        System.out.println("Документация на системата:");
        for (Command cmd : registry.getAllCommands()) {
            System.out.println("  " + cmd.getUsage());
        }
    }
}
