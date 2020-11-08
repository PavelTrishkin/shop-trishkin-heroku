package ru.gb.trishkin.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders_tbl")
public class Order {
    private static final String SEQUENCE_NAME = "order_seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME, allocationSize = 1)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "created_fld")
    @CreationTimestamp
    private LocalDateTime created;

    @Column(name = "changed_fld")
    @UpdateTimestamp
    private LocalDateTime changed;

    @ManyToOne
    private User user;

    @Column(name = "sum_fld")
    private BigDecimal sum;

    @Column(name = "address_fld")
    private String address;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> details;

    @Column(name = "status_fld")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
