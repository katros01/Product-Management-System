package Product.Management.System.Product.Management.System.services;

import Product.Management.System.Product.Management.System.models.Orders;
import Product.Management.System.Product.Management.System.models.Product;
import Product.Management.System.Product.Management.System.models.UserAccount;
import Product.Management.System.Product.Management.System.repositories.OrderRepository;
import Product.Management.System.Product.Management.System.repositories.ProductRepository;
import Product.Management.System.Product.Management.System.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Orders placeOrder(Orders order, String username) throws Exception {
        Product product = productRepository.findById(order.getProduct().getId())
                .orElseThrow(() -> new Exception("Product not found"));

        if (product.getQuantity() < order.getQuantity()) {
            throw new Exception("Insufficient product quantity");
        }

        UserAccount user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new Exception("User not found"));
        
        product.setQuantity(product.getQuantity() - order.getQuantity());
        productRepository.save(product);

        order.setUser(user);
        order.setProduct(product);

        return orderRepository.save(order);
    }

    public List<Orders> getOrdersByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }
    public List<Orders> getAllOrders() {
        return orderRepository.findAll();
    }
}

