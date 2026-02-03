package com.example.day10_image.service;

import com.example.day10_image.entity.SangEntity;

import java.util.ArrayList;
import java.util.List;

public interface SangService {

    void insertp(SangEntity sentity);

    List<SangEntity> allot();

    void deletea(long number);

    SangEntity detail(long number);

    void readcount(long number);

    SangEntity modify1(long number);
}
