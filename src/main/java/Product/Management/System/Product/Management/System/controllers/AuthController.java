package Product.Management.System.Product.Management.System.controllers;

import Product.Management.System.Product.Management.System.models.UserAccount;
import Product.Management.System.Product.Management.System.services.UserService;
import Product.Management.System.Product.Management.System.utils.CustomResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<CustomResponse<String>> login(@RequestBody UserAccount userCredentals, HttpSession session) {
        Optional<UserAccount> user = userService.authenticate(userCredentals.getUsername(), userCredentals.getPassword());
        if (user.isPresent()) {
            session.setAttribute("userId", user.get().getId());
            session.setAttribute("username", user.get().getUsername());
            session.setAttribute("role", user.get().getRole());
            return new ResponseEntity<>(new CustomResponse<>("User logged in", HttpStatus.OK.value(), user.get().getUsername()), HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomResponse<>("Invalid username or password", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/logout")
    public ResponseEntity<CustomResponse<String>> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>(new CustomResponse<>("User logged out", HttpStatus.OK.value()), HttpStatus.OK);
    }
}

