package com.example.apkabazadanych2.junctionTables.productsByUsers;

import com.example.apkabazadanych2.product.ProductRepository;
import com.example.apkabazadanych2.user.User;
import com.example.apkabazadanych2.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductsByUsersService {

    private final ProductsByUsersRepository productsByUsersRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public List<ProductsByUsersResponse> findByUserAndDate(Long userID, LocalDate date){
        User user = userRepository.findById(userID).orElseThrow( () -> new IllegalStateException("Nie znaleziono"));
        List<ProductsByUsers> listOfRaw = productsByUsersRepository.findByUserAndDate(user, date);
        return listOfRaw.stream().map(row -> new ProductsByUsersResponse(
                    row.getID_productsByUsers(),
                    row.getProduct().name,
                row.getProduct().caloric * row.getWeight()/100,
                row.getProduct().protein * row.getWeight()/100,
                row.getProduct().fat * row.getWeight()/100,
                row.getProduct().carbs * row.getWeight()/100,
                row.getWeight()
                )
        ).toList();
    }

    public void deleteProductByUsers(Long productID){
        productsByUsersRepository.deleteById(productID);
    }

    public void addNewProductByUsers(ProductByUserAddRequest productByUserAddRequest){
        ProductsByUsers productByUser = new ProductsByUsers(null, userRepository.getReferenceById(productByUserAddRequest.userID), productRepository.getReferenceById(productByUserAddRequest.productID), productByUserAddRequest.date, productByUserAddRequest.weight);
        productsByUsersRepository.save(productByUser);
    }

}
