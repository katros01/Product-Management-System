package Product.Management.System.Product.Management.System.controllers;

import Product.Management.System.Product.Management.System.models.Review;
import Product.Management.System.Product.Management.System.services.ReviewService;
import Product.Management.System.Product.Management.System.utils.CustomResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Review>>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return new ResponseEntity<>(new CustomResponse<>("Reviews retrieved successfully", HttpStatus.OK.value(), reviews), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Review>> getReviewById(@PathVariable String id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(value -> new ResponseEntity<>(new CustomResponse<>("Review found", HttpStatus.OK.value(), value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new CustomResponse<>("Review not found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<CustomResponse<List<Review>>> getReviewsByProductId(@PathVariable String productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        return new ResponseEntity<>(new CustomResponse<>("Reviews retrieved successfully", HttpStatus.OK.value(), reviews), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<CustomResponse<List<Review>>> getReviewsByUserId(@PathVariable String userId) {
        List<Review> reviews = reviewService.getReviewsByUserId(userId);
        return new ResponseEntity<>(new CustomResponse<>("Reviews retrieved successfully", HttpStatus.OK.value(), reviews), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Review>> createReview(@RequestBody Review review, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return new ResponseEntity<>(new CustomResponse<>("Unauthorized", HttpStatus.UNAUTHORIZED.value()), HttpStatus.UNAUTHORIZED);
        }

        review.setUserId(userId);
        Review createdReview = reviewService.saveReview(review);
        return new ResponseEntity<>(new CustomResponse<>("Review was added successfully", HttpStatus.CREATED.value(), createdReview), HttpStatus.CREATED);
    }
}

