package com.example.day10_image.dto;

import com.example.day10_image.entity.IrisEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IrisDTO {
    Long num;
    double loss;
    double accuracy;
    int epochs;
    String name;

    public IrisEntity entity() {
        IrisEntity ie = new IrisEntity();
        ie.setNum(num);
        ie.setAccuracy(accuracy);
        ie.setLoss(loss);
        ie.setEpochs(epochs);
        ie.setName(name);
        return ie;
    }
}
