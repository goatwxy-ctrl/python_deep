package com.example.day10_image.controller;

import com.example.day10_image.dto.DiabetesDTO;
import com.example.day10_image.dto.IrisDTO;
import com.example.day10_image.dto.SangDTO;
import com.example.day10_image.entity.DiabetesEntity;
import com.example.day10_image.entity.IrisEntity;
import com.example.day10_image.entity.SangEntity;
import com.example.day10_image.repository.DiabetesRepository;
import com.example.day10_image.repository.IrisRepository;
import com.example.day10_image.service.SangService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class SangController {
    String path = "C:/sundo/springboot/day10_image/src/main/resources/static/image";
    @Autowired
    SangService sangService;

    @GetMapping("/")
    public String kk1() {
        return "main";
    }

    /// 자료입력 클릭
    @GetMapping("/inputgo")
    public String kk2() {
        return "input";
    }//입력폼에서 자료입력된 자료를 받아옴

    @PostMapping("/inputsave")
    public String kk3(SangDTO dto) throws IOException {
        MultipartFile mf = dto.getSimage1();
        String fname = mf.getOriginalFilename();
        /// /화일고유번호 지정
        UUID ud = UUID.randomUUID();
        fname = ud.toString() + "-" + fname;
        ////  디비에 저장
        dto.setSimage(fname);
        SangEntity sentity = dto.toentity();
        sangService.insertp(sentity);

        /// 이미지 폴더에 저장
        mf.transferTo(new File(path + "\\" + fname));


        return "redirect:/";
    }

    /// 출력
    @GetMapping("/outgo")
    public String kk4(Model mo) {
        List<SangEntity> list = sangService.allot();
        mo.addAttribute("list", list);
        log.info("자료의 갯수 : " + list.size());
        return "out";
    }

    /// 삭제
    @GetMapping("/delete")
    public String kk5(@RequestParam("number") long number,
                      @RequestParam("delimage") String delimage) {
        sangService.deletea(number); //디비자료 삭제
        //이미지폴더삭제
        File ff = new File(path + "/" + delimage);
        ff.delete();
        return "redirect:/outgo";
    }

    /// 자세히 보기
    @GetMapping("/detail")
    public String kk6(@RequestParam("number") long number, Model mo) {
        //상품번호에 해당되는 자료들을 가지고 옴
        //조회수 증가
        sangService.readcount(number);
        /// //////////////////////

        SangEntity dto = sangService.detail(number);
        mo.addAttribute("dto", dto);
        return "detailview";
    }

    //수정
    @GetMapping("/modify")
    public String kk7(@RequestParam("number") long number, Model mo) {
        SangEntity dto = sangService.modify1(number);
        mo.addAttribute("dto", dto);
        return "modifyview";
    }

    //수정된 자료 저장
    @PostMapping("/modifysave")
    public String kk8(SangDTO dto, @RequestParam("simagego") String simagego) throws IOException {

        String fname = "";
        MultipartFile mf = dto.getSimage1();
        UUID ud = UUID.randomUUID();

        if (mf.isEmpty()) {//수정폼에서 이미지화일을 지정안했을 경우
            log.info("이미지지정 안할경우 화일명" + simagego);
            fname = simagego;
        } else {//수정폼에서 이미지화일을 지정했을 경우
            mf = dto.getSimage1();
            fname = mf.getOriginalFilename();
            /// /화일고유번호 지정
            fname = ud.toString() + "-" + fname;
            ////  디비에 저장
            log.info("이미지화일명 : " + fname);
            mf.transferTo(new File(path + "/" + fname));
            File ff = new File(path + "/" + simagego);
            ff.delete();
        }
        dto.setSimage(fname);
        SangEntity sentity = dto.toentity();
        sangService.insertp(sentity);

        /// 이미지 폴더에 저장
        return "redirect:/";
    }

}
