package com.example.apkabazadanych2.junctionTables.productsByUsers;

import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "productsByUsers")
public class ProductsByUsers {
    @Id
    @SequenceGenerator(
            name = "productsByUsers_sequence",
            sequenceName = "productsByUsers_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "productsByUsers_sequence"
    )

    public Long ID_productsByUsers;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_User", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_Product", nullable = false)
    private Product product;

    public LocalDate date;
    public Long weight;
    public Long time;

}
