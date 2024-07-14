package Product.Management.System.Product.Management.System.repositories;

import Product.Management.System.Product.Management.System.models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query("{'productId' : ?0 }")
    List<Review> findByProductId(String productId);

    @Query("{ 'userId' : ?0 }")
    List<Review> findByUserId(String userId);
}

