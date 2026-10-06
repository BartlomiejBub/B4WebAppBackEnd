package com.example.apkabazadanych2.junctionTables.productsByUsers;

import com.example.apkabazadanych2.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductsByUsersRepository extends JpaRepository<ProductsByUsers, Long> {
    List<ProductsByUsers> findByUserAndDate(User user, LocalDate date);

    @Query("SELECT COUNT(p) > 0 FROM ProductsByUsers p WHERE p.product.ID_Product = :id")
    boolean existsByProductID(@Param("id") Long id);
}
