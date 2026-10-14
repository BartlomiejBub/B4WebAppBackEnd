package com.example.apkabazadanych2.registration.token;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfirmationTokenService.class);

    private ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public Optional<ConfirmationToken> getToken(String token) {
      return confirmationTokenRepository.findByToken(token);
    }

    public void setConfirmedAt(ConfirmationToken confirmationToken) {
        confirmationToken.setConfirmedAt(LocalDateTime.now());
    }

    @Scheduled(cron = "0 0 3 * * *", zone = "Europe/Warsaw")
    public void deleteExpiredTokens() {
        int deleted = confirmationTokenRepository.deleteExpiredUnconfirmed(LocalDateTime.now());
        LOGGER.info("Deleted {} expired confirmation tokens", deleted);
    }
}
