package com.test.kkpjoect;

import com.test.kkpjoect.model.KkEntity;
import com.test.kkpjoect.persistence.KkRepository;
//import jakarta.transaction.Transactional;
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
        Optional<KkEntity> optionalKkEntity = repository.findByUserId(6);

        if (optionalKkEntity.isPresent()) {
            KkEntity killer = optionalKkEntity.get();
            repository.delete(killer);
            log.info("삭제된 유저 정보: " + killer);
        } else {
            log.info("해당 유저를 찾을 수 없습니다.");
        }
    }



}
