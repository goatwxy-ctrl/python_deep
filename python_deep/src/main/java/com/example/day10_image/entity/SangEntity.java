package com.example.day10_image.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(
        name = "koko",
        sequenceName = "sang1212_seq",
        initialValue = 1000,
        allocationSize = 1
        )
@Table(name = "sang1212")
public class SangEntity {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,generator = "koko"
    )
    @Column
    long sid;
    @Column
    String sname;
    @Column
    String simage;
    @Column
    LocalDate sdate;
    @Column
    int sreadcount;
}
