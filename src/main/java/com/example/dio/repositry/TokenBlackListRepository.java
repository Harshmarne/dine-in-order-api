package com.example.dio.repositry;

import com.example.dio.model.TokenBlackList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenBlackListRepository extends JpaRepository<TokenBlackList,String> {

    boolean existsByToken(String token);

    List<TokenBlackList> findByExpirationBefore(long currant);
}
