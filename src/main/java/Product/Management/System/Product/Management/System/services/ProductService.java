package Product.Management.System.Product.Management.System.services;

import Product.Management.System.Product.Management.System.models.Product;
import Product.Management.System.Product.Management.System.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Optional<Product> getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    public List<Product> getProductsByCategoryId(String categoryId) {
        return productRepository.findProductsByCategoryId(categoryId);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
