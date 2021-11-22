package ru.warpreaktor.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="price_rub")
    private int priceRub;
    @Column(name="price_cop")
    private int priceCop;

    @ManyToOne
    @JoinColumn(name = "categories_id")
    private Category productCategory;

    public Product(){
    }

    public Product(Long id, String name, int priceRub, int priceCop) {
        this.id = id;
        this.name = name;
        this.priceRub = priceRub;
        this.priceCop = priceCop;
    }
    public Product(String name, int priceRub, int priceCop, Category category) {
        this.name = name;
        this.priceRub = priceRub;
        this.priceCop = priceCop;
        this.productCategory = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", priceRub=" + priceRub +
                ", priceCop=" + priceCop +
                '}';
    }
}
