package menu.actions;

import data.Item;

import java.util.List;

public class ListItemsAction {

    public void run(List<Item> items) {
        items.forEach(System.out::println);
    }

}
