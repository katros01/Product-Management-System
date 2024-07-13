package Product.Management.System.Product.Management.System.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private Double price;
    private int quantity;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public Product(String id, String name, String categoryId, Double price, int quantity) {
        this.id = id;
        this.name = name;
//        this.categoryId = categoryId;
        this.price = price;
        this.quantity = quantity;

    }

    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public String getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(String categoryId) {
//        this.categoryId = categoryId;
//    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
