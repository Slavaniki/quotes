package slavaniki.quotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import slavaniki.quotes.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
