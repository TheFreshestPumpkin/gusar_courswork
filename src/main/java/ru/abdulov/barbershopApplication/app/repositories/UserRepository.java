package ru.abdulov.barbershopApplication.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.abdulov.barbershopApplication.app.entitys.Image;
import ru.abdulov.barbershopApplication.app.entitys.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

}
