package com.example.day10_image.service;

import com.example.day10_image.entity.SangEntity;
import com.example.day10_image.repository.SangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SangServiceImp implements SangService {
    @Autowired
    SangRepository sangRepository;

    @Override
    public void insertp(SangEntity sentity) {
        sangRepository.save(sentity);
    }

    @Override
    public List<SangEntity> allot() {
        return sangRepository.findAll();
    }

    @Override
    public void deletea(long number) {
        sangRepository.deleteById(number);
    }

    @Override
    public SangEntity detail(long number) {
        return sangRepository.findById(number).orElse(null);
    }

    @Override
    public void readcount(long number) {

        sangRepository.readcount(number);

    }

    @Override
    public SangEntity modify1(long number) {
    SangEntity dto=    sangRepository.modify2(number);
        return dto;
    }
}
