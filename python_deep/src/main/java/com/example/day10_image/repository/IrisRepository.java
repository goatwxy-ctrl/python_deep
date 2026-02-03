package com.example.day10_image.repository;

import com.example.day10_image.entity.IrisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IrisRepository extends JpaRepository<IrisEntity,Long> {

}
