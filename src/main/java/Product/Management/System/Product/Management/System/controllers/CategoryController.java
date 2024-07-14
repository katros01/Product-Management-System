package Product.Management.System.Product.Management.System.controllers;

import Product.Management.System.Product.Management.System.models.Category;
import Product.Management.System.Product.Management.System.services.CategoryService;
import Product.Management.System.Product.Management.System.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Category>>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(new CustomResponse<>("categories retrieved", HttpStatus.OK.value(), categories), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Category>> getCategoryById(@PathVariable String id) {
        Optional<Category> category = categoryService.getCategoryById(id);
        return category.map(value -> new ResponseEntity<>(new CustomResponse<>("category found", HttpStatus.OK.value(), value), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new CustomResponse<>("category not found", HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Category>> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(new CustomResponse<>("Category created successfully", HttpStatus.CREATED.value(),createdCategory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Category>> updateCategory(@PathVariable String id, @RequestBody Category category) {
        category.setId(id);
        Category updatedCategory = categoryService.saveCategory(category);
        return new ResponseEntity<>(new CustomResponse<>("Category updated successfully", HttpStatus.OK.value(), updatedCategory), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> deleteCategory(@PathVariable String id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(new CustomResponse<>("Category deleted successfully", HttpStatus.OK.value()), HttpStatus.OK);
    }
}

