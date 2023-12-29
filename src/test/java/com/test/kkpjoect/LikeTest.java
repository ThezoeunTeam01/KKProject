package com.test.kkpjoect;

import com.test.kkpjoect.model.KkEntity;
import com.test.kkpjoect.model.LikeEntity;
import com.test.kkpjoect.persistence.LikeRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@Log4j2
@SpringBootTest
public class LikeTest {
    @Autowired
    LikeRepository repository;

    @Test
    public void testCreate(){
        LikeEntity list = new LikeEntity();
        list.setUserId(4);
        list.setMovieId(1111111111);
        repository.save(list);
        log.info("저장된 값 찾기"+repository.findByUserIdAndMovieId(list.getUserId(), list.getMovieId()));
    }
    @Test
    public void testDelete(){
        LikeEntity list = new LikeEntity();
        list.setUserId(4);
        list.setMovieId(1111111111);
        repository.save(list);
        List<LikeEntity> lists = repository.findByUserId(4);
        log.info("저장된 값 확인"+lists);
        LikeEntity deleteList = repository.findByUserIdAndMovieId(list.getUserId(), list.getMovieId()).get();
        repository.delete(deleteList);
        log.info("삭제된 값 확인"+repository.findByUserId(4));
    }
}
