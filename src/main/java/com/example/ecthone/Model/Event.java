package com.example.ecthone.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private int id;

    @Column(name = "name")
    @Setter
    @Getter
    private String name;
    @Column(name = "description")
    @Setter
    @Getter
    private String description;
    @Column(name = "format")
    @Setter
    @Getter
    private Format format;
    @Column(name = "duration")
    @Setter
    @Getter
    private Duration duration;
    @Column(name = "address")
    @Setter
    @Getter
    private String address;
    @Column(name = "personid")
    @Setter
    @Getter
    private Integer personid;

    @Column(name = "eventDate")
    @Setter
    @Getter
    private String eventDate;

    @Column(name = "eventIcon")
    @Setter
    @Getter
    private String eventIcon;

    public Event(String name, String description, Format format, Duration duration, String address, Integer personid, String eventDate, String eventIcon) {
        this.name = name;
        this.description = description;
        this.format = format;
        this.duration = duration;
        this.address = address;
        this.personid = personid;
        this.eventDate = eventDate;
        this.eventIcon = eventIcon;
    }

    public Event() {

    }
}
