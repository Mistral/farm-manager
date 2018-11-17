package pro.farmmanager.user;

import java.util.UUID;

public class UserFacade {

    private UserManager userManager;

    public UserFacade(UserManager userManager) {
        this.userManager = userManager;
    }

    public UUID getAuthorizedUser() {
        return UUID.randomUUID();
    }

}
