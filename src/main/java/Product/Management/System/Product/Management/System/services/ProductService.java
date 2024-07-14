package Product.Management.System.Product.Management.System.services;

import Product.Management.System.Product.Management.System.models.Product;
import Product.Management.System.Product.Management.System.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAllProducts();
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAllProducts(pageable);
    }

    public Optional<Product> getProductById(String id) {
        return productRepository.findProductById(id);
    }

    public Page<Product> getProductsByCategoryId(String categoryId, Pageable pageable) {
        return productRepository.findProductsByCategoryId(categoryId, pageable);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
