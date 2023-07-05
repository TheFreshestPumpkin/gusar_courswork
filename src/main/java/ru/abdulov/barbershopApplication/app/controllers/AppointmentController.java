package ru.abdulov.barbershopApplication.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.abdulov.barbershopApplication.app.entitys.Appointment;
import ru.abdulov.barbershopApplication.app.services.AppointmentService;
import ru.abdulov.barbershopApplication.app.services.BarberService;

import java.io.IOException;
import java.security.Principal;


/** Данный класс предназначен для приёма данных о записи*/
@Controller
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final BarberService barberService;

    /** Данный метод обрабатывает полученные данные и формирует новый экземпляр класса appointment */
    @PostMapping("/barber/{barbId}/appointment/{timeId}/add")
    public String addAppointment(@PathVariable Long barbId, @PathVariable int timeId, @RequestParam("service") String service, Principal principal) throws IOException {
        Appointment appointment=new Appointment();
        appointment.setService(service);
        appointment.setBarber(barberService.getBarberById(barbId));
        appointment.setTimeId(timeId);
        appointmentService.saveAppointment(principal, appointment);
        return "redirect:/";
    }
}
