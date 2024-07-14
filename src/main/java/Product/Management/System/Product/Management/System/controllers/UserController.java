package Product.Management.System.Product.Management.System.controllers;

import Product.Management.System.Product.Management.System.models.UserAccount;
import Product.Management.System.Product.Management.System.services.UserService;
import Product.Management.System.Product.Management.System.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserAccount>> getAllUsers() {
        List<UserAccount> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<UserAccount>> getUserById(@PathVariable String id) {
        Optional<UserAccount> user = userService.getUserById(id);
        return user.map(value -> new ResponseEntity<>(new CustomResponse<>("User found", HttpStatus.OK.value(), value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new CustomResponse<>("User Not found", HttpStatus.NOT_FOUND.value()),HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CustomResponse<UserAccount>> createUser(@RequestBody UserAccount user) {
        UserAccount createdUser = userService.saveUser(user);
        return new ResponseEntity<>(new CustomResponse<>("User created successfully", HttpStatus.CREATED.value(), createdUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<UserAccount>> updateUser(@PathVariable String id, @RequestBody UserAccount user) {
        user.setId(id);
        UserAccount updatedUser = userService.saveUser(user);
        return new ResponseEntity<>(new CustomResponse<>("User Updated successfully", HttpStatus.OK.value(),updatedUser), HttpStatus.OK);
    }
}

