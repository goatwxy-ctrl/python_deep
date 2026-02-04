package com.example.day10_image.dto;
import com.example.day10_image.entity.WineEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WineDTO {

    Long num3;
    double loss3;
    double accuracy3;
    int epochs3;
    String name3;

    public WineEntity entity() {
        WineEntity de = new WineEntity();
        de.setNum3(num3);
        de.setAccuracy3(accuracy3);
        de.setLoss3(loss3);
        de.setEpochs3(epochs3);
        de.setName3(name3);
        return de;
    }
}
