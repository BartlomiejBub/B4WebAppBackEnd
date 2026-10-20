package com.example.apkabazadanych2.user;

import com.example.apkabazadanych2.email.EmailSender;
import com.example.apkabazadanych2.registration.EmailValidator;
import com.example.apkabazadanych2.registration.token.ConfirmationToken;
import com.example.apkabazadanych2.registration.token.ConfirmationTokenService;
import com.example.apkabazadanych2.security.PasswordEncoder;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public void addNewUser(User user){
        Optional<User> userByLogin = userRepository.findByLogin(user.getLogin());
        Optional<User> userByEmail = userRepository.findByEmail(user.getEmail());

        if(userByLogin.isPresent()) {
            throw new IllegalStateException("User with this login already exists");
        } else if (userByEmail.isPresent()) {
            throw new IllegalStateException("User with this email already exists");
        } else {
            if(!emailValidator.test(user.getEmail())){
                throw new IllegalStateException("Not valid email");
            } else {

                user.setPassword(passwordEncoder.bCryptPasswordEncoder().encode(user.getPassword()));
                // A new account always starts with default goals (they can be changed on the "My goals" page)
                user.setGoalKcal(User.DEFAULT_GOAL_KCAL);
                user.setGoalProtein(User.DEFAULT_GOAL_PROTEIN);
                user.setGoalFat(User.DEFAULT_GOAL_FAT);
                user.setGoalCarbs(User.DEFAULT_GOAL_CARBS);
                userRepository.save(user);

                String token =  UUID.randomUUID().toString();
                ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), user);
                confirmationTokenService.saveConfirmationToken(confirmationToken);

                String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
                emailSender.sendEmail(user.getEmail(), buildEmail(user.getName(), link));
            }
        }
    }

    @Transactional
    public String confirmRegistrationToken(String token){
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("Invalid token"));
        if(confirmationToken.getConfirmedAt() != null){
            throw new IllegalStateException("email is already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())){
            throw new IllegalStateException("token is expired");
        }

        confirmationTokenService.setConfirmedAt(confirmationToken);
        confirmationToken.getUser().setEnabled(true);
        return "confirmed";
    }

    private String buildEmail(String name, String link) {
        return """
                <div style="font-family: Helvetica, Arial, sans-serif; font-size: 16px; margin: 0; background-color: #f4f4f4; padding: 20px;">
                    <table role="presentation" width="100%%" style="border-collapse: collapse; max-width: 600px; margin: 0 auto; background-color: #ffffff; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 6px rgba(0,0,0,0.1);">
                        <tr>
                            <td style="padding: 30px; background-color: #007bff; text-align: center; color: #ffffff;">
                                <h1 style="margin: 0; font-size: 24px;">Witaj w naszej aplikacji!</h1>
                            </td>
                        </tr>
                        <tr>
                            <td style="padding: 30px; text-align: left; color: #333333;">
                                <p style="margin: 0 0 20px 0; font-size: 18px;">Cześć <strong>%s</strong>,</p>
                                <p style="margin: 0 0 20px 0; line-height: 1.5;">
                                    Dziękujemy za rejestrację. Aby aktywować swoje konto i uzyskać pełny dostęp do systemu, kliknij poniższy przycisk:
                                </p>
                                <div style="text-align: center; margin: 30px 0;">
                                    <a href="%s" style="background-color: #007bff; color: #ffffff; padding: 14px 28px; text-decoration: none; border-radius: 5px; font-weight: bold; display: inline-block;">Aktywuj konto</a>
                                </div>
                                <p style="margin: 0; font-size: 14px; color: #777777;">
                                    Ten link wygaśnie za 15 minut. Jeśli to nie Ty zakładałeś konto, zignoruj tę wiadomość.
                                </p>
                            </td>
                        </tr>
                    </table>
                </div>
                """.formatted(name, link);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)){
           throw new IllegalStateException("User with id " + userId + " dont exists");
        } else {
            userRepository.deleteById(userId);
        }
    }

    @Transactional
    public void updateUser(User user){
        Optional<User> userOptional = userRepository.findByEmail(user.email);
        if (userOptional.isPresent() && !Objects.equals(userOptional.get().getLogin(), user.login)) {
            throw new IllegalStateException("This email is already taken");
        }
        User existing = userRepository.findById(user.ID_User)
                .orElseThrow(() -> new IllegalStateException("User with id " + user.ID_User + " dont exists"));
        existing.setName(user.name);
        existing.setSurname(user.surname);
        existing.setEmail(user.email);
        existing.setUserRole(user.userRole);
        existing.setEnabled(user.enabled);
        existing.setLocked(user.locked);
        userRepository.save(existing);
    }

    public UserGoals getGoals(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + " dont exists"));
        return new UserGoals(user.goalKcal, user.goalProtein, user.goalFat, user.goalCarbs);
    }

    @Transactional
    public UserGoals updateGoals(Long userId, UserGoals goals){
        if (!isValidGoal(goals.goalKcal) || !isValidGoal(goals.goalProtein) || !isValidGoal(goals.goalFat) || !isValidGoal(goals.goalCarbs)) {
            throw new IllegalStateException("Goals must be numbers greater than or equal to 0");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User with id " + userId + " dont exists"));
        user.setGoalKcal(goals.goalKcal);
        user.setGoalProtein(goals.goalProtein);
        user.setGoalFat(goals.goalFat);
        user.setGoalCarbs(goals.goalCarbs);
        userRepository.save(user);
        return getGoals(userId);
    }

    private boolean isValidGoal(Double value){
        return value != null && value >= 0 && !value.isInfinite();
    }

    public Optional<User> getUser(String userLogin){
        return userRepository.findByLogin(userLogin);
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found: " +username));
    }

    public LoginResponse loginUser(String login, String password){
        Optional<User> check = getUser(login);
        if(check.isPresent()){
            if(passwordEncoder.bCryptPasswordEncoder().matches(password, check.get().password)){
                return new LoginResponse("SUCCESS", login, check.get().email, check.get().userRole.name(), check.get().ID_User);
            } else {
                return new LoginResponse("WRONG PASSWORD", login, null, null, check.get().ID_User);
            }
        } else {
            return new LoginResponse("WRONG LOGIN", login, null, null, null);
        }
    }
}
