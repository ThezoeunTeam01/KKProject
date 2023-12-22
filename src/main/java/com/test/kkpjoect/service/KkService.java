package com.test.kkpjoect.service;

import com.test.kkpjoect.dto.KkDTO;
import com.test.kkpjoect.model.KkEntity;
import com.test.kkpjoect.persistence.KkRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class KkService {

    @Autowired
    private KkRepository repository;

    public KkDTO userCreate(KkDTO dto){
        log.info("서비스 크리에이트 시작");
        KkDTO newDTO = new KkDTO(dto);
        newDTO.setUserName(newDTO.getUserName()+"_forKakao");
        //받은 DTO 값을 엔티티에 저장(ex/ setUserName(dto.getUserName 같은것들)
        KkEntity user = KkDTO.toEntity(newDTO);
        log.info("받은 DTO를 엔티티에 저장한 값 "+ user);
        // 저장된 엔티티를 레포지토리에 저장
        KkEntity testSave = repository.save(user);
        log.info("엔티티를 레포지토리에 저장한 값"+testSave);
        // 저장된 엔티티를 찾음
        List<KkEntity> saveDate = repository.findByUserName(user.getUserName());
        log.info("저장된 엔티티를 검색한 값"+saveDate);
        // 찾은 레포지토리 값을 엔티티로 지정함
        KkEntity saveUser = saveDate.get(0);
//        saveUser.setUserName("이름 바꿨찌롱!!");
//        repository.save(saveUser);
        log.info("검색한 값 중 첫번쨰의 값을 지정한 결과 : "+saveUser);
        //지정한 엔티티의 값을 DTO에 저장
        KkDTO saveDTO = new KkDTO(saveUser);
        log.info("지정한 결과 값을 DTO로 반환하기 위해 저장한 값 "+saveDTO);
        //저장한 DTO의 값을 리턴함
        return saveDTO;
    }
    public List<KkEntity> userRead(KkDTO dto){
        //컨트롤러에서 받아온 DTO를 이용하여(패스워드) 테이블 내에서 검색한 결과 값 or 그걸 담을 객체를 생성함
      List<KkEntity> readUser =  repository.findByPassWord(dto.getPassWord());
        //담은 객체를 컨트롤러로 반환
      return readUser;
    }

    public KkEntity userUpdate(KkDTO dto){
        Optional<KkEntity> updateUsers = repository.findByUserId(dto.getUserId());
        KkEntity updateUser = updateUsers.get();
        updateUser.setUserName("아 왤캐 꼽줘!!!");
        repository.save(updateUser);

        Optional<KkEntity> updatedUsers = repository.findByUserId(updateUser.getUserId());
        KkEntity updatedUser = updateUsers.get();

        return updatedUser;
    }

    public List<KkEntity> userDelete(KkDTO dto){
        Optional<KkEntity> deleteUsers  = repository.findByUserId(dto.getUserId());
        KkEntity deleteUser = deleteUsers.get();
        repository.delete(deleteUser);

        List<KkEntity> users = repository.findAllBy();
        return users;
    }

}
