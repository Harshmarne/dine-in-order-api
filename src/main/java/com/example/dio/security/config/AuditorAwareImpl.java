package com.example.dio.security.config;

import com.example.dio.security.util.UserIdentity;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

@AllArgsConstructor
@Configuration
public class AuditorAwareImpl  implements AuditorAware<String> {

    private final UserIdentity userIdentity;

    /**
     * @return
     */
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(userIdentity.getCurrentUserEmail());
    }
}
