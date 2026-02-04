package com.example.day10_image.controller;
import com.example.day10_image.dto.DiabetesDTO;
import com.example.day10_image.entity.DiabetesEntity;
import com.example.day10_image.repository.DiabetesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
@Controller
public class DiabetesController {

    @Autowired
    DiabetesRepository diabetesRepository;

    ////
    @GetMapping("/diabetes")// top 이곳을 호출
    public String ss8(@RequestParam int epoch2,
                      @RequestParam double aa,
                      @RequestParam double bb,
                      @RequestParam double cc,
                      @RequestParam double dd,
                      @RequestParam double ee,
                      @RequestParam double ff,
                      @RequestParam double gg,
                      @RequestParam double hh
    ) throws IOException {
        if (epoch2 > 0) {
            ProcessBuilder processBuilder = new ProcessBuilder("python",
                    "C:/sundo/AIpython/day5_diabetes_m/diabetes2.py",
                    String.valueOf(epoch2),
                    String.valueOf(aa),
                    String.valueOf(bb),
                    String.valueOf(cc),
                    String.valueOf(dd),
                    String.valueOf(ee),
                    String.valueOf(ff),
                    String.valueOf(gg),
                    String.valueOf(hh));
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));

            log.info("완료");

            StringBuilder output2 = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {// br.readline
                System.out.println("lise " + line);
                output2.append(line);
            }
        }
        return "redirect:/diabetesview";
    }

    @GetMapping("/diabetesview")
    public String ss8(Model model) {
        List<DiabetesEntity> list2 = diabetesRepository.findAll();
        model.addAttribute("list", list2);
        return "diabetesView";
    }

    @ResponseBody
    @PostMapping("/diabetesout")//파이선에서 자료를 이곳으로 넘기면..받아서 DB에 저장
    public ResponseEntity<String> ss9(@RequestParam double loss2,
                                      @RequestParam double accuracy2,
                                      @RequestParam int epochs2,
                                      @RequestParam String name2
    ) throws IOException {
        DiabetesDTO diabetesDTO = new DiabetesDTO();
        diabetesDTO.setLoss2(loss2);
        diabetesDTO.setAccuracy2(accuracy2);
        diabetesDTO.setEpochs2(epochs2);
        diabetesDTO.setName2(name2);
        System.out.println();
        System.out.println("diabetes 데이터: " + diabetesDTO.toString());
        System.out.println();
        DiabetesEntity diabetesEntity = diabetesDTO.entity();
        diabetesRepository.save(diabetesEntity);
        return ResponseEntity.ok("데이터 수신 완료");
    }
}
