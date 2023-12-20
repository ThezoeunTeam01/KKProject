package com.test.kkpjoect.persistence;

import com.test.kkpjoect.model.KkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KkRepository extends JpaRepository<KkEntity, Long> {

    // userName을 기준으로 사용자를 조회하는 메소드
    List<KkEntity> findByUserName(String userName);
    Optional<KkEntity> findByUserId(Long userId);

    Optional<KkEntity> findByPassWord(String password);

}