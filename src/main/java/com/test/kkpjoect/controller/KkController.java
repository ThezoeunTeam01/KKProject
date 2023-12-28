package com.test.kkpjoect.controller;

import com.test.kkpjoect.dto.KkDTO;
import com.test.kkpjoect.service.KkService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// @Log4j2: Lombok 어노테이션으로 Log4j2를 사용하기 위한 로그 객체를 자동으로 생성합니다.
// 이 로그 객체를 사용하여 클래스 내에서 로깅 작업을 수행할 수 있습니다.

// @RestController: 해당 클래스가 RESTful 웹 서비스의 컨트롤러임을 나타냅니다.
// 이 어노테이션을 사용하면 클래스 내의 메서드들이 HTTP 응답으로 데이터를 반환할 수 있도록 해줍니다.

// @RequestMapping("/api/kk"): 클래스 레벨에서 사용되며, 클래스의 모든 핸들러 메서드에 적용되는 기본 URI를 설정합니다.
// 여기서는 "/api/kk"로 설정되어 있어 이 클래스 내의 모든 메서드의 URI가 "/api/kk/..." 형태로 시작합니다.
@RestController
@RequestMapping("/")
@Log4j2
public class KkController {

    @Autowired
    private KkService service;

    @PostMapping("/create")
    public KkDTO createUser(@RequestBody KkDTO dto){
        log.info("컨트롤러 접속 확인");
        service.userCreate(dto);
        return dto;
    }
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody KkDTO dto, HttpSession session) {
        log.info("로그인 컨트롤러 접속 확인");

        // 로그인 처리
        boolean isValidUser = service.userLogin(dto);

        if (isValidUser) {
            // 세션에 사용자 정보 저장
            session.setAttribute("userId", dto.getUserId());
            session.setAttribute("userName", dto.getUserName());

            // 성공 시 세션 정보 반환
            Map<String, String> sessionInfo = new HashMap<>();
            sessionInfo.put("userId", String.valueOf(dto.getUserId()));
            sessionInfo.put("userName", dto.getUserName());

            return ResponseEntity.ok(sessionInfo);
        } else {
            // 실패 시 에러 응답
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        log.info("로그아웃 컨트롤러 접속 확인");

        // 세션에서 사용자 정보 삭제
        session.removeAttribute("userId");
        session.removeAttribute("userName");

        return "로그아웃 성공";
    }
}
