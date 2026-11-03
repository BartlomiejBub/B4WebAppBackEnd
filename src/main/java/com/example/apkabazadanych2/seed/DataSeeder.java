package com.example.apkabazadanych2.seed;

import com.example.apkabazadanych2.exercise.Exercise;
import com.example.apkabazadanych2.exercise.ExerciseRepository;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductList;
import com.example.apkabazadanych2.junctionTables.recipe.RecipeProductListRepository;
import com.example.apkabazadanych2.product.Product;
import com.example.apkabazadanych2.product.ProductRepository;
import com.example.apkabazadanych2.recipe.Recipe;
import com.example.apkabazadanych2.recipe.RecipeRepository;
import com.example.apkabazadanych2.user.User;
import com.example.apkabazadanych2.user.UserRepository;
import com.example.apkabazadanych2.user.UserRole;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

// Fills an empty database with starting data. Runs on every start, skips what already exists.
@Component
@AllArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataSeeder.class);

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeProductListRepository recipeProductListRepository;
    private final ExerciseRepository exerciseRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment env;

    @Override
    @Transactional
    public void run(String... args) {
        User admin = seedAdmin();
        if (productRepository.count() == 0) {
            seedProductsAndRecipes(admin);
        }
        if (exerciseRepository.count() == 0) {
            seedExercises(admin);
        }
    }

    private User seedAdmin() {
        return userRepository.findByLogin("admin").orElseGet(() -> {
            String password = env.getProperty("ADMIN_PASSWORD", "admin");
            User admin = new User(null, "Admin", "Admin", "admin",
                    bCryptPasswordEncoder.encode(password), "admin@b4webapp.local");
            admin.userRole = UserRole.ADMIN;
            admin.enabled = true; // skip email confirmation
            LOGGER.info("Seeded admin account (login: admin)");
            return userRepository.save(admin);
        });
    }

    private void seedProductsAndRecipes(User admin) {
        Product oats      = product("Rolled oats",     379.0, 13.2, 67.7,  6.5, 100L, admin);
        Product milk      = product("Milk 2%",          50.0,  3.4,  4.8,  2.0, 100L, admin);
        Product banana    = product("Banana",           89.0,  1.1, 22.8,  0.3, 120L, admin);
        Product chicken   = product("Chicken breast",  120.0, 22.5,  0.0,  2.6, 100L, admin);
        Product rice      = product("White rice",      130.0,  2.7, 28.0,  0.3, 100L, admin);
        Product egg       = product("Egg",             143.0, 12.6,  0.7,  9.5,  60L, admin);
        Product bread     = product("Wholegrain bread",247.0, 13.0, 41.0,  3.4,  40L, admin);
        Product yogurt    = product("Greek yogurt",     97.0,  9.0,  3.6,  5.0, 150L, admin);
        Product salmon    = product("Salmon",          208.0, 20.0,  0.0, 13.0, 100L, admin);
        Product broccoli  = product("Broccoli",         34.0,  2.8,  6.6,  0.4, 100L, admin);
        Product oliveOil  = product("Olive oil",       884.0,  0.0,  0.0,100.0,  10L, admin);
        Product apple     = product("Apple",            52.0,  0.3, 13.8,  0.2, 180L, admin);

        Recipe porridge = recipe("Banana porridge", "Quick breakfast",
                "Bring the milk to a boil, add the oats and cook for 5 minutes. Top with sliced banana.",
                10L, 532.0, 20.4, 93.4, 10.5, 450L, 1L, admin);
        ingredient(porridge, oats, 80L);
        ingredient(porridge, milk, 250L);
        ingredient(porridge, banana, 120L);

        Recipe chickenRice = recipe("Chicken with rice", "Classic lunch",
                "Cook the rice. Dice the chicken and fry it in a pan with olive oil, season to taste.",
                25L, 588.0, 50.4, 56.0, 15.2, 410L, 1L, admin);
        ingredient(chickenRice, chicken, 200L);
        ingredient(chickenRice, rice, 200L);
        ingredient(chickenRice, oliveOil, 10L);

        Recipe scrambledEggs = recipe("Scrambled eggs on toast", "Protein breakfast",
                "Whisk the eggs, scramble them in a pan with a little olive oil and serve on toasted bread.",
                10L, 440.0, 25.5, 33.6, 21.9, 205L, 1L, admin);
        ingredient(scrambledEggs, egg, 120L);
        ingredient(scrambledEggs, bread, 80L);
        ingredient(scrambledEggs, oliveOil, 5L);

        Recipe salmonBroccoli = recipe("Baked salmon with broccoli", "Light dinner",
                "Bake the salmon at 200°C for 15 minutes. Steam the broccoli and drizzle with olive oil.",
                20L, 568.0, 35.6, 13.2, 41.6, 360L, 1L, admin);
        ingredient(salmonBroccoli, salmon, 150L);
        ingredient(salmonBroccoli, broccoli, 200L);
        ingredient(salmonBroccoli, oliveOil, 10L);

        Recipe yogurtBowl = recipe("Yogurt and apple bowl", "Healthy snack",
                "Dice the apple and mix it with Greek yogurt and a handful of oats.",
                5L, 305.0, 16.4, 48.4, 7.9, 350L, 1L, admin);
        ingredient(yogurtBowl, yogurt, 150L);
        ingredient(yogurtBowl, apple, 180L);
        ingredient(yogurtBowl, oats, 20L);

        LOGGER.info("Seeded {} products and {} recipes", productRepository.count(), recipeRepository.count());
    }

    private void seedExercises(User admin) {
        exercise("Push-up", "Keep your body straight and lower your chest to the floor.", "Chest", 1L, admin);
        exercise("Squat", "Feet shoulder-width apart, lower your hips until thighs are parallel to the floor.", "Legs", 1L, admin);
        exercise("Pull-up", "Hang from a bar and pull yourself up until your chin is above it.", "Back", 1L, admin);
        exercise("Lunge", "Step forward and lower your back knee towards the floor, then switch legs.", "Legs", 1L, admin);
        exercise("Burpee", "Squat, jump back into a plank, do a push-up, jump forward and jump up.", "Full body", 2L, admin);
        exercise("Crunch", "Lie on your back with knees bent and lift your shoulders off the floor.", "Abs", 1L, admin);

        LOGGER.info("Seeded {} exercises", exerciseRepository.count());
    }

    private Product product(String name, Double kcal, Double protein, Double carbs, Double fat,
                            Long gramsInOne, User user) {
        Product p = new Product();
        p.name = name;
        p.caloric = kcal;
        p.protein = protein;
        p.carbs = carbs;
        p.fat = fat;
        p.gramsInOne = gramsInOne;
        p.setUser(user);
        return productRepository.save(p);
    }

    private Recipe recipe(String name, String header, String description, Long timeToCook,
                          Double kcal, Double protein, Double carbs, Double fat,
                          Long gramsInOne, Long portion, User user) {
        Recipe r = new Recipe();
        r.name = name;
        r.header = header;
        r.description = description;
        r.timeToCook = timeToCook;
        r.caloric = kcal;
        r.protein = protein;
        r.carbs = carbs;
        r.fat = fat;
        r.gramsInOne = gramsInOne;
        r.portion = portion;
        r.user = user;
        return recipeRepository.save(r);
    }

    private void ingredient(Recipe recipe, Product product, Long weight) {
        RecipeProductList row = new RecipeProductList();
        row.recipe = recipe;
        row.product = product;
        row.weight = weight;
        recipeProductListRepository.save(row);
    }

    private void exercise(String name, String description, String target, Long caloriesBurnedInRep, User user) {
        Exercise e = new Exercise();
        e.name = name;
        e.description = description;
        e.target = target;
        e.caloriesBurnedInRep = caloriesBurnedInRep;
        e.setUser(user);
        exerciseRepository.save(e);
    }
}
