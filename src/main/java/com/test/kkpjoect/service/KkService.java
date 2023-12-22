package com.test.kkpjoect.service;

import com.test.kkpjoect.dto.KkDTO;
import com.test.kkpjoect.model.KkEntity;
import com.test.kkpjoect.persistence.KkRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Log4j2
@Service
public class KkService {

    @Autowired
    private KkRepository repository;

    public KkDTO userCreate(KkDTO dto){
        log.info("서비스 크리에이트 시작");
        //받은 DTO 값을 엔티티에 저장(ex/ setUserName(dto.getUserName 같은것들)
        KkEntity user = KkDTO.toEntity(dto);
        log.info("받은 DTO를 엔티티에 저장한 값 "+ user);
        // 저장된 엔티티를 레포지토리에 저장
        KkEntity testSave = repository.save(user);
        log.info("엔티티를 레포지토리에 저장한 값"+testSave);
        // 저장된 엔티티를 찾음
        List<KkEntity> saveDate = repository.findByUserName(user.getUserName());
        log.info("저장된 엔티티를 검색한 값"+saveDate);
        // 찾은 레포지토리 값을 엔티티로 지정함
        KkEntity saveUser = saveDate.get(0);
        log.info("검색한 값 중 첫번쨰의 값을 지정한 결과 : "+saveUser);
        //지정한 엔티티의 값을 DTO에 저장
        KkDTO saveDTO = new KkDTO(saveUser);
        log.info("지정한 결과 값을 DTO로 반환하기 위해 저장한 값 "+saveDTO);
        //저장한 DTO의 값을 리턴함
        return saveDTO;
    }
    public KkDTO userRead(KkDTO dto){
        //넘겨 받은 DTO의 유저네임을 이용해서 테이블의 유저 네임을 찾음
        List<KkEntity> readUser = repository.findByUserName(dto.getUserName());
        //찾은 결과 값중 첫번째 값을 지정함
        KkEntity user = readUser.get(0);
        //지정한 엔티티의 값을 DTO에 저장되도록 변환함(변환 메소드 확인은 DTO안에 있음)
        KkDTO userSelect = new KkDTO(user);
        //저장된 DTO의 값을 리턴함
        return userSelect;
    }


}
