package Product.Management.System.Product.Management.System.repositories;

import Product.Management.System.Product.Management.System.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("SELECT c FROM Category c")
    List<Category> findAllCategories();

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Optional<Category> findCategoryById(@Param("id") Long id);
}
