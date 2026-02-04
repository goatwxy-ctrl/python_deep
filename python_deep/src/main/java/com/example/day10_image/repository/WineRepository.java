package com.example.day10_image.repository;
import com.example.day10_image.entity.WineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WineRepository extends JpaRepository<WineEntity,Long> {
}
