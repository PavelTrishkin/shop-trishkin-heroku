package ru.gb.trishkin.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "products_tbl")
public class Product {
    private static final String SEQUENCE_NAME = "product_seq";

    public Product(Long id, String title, Double price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "price_fld")
    private Double price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;
}
