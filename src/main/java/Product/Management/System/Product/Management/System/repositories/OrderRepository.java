package Product.Management.System.Product.Management.System.repositories;

import Product.Management.System.Product.Management.System.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, String> {
    List<Orders> findByUserId(String userId);
}

