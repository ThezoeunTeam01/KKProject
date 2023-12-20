package com.test.kkpjoect;

import com.test.kkpjoect.model.KkEntity;
import com.test.kkpjoect.persistence.KkRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@Log4j2
@SpringBootTest
@Transactional //트랙잭션은 전체 성공 아니면 적용이 안됨
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
        user2.setUserName("testuser02");
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
        user2.setPassWord("1234222222223232");

        repository.save(user);
        repository.save(user2);

        List<KkEntity> readTest = repository.findByUserName(user.getUserName());

        for (KkEntity forname : readTest){
            log.info(forname);//이름
        }





    }




}
