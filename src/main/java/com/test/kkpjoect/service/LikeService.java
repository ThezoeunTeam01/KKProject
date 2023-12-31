package com.test.kkpjoect.service;

import com.test.kkpjoect.dto.LikeDTO;
import com.test.kkpjoect.model.LikeEntity;
import com.test.kkpjoect.persistence.LikeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class LikeService {
    private final LikeRepository repository;

    public void LikeCreate(LikeDTO dto){
        LikeEntity list = LikeDTO.toEntity(dto);
        repository.save(list);
    }
    public void LikeDelete(LikeDTO dto){
        LikeEntity list = repository.findByUserIdAndMovieId(dto.getUserId(), dto.getMovieId()).get();
        repository.delete(list);
    }
    public List<LikeEntity> LikeRead(LikeDTO dto) {
        return repository.findByUserId(dto.getUserId());
    }
}
