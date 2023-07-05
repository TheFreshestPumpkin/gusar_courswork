package ru.abdulov.barbershopApplication.app.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.abdulov.barbershopApplication.app.entitys.Image;
import ru.abdulov.barbershopApplication.app.repositories.ImageRepository;

import java.io.ByteArrayInputStream;


/** Данный класс предназначен для обработки изображений*/
@RestController                 //не возвращает представление
@RequiredArgsConstructor
public class ImageController {
    private final ImageRepository imageRepository;

    /** Данный метод формирует изображание из его представления в бд */
    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id){
        Image image = imageRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())  //получаем название файла
                .contentType(MediaType.valueOf(image.getContentType()))     //получаем расширение файла
                .contentLength(image.getSize())                             //получаем размер файла
                .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes()))); //переводим байты в изображение
    }
}
