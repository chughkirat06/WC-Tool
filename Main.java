package WCTool;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        CommandHandler handler = new CommandHandler();
        Scanner sc = new Scanner(System.in);

        System.out.print(">> ");
        String command = sc.nextLine();
        sc.close();

        // Convert string to array
        String[] array = command.split(" ");

        // Check if the command is a standard input
        boolean stdin = Arrays.asList(array).contains("|");

        if (!stdin) {
            handleDirectCommand(array, handler);
        } else {
            handlePipedCommand(array, handler);
        }
    }

    public static void handleDirectCommand(String[] array, CommandHandler handler) {
        if (array[0].equals("ccwc")) {
            if (array.length == 3) {
                handler.invalidFlagMessage(array[1]);
                handler.processCommand(array[1], array[2]);
            } else if (array.length == 2) {
                handler.processCommand(array[1], array[1]);
            } else {
                System.out.println("Command not found.");
                return;
            }
        } else {
            System.out.println("Command '" + array[0] + "' not found, did you mean: \n command 'ccwc'");
            return;
        }
    }

    public static void handlePipedCommand(String[] array, CommandHandler handler) {
        if (array[0].equals("cat")) {
            if (!array[3].equals("ccwc")) {
                System.out.println("Command '" + array[3] + "' not found, did you mean: \n command 'ccwc'");
                return;
            }
            if (array.length == 5) {
                handler.invalidFlagMessage(array[4]);
                handler.processCommand(array[4], array[1]);
            } else if (array.length == 4) {
                handler.processCommand("", array[1]);
            } else {
                System.out.println("Command not found.");
                return;
            }
        } else {
            System.out.println("Command '" + array[0] + "' not found, did you mean: \n command 'cat'");
            return;
        }
    }
}