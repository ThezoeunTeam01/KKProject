package com.test.kkpjoect.dto;

import com.test.kkpjoect.model.KkEntity;
import com.test.kkpjoect.model.LikeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {
    private int id;
    private int userId;
    private int movieId;

    public LikeDTO(final LikeEntity entity){
        this.id = entity.getId();
        this.userId = entity.getUserId();
        this.movieId = entity.getMovieId();
    }
    public LikeDTO(final LikeDTO dto){
        this.id = dto.getId();
        this.userId = dto.getUserId();
        this.movieId = dto.getMovieId();
    }
    public static LikeEntity toEntity(final LikeDTO dto){
        return LikeEntity.builder()
                .userId(dto.getUserId())
                .movieId(dto.getMovieId())
                .build();
    }
}
