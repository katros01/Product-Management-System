package Product.Management.System.Product.Management.System.repositories;

import Product.Management.System.Product.Management.System.models.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserAccount, Long> {

    @Query("SELECT u FROM UserAccount u")
    List<UserAccount> findAllUsers();

    @Query("SELECT u FROM UserAccount u WHERE u.id = :id")
    Optional<UserAccount> findUserById(@Param("id") String id);

    @Query("SELECT u FROM UserAccount u WHERE u.username = :username")
    Optional<UserAccount> findUserByUsername(@Param("username") String username);

}