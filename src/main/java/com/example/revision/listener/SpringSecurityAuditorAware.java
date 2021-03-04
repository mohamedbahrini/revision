package com.example.revision.listener;

import com.example.revision.utils.Constants;
import com.example.revision.utils.SecurityUtil;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtil.getUserName().orElse(Constants.SYSTEM_ACCOUNT));
    }
}
