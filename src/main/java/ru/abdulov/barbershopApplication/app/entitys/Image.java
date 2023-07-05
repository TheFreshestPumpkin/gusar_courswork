package ru.abdulov.barbershopApplication.app.entitys;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Данный класс предназначен для представления сущности изображения */
@Entity
@Table(name = "images")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "originalFileName")
    private String originalFileName;

    @Column(name = "size")
    private Long size;

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "isPreviewImage")
    private boolean isPreviewImage;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] bytes;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER) //Показывает отношение таблиц barbers и images, много фотографий к 1 барберу
    private Barber barber;                                           //cascade - как изменение фотографии повлияет на сущность барбера(обновится), fetch - способ загрузки, eager - подгружает все связанные объекты сразу
}
