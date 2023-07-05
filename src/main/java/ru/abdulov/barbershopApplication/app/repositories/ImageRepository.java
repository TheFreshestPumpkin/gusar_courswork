package ru.abdulov.barbershopApplication.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.abdulov.barbershopApplication.app.entitys.Image;

public interface ImageRepository extends JpaRepository<Image,Long> {

}
