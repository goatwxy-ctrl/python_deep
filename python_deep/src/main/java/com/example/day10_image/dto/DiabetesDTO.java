package com.example.day10_image.dto;
import com.example.day10_image.entity.DiabetesEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiabetesDTO {

    Long num2;
    double loss2;
    double accuracy2;
    int epochs2;
    String name2;

    public DiabetesEntity entity() {
        DiabetesEntity de = new DiabetesEntity();
        de.setNum2(num2);
        de.setAccuracy2(accuracy2);
        de.setLoss2(loss2);
        de.setEpochs2(epochs2);
        de.setName2(name2);
        return de;
    }

}