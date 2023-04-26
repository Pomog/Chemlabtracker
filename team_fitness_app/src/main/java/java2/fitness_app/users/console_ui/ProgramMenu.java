package java2.fitness_app.users.console_ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class ProgramMenu {

	private Map<Integer, UIAction> menuNumberToUIActionMap;

	@Autowired
	public ProgramMenu(List<UIAction> uiActions) {
		menuNumberToUIActionMap = new HashMap<>();
		menuNumberToUIActionMap.put(1, findUIAction(uiActions, AddUserUIAction.class));
		menuNumberToUIActionMap.put(2, findUIAction(uiActions, LoginUserUIAction.class));
		menuNumberToUIActionMap.put(3, findUIAction(uiActions, RemoveUserUIAction.class));
		menuNumberToUIActionMap.put(4, findUIAction(uiActions, GetAllUsersUIAction.class));
		menuNumberToUIActionMap.put(5, findUIAction(uiActions, ExitUIAction.class));
	}

	private UIAction findUIAction(List<UIAction> uiActions, Class uiActionClass) {
		return uiActions.stream()
				.filter(uiAction -> uiAction.getClass().equals(uiActionClass))
				.findFirst()
				.get();
	}

	public void print() {
		System.out.println();
		System.out.println("Program menu:");
		System.out.println("1. Register new user.");
		System.out.println("2. Login.");
		System.out.println("3. Delete user from database.");
		System.out.println("4. Show all users in the list");
		System.out.println("5. Exit");
		System.out.println();
	}

	public int getMenuNumberFromUser() {
		System.out.println("Enter menu item number to execute:");
		Scanner scanner = new Scanner(System.in);
		return Integer.parseInt(scanner.nextLine());
	}

	public void executeSelectedMenuItem(int selectedMenu) {
		menuNumberToUIActionMap.get(selectedMenu).execute();
	}

}
