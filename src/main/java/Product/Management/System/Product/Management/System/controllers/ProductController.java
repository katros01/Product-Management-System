package Product.Management.System.Product.Management.System.controllers;

import Product.Management.System.Product.Management.System.models.Product;
import Product.Management.System.Product.Management.System.services.ProductService;
import Product.Management.System.Product.Management.System.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public ResponseEntity<List<Product>> getProducts() {
//        List<Product> products = productService.getAllProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

    @GetMapping(path = "{id}")
    public ResponseEntity<CustomResponse<Product>> getProduct(@PathVariable String id) {
        Optional<Product> product = productService.getProductById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(new CustomResponse<Product>("Product found", HttpStatus.OK.value(), product.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new CustomResponse<>("Product not found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED  );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Product>> updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        Product updatedProduct = productService.updateProduct(product);
        return new ResponseEntity<>(new CustomResponse<>("Product updated successfully", HttpStatus.OK.value(), updatedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(new CustomResponse<>("Product deleted successfully", HttpStatus.OK.value()), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortBy));
        Page<Product> products = productService.getAllProducts(pageable);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
