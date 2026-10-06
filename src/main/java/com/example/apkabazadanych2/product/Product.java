package com.example.apkabazadanych2.product;

import com.example.apkabazadanych2.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    public Long ID_Product;
    public String name;
    public Double caloric;
    public Double protein;
    public Double carbs;
    public Double fat;
    public Long gramsInOne;
    public String code;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_User")
    private User user;

    public Long getUserID() {
        if (this.user != null) {
            return this.user.ID_User;
        }
        return null;
    }

}
