package ru.abdulov.barbershopApplication.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.abdulov.barbershopApplication.app.entitys.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
}
