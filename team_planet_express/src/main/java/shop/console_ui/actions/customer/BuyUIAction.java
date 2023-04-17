package shop.console_ui.actions.customer;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.customer.BuyRequest;
import shop.core.responses.customer.BuyResponse;
import shop.core.services.actions.customer.BuyService;
import shop.core.support.CurrentUserId;

public class BuyUIAction extends UIAction {

    private static final String ACTION_NAME = "Buy items in the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String MESSAGE_CART_IS_CLOSED = "Your cart is closed now.";

    private final BuyService buyService;
    private final CurrentUserId currentUserId;
    private final UserCommunication userCommunication;

    public BuyUIAction(BuyService buyService, CurrentUserId currentUserId, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.buyService = buyService;
        this.currentUserId = currentUserId;
        this.userCommunication = userCommunication;
    }

    @Override
    public void execute() {
        BuyRequest request = new BuyRequest(currentUserId);
        BuyResponse response = buyService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(error -> userCommunication.informUser(error.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_CART_IS_CLOSED);
        }
    }

}
