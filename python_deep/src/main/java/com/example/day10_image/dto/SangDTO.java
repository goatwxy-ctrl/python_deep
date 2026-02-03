package com.example.day10_image.dto;

import com.example.day10_image.entity.SangEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SangDTO {
    long sid;
    String sname;
    String simage; //화일명만 저장용도
    MultipartFile  simage1;//폼에서 받아온 이미지명
    LocalDate sdate;
    int sreadcount;//

    public SangEntity toentity() {
        SangEntity se =new SangEntity();
        se.setSid(sid);
        se.setSname(sname);
        se.setSimage(simage);
        se.setSdate(sdate);
        se.setSreadcount(sreadcount);
        return se;
    }
}
