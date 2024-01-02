package com.test.kkpjoect.controller;

import com.test.kkpjoect.dto.LikeDTO;
import com.test.kkpjoect.model.LikeEntity;
import com.test.kkpjoect.persistence.LikeRepository;
import com.test.kkpjoect.service.LikeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/")
public class LikeController {
    @Autowired
    private LikeService service;

    @PostMapping("/likeCreate")
    public void likeCreate(@RequestBody LikeDTO dto){
        log.info("라이크 크리에이트 시작");
        log.info("받은 dto 값 확인"+dto);
        service.LikeCreate(dto);
    }
    @PostMapping("/likeDelete")
    public void likeDelete(@RequestBody LikeDTO dto){
        log.info("라이크 딜리트 시작");
        log.info("받은 dto 값 확인"+dto);
        service.LikeDelete(dto);
    }

    @PostMapping("/likeRead")
    public ResponseEntity<List<LikeEntity>> likeRead(@RequestBody LikeDTO dto){
        log.info("라이크 리드 시작");
        log.info("받은 dto 값 확인" + dto);
        List<LikeEntity> likeEntities = service.LikeRead(dto);
        log.info("출력되는 리스트"+likeEntities);
        // ResponseEntity로 데이터와 HTTP 상태코드를 함께 반환
        return new ResponseEntity<>(likeEntities, HttpStatus.OK);
    }





}
