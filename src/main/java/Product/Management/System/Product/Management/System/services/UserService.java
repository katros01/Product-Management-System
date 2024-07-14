package Product.Management.System.Product.Management.System.services;

import Product.Management.System.Product.Management.System.models.UserAccount;
import Product.Management.System.Product.Management.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserAccount> getAllUsers() {
        return userRepository.findAllUsers();
    }

    public Optional<UserAccount> getUserById(String id) {
        return userRepository.findUserById(id);
    }

    public Optional<UserAccount> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public UserAccount saveUser(UserAccount user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<UserAccount> authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}

