package com.example.day10_image.controller;

import com.example.day10_image.dto.IrisDTO;
import com.example.day10_image.entity.IrisEntity;
import com.example.day10_image.repository.IrisRepository;
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
public class IrisController {

    @Autowired
    IrisRepository irisRepository;

    @GetMapping("/iris")// top 이곳을 호출
    public String ss4(@RequestParam int epoch,
                      @RequestParam double a,
                      @RequestParam double b,
                      @RequestParam double c,
                      @RequestParam double d
    ) throws IOException {
        if (epoch > 0) {
            ProcessBuilder processBuilder = new ProcessBuilder("python",
                    "C:/sundo/AIpython/day4_iris_m/iris2.py",
                    String.valueOf(epoch),
                    String.valueOf(a),
                    String.valueOf(b),
                    String.valueOf(c),
                    String.valueOf(d));
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));

            log.info("완료");

            StringBuilder output = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {// br.readline
                System.out.println("lise " + line);
                output.append(line);
            }
        }
        return "redirect:/irisview";
    }

    @GetMapping("/irisview")
    public String ss5(Model model) {
        List<IrisEntity> list = irisRepository.findAll();
        model.addAttribute("list", list);
        return "irisView";
    }

    @ResponseBody
    @PostMapping("/irisout")//파이선에서 자료를 이곳으로 넘기면..받아서 DB에 저장
    public ResponseEntity<String> ss5(@RequestParam double loss,
                                      @RequestParam double accuracy,
                                      @RequestParam int epochs,
                                      @RequestParam String name
    ) throws IOException {
        IrisDTO irisDTO = new IrisDTO();
        irisDTO.setLoss(loss);
        irisDTO.setAccuracy(accuracy);
        irisDTO.setEpochs(epochs);
        irisDTO.setName(name);
        System.out.println();
        System.out.println("iris 데이터: " + irisDTO.toString());
        System.out.println();
        IrisEntity irisEntity = irisDTO.entity();
        irisRepository.save(irisEntity);
        return ResponseEntity.ok("데이터 수신 완료");
    }
}
