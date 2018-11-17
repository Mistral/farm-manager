package pro.farmmanager.user;

import pro.farmmanager.infrastructure.Repository;

class UserManager {

    private Repository<User> repository;

    UserManager(Repository<User> repository) {
        this.repository = repository;
    }

}
