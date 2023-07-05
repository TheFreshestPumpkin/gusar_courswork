package ru.abdulov.barbershopApplication.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.abdulov.barbershopApplication.app.entitys.Barber;

import java.util.List;

public interface BarberRepository extends JpaRepository<Barber, Long> {
    List<Barber> findByBarbName(String barbName);
}
