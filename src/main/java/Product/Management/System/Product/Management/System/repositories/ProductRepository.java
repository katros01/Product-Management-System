package Product.Management.System.Product.Management.System.repositories;

import Product.Management.System.Product.Management.System.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("SELECT p FROM Product p")
    List<Product> findAllProducts();

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Optional<Product> findProductById(@Param("id") String id);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    Page<Product> findProductsByCategoryId(@Param("categoryId") String categoryId, Pageable pageable);

    @Query("SELECT p FROM Product p")
    Page<Product> findAllProducts(Pageable pageable);

}
