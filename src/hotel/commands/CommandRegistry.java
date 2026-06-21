package hotel.commands;

import java.util.ArrayList;
import java.util.List;

public class CommandRegistry {
    // Използваме чист ArrayList вместо HashMap (за да е по материала за ООП 1)
    private final List<Command> commands = new ArrayList<>();

    public void register(Command command) {
        commands.add(command);
    }

    public Command find(String name) {
        for (Command cmd : commands) {
            if (cmd.getName().equalsIgnoreCase(name)) {
                return cmd; // Връщаме намерения обект (Полиморфизъм)
            }
        }
        return null;
    }

    public List<Command> getAllCommands() {
        return commands;
    }
}
