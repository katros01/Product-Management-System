package Product.Management.System.Product.Management.System.controllers;

import Product.Management.System.Product.Management.System.models.Orders;
import Product.Management.System.Product.Management.System.services.OrderService;
import Product.Management.System.Product.Management.System.utils.CustomResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Orders>> placeOrder(HttpSession session, @RequestBody Orders order) {
        String username = (String) session.getAttribute("username");
        String role = (String) session.getAttribute("role");

        if (username == null || !"buyer".equals(role)) {
            return new ResponseEntity<>(new CustomResponse<>("You are not authorized", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);

        }

        try {
            Orders createdOrder = orderService.placeOrder(order, username);
            return new ResponseEntity<>(new CustomResponse<>("Order created Successfully", HttpStatus.CREATED.value(), createdOrder), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse<>("Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<CustomResponse<List<Orders>>> getUserOrders(HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        if (userId == null) {
            return new ResponseEntity<>(new CustomResponse<>("You are not authorized", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
        }

        List<Orders> orders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(new CustomResponse<>("Orders Found", HttpStatus.OK.value(), orders), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Orders>>> getAllOrders(HttpSession session) {
        String role = (String) session.getAttribute("role");

        if (role == null || !"admin".equals(role)) {
            return new ResponseEntity<>(new CustomResponse<>("You are unauthorized", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
        }

        List<Orders> orders = orderService.getAllOrders();
        return new ResponseEntity<>(new CustomResponse<>("All orders are retrieved", HttpStatus.OK.value(), orders), HttpStatus.OK);
    }

}

