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
        name = "iris",
        sequenceName = "iris_seq",
        allocationSize = 1,
        initialValue = 1000
)
@Table(name = "iris0202")
public class IrisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "iris")
    Long num;
    @Column
    double loss;
    @Column
    double accuracy;
    @Column
    int epochs;
    @Column
    String name;
}
