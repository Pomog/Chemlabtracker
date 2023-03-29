package console_ui.actions.customer;

import console_ui.UserCommunication;
import console_ui.actions.UIAction;
import core.domain.user.UserRole;
import core.requests.customer.BuyRequest;
import core.responses.customer.BuyResponse;
import core.services.actions.customer.BuyService;
import core.support.MutableLong;

public class BuyUIAction extends UIAction {

    private static final String ACTION_NAME = "Buy items in the cart";
    private static final int ACCESS_NUMBER = UserRole.getAccessNumber(UserRole.GUEST, UserRole.CUSTOMER);

    private static final String MESSAGE_CART_IS_CLOSED = "Your cart is closed now.";

    private final BuyService buyService;
    private final UserCommunication userCommunication;
    private final MutableLong currentUserId;

    public BuyUIAction(BuyService buyService, MutableLong currentUserId, UserCommunication userCommunication) {
        super(ACTION_NAME, ACCESS_NUMBER);
        this.buyService = buyService;
        this.userCommunication = userCommunication;
        this.currentUserId = currentUserId;
    }

    @Override
    public void execute() {
        BuyRequest request = new BuyRequest(currentUserId.getValue());
        BuyResponse response = buyService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(error -> userCommunication.informUser(error.getMessage()));
        } else {
            userCommunication.informUser(MESSAGE_CART_IS_CLOSED);
        }
    }

}
