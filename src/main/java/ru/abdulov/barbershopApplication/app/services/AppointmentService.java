package ru.abdulov.barbershopApplication.app.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import ru.abdulov.barbershopApplication.app.entitys.Appointment;
import ru.abdulov.barbershopApplication.app.entitys.Barber;
import ru.abdulov.barbershopApplication.app.entitys.User;
import ru.abdulov.barbershopApplication.app.repositories.AppointmentRepository;
import ru.abdulov.barbershopApplication.app.repositories.BarberRepository;
import ru.abdulov.barbershopApplication.app.repositories.UserRepository;

import java.io.IOException;
import java.security.Principal;

import static java.lang.Math.toIntExact;


/** Данный класс предназначен для обработки получаумых данных для взаимодействия с репозиторием записей */
@Service
@RequiredArgsConstructor
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    private final BarberRepository barberRepository;

    /** Данный метод обрабатывает данные о записи, и сохраняет полученный объект в репозиторий */
    public void saveAppointment(Principal principal, Appointment appointment) throws IOException {
        Barber barber=appointment.getBarber();
        int duration=0;
        if(appointment.getService().equals("Мужская стрижка")) { appointment.setDuration(3); }
        else if(appointment.getService().equals("Укладка бороды")) { appointment.setDuration(2); }
        else if(appointment.getService().equals("Бритьё опасной бритвой")) { appointment.setDuration(1); }
        barber.setTimeIsBusy(toIntExact(appointment.getTimeId()),appointment.getDuration(),true);
        appointment.setClient(getUserByPrincipal(principal));
        appointmentRepository.save(appointment);
    }

    /** Данный метод позволяет получить пользовтеля по сущности principal(данным уже вошедшего пользователя) */
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }
}
