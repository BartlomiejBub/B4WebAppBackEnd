package com.example.apkabazadanych2.junctionTables.recipesByUsers;

import com.example.apkabazadanych2.junctionTables.productsByUsers.ProductsByUsers;
import com.example.apkabazadanych2.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecipesByUsersRepository extends JpaRepository<RecipesByUsers, Long> {
    List<RecipesByUsers> findByUserAndDate(User user, LocalDate date);

    @Query("SELECT COUNT(r) > 0 FROM RecipesByUsers r WHERE r.recipe.ID_Recipe = :id")
    boolean existsByRecipeID(@Param("id") Long id);
}
