package hotel;

import hotel.commands.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Хотелска Система");
        System.out.println("Напишете 'help' за списък с команди.");

        HotelContext context = new HotelContext();
        CommandRegistry registry = buildRegistry();
        Scanner scanner = new Scanner(System.in);

        while (context.isRunning()) {
            System.out.print("> ");
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            // Разделяне на думи по празни пространства
            String[] tokens = line.split("\\s+");

            Command command = registry.find(tokens[0]);

            if (command == null) {
                System.out.println("Непозната команда. Напишете 'help' за помощ.");
                continue;
            }

            try {
                command.execute(tokens, context);
            } catch (Exception e) {
                System.out.println("Грешка: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static CommandRegistry buildRegistry() {
        CommandRegistry registry = new CommandRegistry();

        // Файлови операции
        registry.register(new OpenCommand());
        registry.register(new CloseCommand());
        registry.register(new SaveCommand());
        registry.register(new SaveAsCommand());
        registry.register(new ExitCommand());

        // Бизнес операции
        registry.register(new CheckinCommand());
        registry.register(new CheckoutCommand());
        registry.register(new AvailabilityCommand());
        registry.register(new UnavailableCommand());
        registry.register(new ReportCommand());
        registry.register(new FindCommand());
        registry.register(new FindUrgentCommand());

        // Хелпът се закача последен
        registry.register(new HelpCommand(registry));

        return registry;
    }
}
