package com.test.kkpjoect;

import com.test.kkpjoect.dto.KkDTO;
import com.test.kkpjoect.model.KkEntity;
import com.test.kkpjoect.persistence.KkRepository;
//import jakarta.transaction.Transactional;
import com.test.kkpjoect.service.KkService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;
import java.util.Optional;

@Log4j2
@SpringBootTest
//@Transactional //트랙잭션은 전체 성공 아니면 적용이 안됨
public class KkTest {

    @Autowired
    private KkRepository repository;

    @Autowired
    private KkService service;
    @Test
    public void  testCreate(){
        //Create(seve)
        KkEntity user = new KkEntity();
        user.setUserName("testuser01");
        user.setPassWord("1234");

        KkEntity user2 = new KkEntity();
        user2.setUserName("testuser0222");
        user2.setPassWord("1234");

        log.info("유저1 값 : "+user);
        log.info("유저2 값 : "+user2);

        repository.save(user);
        repository.save(user2);

        List<KkEntity> saveUser = repository.findByUserName(user2.getUserName());
        log.info("레포지토리 : "+saveUser);
    }
    @Test
    public void testRead(){
        //Create(seve)
        KkEntity user = new KkEntity();
        user.setUserName("좀 제발 되라고");
        user.setPassWord("123422");
        KkEntity user2 = new KkEntity();
        user2.setUserName("좀 제발 되라고");
        user2.setPassWord("12342");

        repository.save(user);
        repository.save(user2);

        List<KkEntity> readTest = repository.findByUserName(user.getUserName());

        for (KkEntity forname : readTest){
            log.info(forname);//이름
        }

    }
    @Test
    public void testUpdate() {
//        //변경할 행을 선택
//        Optional<KkEntity> seleteUsers = repository.findByUserId(9);
//        KkEntity seleteUser  = seleteUsers.get();
//        log.info("셀렉트 유저 : "+seleteUser);
//        //선택된 요소를 변경
//        seleteUser.setUserName("업데이트 진행 완료");
//        //변경된 요소를 저장
//        repository.save(seleteUser);
//        //변경된 요소 확인
//        Optional<KkEntity> testUpdate = repository.findByUserId(9);
//        log.info("업데이트 완료된 요소 확인"+testUpdate);

        // 사용자 생성 및 저장
        KkEntity user = new KkEntity();
        user.setUserName("업데이트 전");
        user.setPassWord("123422");
        repository.save(user);

        // 사용자 이름으로 사용자 조회
        List<KkEntity> userList = repository.findByUserName(user.getUserName());
        // List<KkEntity> userList = repository.findByPassWord("123422");
        log.info("업데이트 전 사용자 정보: " + userList);

        if (!userList.isEmpty()) {
            // 사용자의 비밀번호 업데이트
            KkEntity foundUser = userList.get(0);
            foundUser.setUserName("업데이트 후");

            // 업데이트된 사용자 엔터티 저장 - 없어도 저장은 됨
            repository.save(foundUser);

            // Optional: 업데이트된 사용자를 검증하기 위해 검색하고 로그에 기록
            KkEntity updatedUser = repository.findById(foundUser.getUserId()).orElse(null);
            log.info("업데이트된 사용자 정보: " + updatedUser);
            log.info("유저 리스트 : " + userList);
        } else {
            log.error("해당 사용자를 찾을 수 없습니다.");
        }
    }
    @Test
    public void testDelete() {
        // 삭제할 요소 확인
        Optional<KkEntity> optionalKkEntity = repository.findByUserId(6);
        //요소 검증에 따른 실행
        if (optionalKkEntity.isPresent()) {
            //삭제할 요소 선택
            KkEntity killer = optionalKkEntity.get();
            //삭제할 요소 로그 확인
            log.info("삭제된 유저 정보: " + killer);
            //테이블에서 삭제 진행
            repository.delete(killer);
        } else {
            log.info("해당 유저를 찾을 수 없습니다.");
        }
    }



    ////////서비스 테스트

    @Test
    public void testServiceCreate(){
        log.info("테스트 크리에이트 시작");

        KkDTO user =  KkDTO.builder()
                .userName("테스트 시작")
                .passWord("테스트 패스워드")
                .build();

        log.info("DTO에 테스트 유저 생성 : "+user);
        log.info("서비스 살행 전");
        KkDTO returnUser = service.userCreate(user);
        log.info("서비스에서 리턴 받은 DTO 값 확인"+returnUser);
        log.info("내가 만든 DTO값 확인"+user);
        log.info("테스트 크리에이트 종료");

        if(user.getUserName() == returnUser.getUserName()){
            log.info("생성한 유저와 저장된 유저가 같습니다.");
        }else {
            log.info("생성한 유저와 저장된 유저가 다릅니다.");

        }

    }
    @Test
    public void testServiceRead(){
        // 폼 혹은 앞에서 받아온 값
        KkDTO readUser = KkDTO.builder()
                .passWord("테스트 패스워드")
                .build();
        //서비스 안에 메서드를 실행(DTO 값을 담아서 넘김) or 리턴 값을 받기위한 객체 생성
        List<KkEntity> readTable = service.userRead(readUser);
        //반환된 값 확인
        log.info("검색한 결과 값 : "+readTable);
    }
    @Test
    public void testServiceUpdate(){

        KkDTO user =KkDTO.builder()
                .userId(1)
                .build();

        KkEntity updateUser =  service.userUpdate(user);

        log.info("아 스트링 : "+updateUser);
    }

    @Test
    public void testServiceKillng(){
        KkDTO user = KkDTO.builder()
                .userId(9)
                .build();
        List<KkEntity> allUsers = service.userDelete(user);
        log.info("남은 유저 확인 : "+allUsers);

    }






}
