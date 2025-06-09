package com.example.ewd.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "plants")
@Getter
@Setter
public class Plant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String latinName;

    @Column(columnDefinition = "TEXT")
    private String description;
    private String heightMin;
    private String heightMax;
    private String color;
    private String colorHex;
    private String bloomTimeFrom;
    private String bloomTimeTo;
    private String poisonous;
    private String sunlightRequirements;
    private String soilRequirements;
    private String waterRequirements;


    private String imageCover;
    private String imageJanuary;
    private String imageFebruary;
    private String imageMarch;
    private String imageApril;
    private String imageMay;
    private String imageJune;
    private String imageJuly;
    private String imageAugust;
    private String imageSeptember;
    private String imageOctober;
    private String imageNovember;
    private String imageDecember;

    @ManyToMany(mappedBy = "plants")
    @JsonIgnore
    private List<PlantCollection> collections;

}
