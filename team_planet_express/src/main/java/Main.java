import menu.Menu;
import menu.ShopController;

public class Main {

    public static void main(String[] args) {

        Menu menu = new Menu(new ShopController());
        menu.run();

    }

}
