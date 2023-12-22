package com.test.kkpjoect.dto;

import com.test.kkpjoect.model.KkEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KkDTO {
    private int userId;
    private String userName;
    private String passWord;

    public KkDTO(final KkEntity entity){
        this.userId = entity.getUserId();
        this.userName = entity.getUserName();
        this.passWord = entity.getPassWord();
    }

    public KkDTO(final KkDTO dto){
        this.userId = dto.getUserId();
        this.userName = dto.getUserName();
        this.passWord = dto.getPassWord();
    }

    public static KkEntity toEntity(final KkDTO dto){
        return KkEntity.builder()
                .userName(dto.getUserName())
                .passWord(dto.getPassWord())
                .build();
    }
}
