package br.com.basis.madre.prescricao.service;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationPrincipalService {
    private static final String NAO_INFORMADO = "Não Informado";

    public String getLoginAtivo() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();

            if (Objects.nonNull(principal)) {
                return principal.toString();
            }
        }

        return NAO_INFORMADO;
    }

}
