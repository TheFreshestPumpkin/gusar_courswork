package ru.abdulov.barbershopApplication.app.entitys;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/** Данный класс предназначен для представления сущности барбера */
@Data                         //Создаёт геттеры, сеттеры, toString переопределяет  equals
@Entity                      //Показывает hibernate, что данный класс эмулирует таблицу из бд
@Table(name = "barbers")     //Название таблицы
@AllArgsConstructor          //Создает конструктор который принимает в аргементы все поля класса
@NoArgsConstructor           //Создаёт конструктор без аргументов
public class Barber {
    @Id                      //указывает что поле id, является id
    @GeneratedValue(strategy = GenerationType.AUTO) //автоматическая генерация id
    @Column(name = "id")                            //явное указание колонки в бд
    private Long id;

    @Column(name = "barbName")
    private String barbName;

    @Column(name = "experience")
    private int experience;

    @Column(name = "rating")
    private float rating;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "barber")
    private List<Image> images=new ArrayList<>();

    private Long previewImageId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER
            ,mappedBy = "barber")
    private List<Appointment> appointments=new ArrayList<>();

    private boolean[] timeIsBusy=new boolean[28];

    public int intRating(){
        return Math.round(rating);
    }

    public void addImageToBarber(Image image){
        image.setBarber(this);
        images.add(image);
    }

    public void setTimeIsBusy(int timeId,int duration, boolean bool) {
        for(int i=timeId;i<timeId+duration && i<timeIsBusy.length;i++) {
            timeIsBusy[i] = bool;
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Barber;
    }

}
