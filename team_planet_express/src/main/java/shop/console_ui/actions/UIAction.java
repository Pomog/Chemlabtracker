package shop.console_ui.actions;

public abstract class UIAction {

    private final String actionName;
    private final int accessNumber;

    protected UIAction(String actionName, int accessNumber) {
        this.actionName = actionName;
        this.accessNumber = accessNumber;
    }

    public String getActionName() {
        return actionName;
    }

    public int getAccessNumber() {
        return accessNumber;
    }

    public abstract void execute();

}
