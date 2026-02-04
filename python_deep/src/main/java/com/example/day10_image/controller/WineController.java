package com.example.day10_image.controller;
import com.example.day10_image.dto.WineDTO;
import com.example.day10_image.entity.WineEntity;
import com.example.day10_image.repository.WineRepository;
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
public class WineController {
    @Autowired
    WineRepository wineRepository;

    @GetMapping("/wine")// top 이곳을 호출
    public String ss1(@RequestParam int epoch3,
                      @RequestParam double i,
                      @RequestParam double j,
                      @RequestParam double k,
                      @RequestParam double l
    ) throws IOException {
        if (epoch3 > 0) {
            ProcessBuilder processBuilder = new ProcessBuilder("python",
                    "C:/sundo/AIpython/day6_wine_m/wine2.py",
                    String.valueOf(epoch3),
                    String.valueOf(i),
                    String.valueOf(j),
                    String.valueOf(k),
                    String.valueOf(l));
            Process process = processBuilder.start();
            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));

            log.info("완료");

            StringBuilder output3 = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {// br.readline
                System.out.println("lise " + line);
                output3.append(line);
            }
        }
        return "redirect:/wineview";
    }

    @GetMapping("/wineview")
    public String ss2(Model model) {
        List<WineEntity> list3 = wineRepository.findAll();
        model.addAttribute("list", list3);
        return "wineView";
    }

    @ResponseBody
    @PostMapping("/wineout")//파이선에서 자료를 이곳으로 넘기면..받아서 DB에 저장
    public ResponseEntity<String> ss3(@RequestParam double loss3,
                                      @RequestParam double accuracy3,
                                      @RequestParam int epochs3,
                                      @RequestParam String name3
    ) throws IOException {
        WineDTO dto = new WineDTO();
        dto.setLoss3(loss3);
        dto.setAccuracy3(accuracy3);
        dto.setEpochs3(epochs3);
        dto.setName3(name3);
        System.out.println();
        System.out.println("wine 데이터: " + dto.toString());
        System.out.println();
        WineEntity wineEntity = dto.entity();
        wineRepository.save(wineEntity);
        return ResponseEntity.ok("데이터 수신 완료");
    }
}
