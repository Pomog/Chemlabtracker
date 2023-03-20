package console_ui;

import console_ui.actions.UIAction;

import java.util.List;

public class UIMenu {

    private static final String MENU_HEADER = "\r\nProgram menu:";
    private static final String PROMPT_TOPIC_ACTION = "an action number: ";
    private static final String ERROR_INVALID_ACTION_NUMBER = "Error: Please enter one of the menu numbers.";

    private final UIActionsList uiActionsList;
    private final UserCommunication userCommunication;


    public UIMenu(UIActionsList uiActionsList, UserCommunication userCommunication) {
        this.uiActionsList = uiActionsList;
        this.userCommunication = userCommunication;
    }

    public void startUI() {
        while (true) {
            userCommunication.informUser(MENU_HEADER);
            //TODO get role here maybe ?
            List<UIAction> uiActionsListForUserRole = uiActionsList.getUIActionsListForUserRole();
            for (int i = 0; i < uiActionsListForUserRole.size(); i++) {
                userCommunication.informUser(i + 1 + ". " + uiActionsListForUserRole.get(i).getActionName());
            }
            userCommunication.requestInput(PROMPT_TOPIC_ACTION);
            try {
                Integer userChoice = userCommunication.getMenuActionNumber();
                if (userChoice > 0 && userChoice < uiActionsListForUserRole.size() + 1) {
                    uiActionsListForUserRole.get(userChoice - 1).execute();
                } else {
                    userCommunication.informUser(ERROR_INVALID_ACTION_NUMBER);
                }
            } catch (NumberFormatException e) {
                userCommunication.informUser(ERROR_INVALID_ACTION_NUMBER);
            }
        }
    }

}
