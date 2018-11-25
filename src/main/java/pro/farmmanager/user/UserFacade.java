package pro.farmmanager.user;

import java.util.UUID;

public class UserFacade {

    private UserManager userManager;

    public static UUID authorizedId = UUID.randomUUID();

    public UserFacade(UserManager userManager) {
        this.userManager = userManager;
        authorizedId = UUID.randomUUID();
    }

    public UUID getAuthorizedUser() {
        return authorizedId;
    }

}
