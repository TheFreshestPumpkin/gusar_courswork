package ru.abdulov.barbershopApplication.app.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.abdulov.barbershopApplication.app.entitys.User;
import ru.abdulov.barbershopApplication.app.entitys.enums.Role;
import ru.abdulov.barbershopApplication.app.repositories.UserRepository;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/** Данный класс предназначен для обработки получаумых данных для взаимодействия с репозиторием пользователей */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /** Данный метод создаёт объект класса user по полученным данным и сохраняет его в репозиторий */
    public boolean createUser(User user){
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) return false; //Если такой емейл уже естьб в базе данных
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_ADMIN);
        log.info("New user is saving, his email is {}",email);
        userRepository.save(user);
        return true;
    }

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    /** Данный метод позволяет получить из репозитория список всех пользователей */
    public List<User> userList(){
        return userRepository.findAll();
    }

    /** Данный метод позволяет удалить пользователя из репозитория по id */
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /** Данный метод позвоялет поменять роли полученного пользователя */
    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
}
