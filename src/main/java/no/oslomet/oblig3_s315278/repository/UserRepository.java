package no.oslomet.oblig3_s315278.repository;

import no.oslomet.oblig3_s315278.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
