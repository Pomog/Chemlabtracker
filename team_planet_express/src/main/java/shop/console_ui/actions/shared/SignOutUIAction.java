package shop.console_ui.actions.shared;

import shop.console_ui.UserCommunication;
import shop.console_ui.actions.UIAction;
import shop.core.domain.user.UserRole;
import shop.core.requests.shared.SignOutRequest;
import shop.core.responses.shared.SignOutResponse;
import shop.core.services.actions.shared.SignOutService;
import shop.core.support.CurrentUserId;
import shop.dependency_injection.DIComponent;
import shop.dependency_injection.DIDependency;

@DIComponent
public class SignOutUIAction extends UIAction {

    private static final String ACTION_NAME = "Sign out";
    private static final int ACCESS_NUM = UserRole.getAccessNumberExclude(UserRole.GUEST);

    private static final String MESSAGE_SIGNED_OUT = "Signed out.";

    @DIDependency
    private SignOutService signOutService;
    @DIDependency
    private CurrentUserId currentUserId;
    @DIDependency
    private UserCommunication userCommunication;

    public SignOutUIAction() {
        super(ACTION_NAME, ACCESS_NUM);
    }

    @Override
    public void execute() {
        SignOutRequest request = new SignOutRequest(currentUserId);
        SignOutResponse response = signOutService.execute(request);
        if (response.hasErrors()) {
            response.getErrors().forEach(coreError -> userCommunication.informUser(coreError.getMessage()));
            return;
        }
        userCommunication.informUser(MESSAGE_SIGNED_OUT);
    }

}
