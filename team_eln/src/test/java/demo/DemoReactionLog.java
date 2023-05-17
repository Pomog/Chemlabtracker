package demo;

import java2.eln.core.domain.ReactionData;
import java2.eln.core.services.CreateConditionDataFromFile;
import java2.eln.core.services.CreateStructureFromFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DemoReactionLog {
    public static void main(String[] args) {
        boolean repeat = true;
        List<ReactionData> reactionsLog = new ArrayList<>();

        while (true) {
            System.out.println("Program menu:");
            System.out.println("1. Add reaction to list");
            System.out.println("2. Delete reaction from list");
            System.out.println("3. Show all reaction in the list");
            System.out.println("4. Exit");

            System.out.println("");

            System.out.println("Enter menu item number to execute:");
            Scanner scanner = new Scanner(System.in);
            int userChoice = Integer.parseInt(scanner.nextLine());

            switch (userChoice) {
                case 1: {
                    System.out.println("Enter reaction code: ");
                    String reactionCode = scanner.nextLine();
                    System.out.println("Enter reaction name: ");
                    String reactionName = scanner.nextLine();
                    System.out.println("Enter DataFile name for reaction: ");
                    String path = scanner.nextLine();

                    ReactionData reactionData = new ReactionData(reactionCode, reactionName);

                    CreateStructureFromFile newMaterial = new CreateStructureFromFile(path);
                    reactionData.addStartingMaterial(newMaterial.readFromFile("SM1"));
                    reactionData.addStartingMaterial(newMaterial.readFromFile("SM2"));
                    reactionData.addStartingMaterial(newMaterial.readFromFile("SM3"));
                    reactionData.setMainProduct(newMaterial.readFromFile("MP"));
                    CreateConditionDataFromFile newConditions = new CreateConditionDataFromFile(path);
                    reactionData.setConditions(newConditions.readFromFile());

                    reactionsLog.add(reactionData);

                    System.out.println("reaction was added.");
                    break;

                }
                case 2: {
                /*
                    System.out.println("Enter book title: ");
                    String bookTitle = scanner.nextLine();
                    System.out.println("Enter book author: ");
                    String bookAuthor = scanner.nextLine();
                    books.remove(new Book(bookTitle, bookAuthor));
                    System.out.println("Your book was removed from list.");
                    break;
                    */
                }
                case 3: {
                    System.out.println("Reactions list: ");
                    for (ReactionData reaction : reactionsLog) {
                        System.out.println(reaction);
                    }
                    System.out.println("Reactions list end.");
                    break;
                }
                case 4: {
                    System.out.println("Good by!");
                    System.exit(0);
                }
            }
            System.out.println("");
        }
    }
}


