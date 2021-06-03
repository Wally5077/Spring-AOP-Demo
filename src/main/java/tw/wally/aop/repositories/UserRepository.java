package tw.wally.aop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.wally.aop.model.User;

import java.util.Optional;

/**
 * @author - wally55077@gmail.com
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);
}
