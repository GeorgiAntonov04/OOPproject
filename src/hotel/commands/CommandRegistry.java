package hotel.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    private final List<Command> commands = new ArrayList<>();

    public void register(Command command) {
        commands.add(command);
    }

    public Command find(String name) {
        for (Command cmd : commands) {
            if (cmd.getName().equalsIgnoreCase(name)) {
            }
        }
        return null;
    }

    public List<Command> getAllCommands() {
        return commands;
    }
}
