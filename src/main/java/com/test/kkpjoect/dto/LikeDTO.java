package com.test.kkpjoect.dto;

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
}
