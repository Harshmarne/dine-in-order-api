package com.example.dio.utility.schedulers;

import com.example.dio.model.TokenBlackList;
import com.example.dio.repositry.TokenBlackListRepository;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;

@Component
@AllArgsConstructor
public class CleanExpireToken {

    private final TokenBlackListRepository tokenBlackListRepository;

    @Scheduled(cron = "0 5 * * * *")
    public void cleanExpiredToken(){
        List<TokenBlackList> blackList = tokenBlackListRepository.findByExpirationBefore(Instant.now().toEpochMilli());

        tokenBlackListRepository.deleteAll(blackList);
    }
}
