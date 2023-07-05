package ru.abdulov.barbershopApplication.app.entitys.enums;

import org.springframework.security.core.GrantedAuthority;

/** Здесь перечисленны возможные роли, доступные в веб-приложении */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_MANAGER,
    ROLE_ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }

}
