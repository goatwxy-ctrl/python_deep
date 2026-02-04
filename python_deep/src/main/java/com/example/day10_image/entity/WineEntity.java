package com.example.day10_image.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SequenceGenerator(
        name = "wine",
        sequenceName = "wine_seq",
        allocationSize = 1,
        initialValue = 1000
)
@Table(name = "wine2024")
public class WineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wine")
    Long num3;
    @Column
    double loss3;
    @Column
    double accuracy3;
    @Column
    int epochs3;
    @Column
    String name3;
}
