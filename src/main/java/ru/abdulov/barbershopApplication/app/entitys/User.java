package ru.abdulov.barbershopApplication.app.entitys;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.abdulov.barbershopApplication.app.entitys.enums.Role;

import java.util.*;

/** Данный класс предназначен для представления сущности пользователя */
@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "active")
    private boolean active;

    @Column(name = "password",length = 1000)
    private String password;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY
            ,mappedBy = "client")
    private List<Appointment> appointments=new ArrayList<>();

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)           //помечается, как мапа для коллекций, Все записи хранятся в отдельной таблице
    @CollectionTable(name="user_role",joinColumns = @JoinColumn(name = "user_id")) //указывает имя таблицы, определяет оснвной столбец
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    public boolean isManager() {
        return roles.contains(Role.ROLE_MANAGER);
    }

    //от UserDetails, security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
