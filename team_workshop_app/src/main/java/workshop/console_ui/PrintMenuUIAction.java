package workshop.console_ui;

public class PrintMenuUIAction  implements UIAction{
    @Override
     public void execute() {
        System.out.println("Program menu:");
        System.out.println("1. Add car to list");
        System.out.println("2. Delete car from list");
        System.out.println("3. Show all cars in the list");
        System.out.println("4. Exit");
        System.out.println("");

    }
}
