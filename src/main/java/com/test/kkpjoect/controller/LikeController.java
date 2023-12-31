package com.test.kkpjoect.controller;

import com.test.kkpjoect.dto.LikeDTO;
import com.test.kkpjoect.persistence.LikeRepository;
import com.test.kkpjoect.service.LikeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/")
public class LikeController {
    @Autowired
    private LikeService service;

    @PostMapping("/likeCreate")
    public void likeCreate(LikeDTO dto){
        service.LikeCreate(dto);
    }







}
