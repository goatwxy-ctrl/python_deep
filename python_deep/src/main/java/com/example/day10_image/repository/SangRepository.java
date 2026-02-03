package com.example.day10_image.repository;

import com.example.day10_image.entity.SangEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SangRepository extends JpaRepository<SangEntity,Long> {
@Transactional
@Modifying
@Query(value = "update sang1212 set sreadcount=sreadcount+1 "+
        " where  sid =:number",nativeQuery = true)
    void readcount(@Param("number") long number);


    @Query(value = """
       select * from sang1212   where  sid =:number
       """,nativeQuery = true)
    SangEntity modify2(long number);

}


