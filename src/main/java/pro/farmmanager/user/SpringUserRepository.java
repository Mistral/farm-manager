package pro.farmmanager.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface SpringUserRepository extends JpaRepository<User, UUID> {

}
