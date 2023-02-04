package ui;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final List<String> listOfCommands = Arrays.asList("Add Course <name>", "Select Course <name>", "Quit");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Commands:");
            for (int i = 0; i < listOfCommands.size(); i++) {
                System.out.println(i + 1 + ". " + listOfCommands.get(i));
            }
            System.out.println("Please input your command (number or full string command): ");
            String command = sc.nextLine();
            switch (command) {
                case "quit":
                    run = false;
                    break;
            }

        }
    }
}
