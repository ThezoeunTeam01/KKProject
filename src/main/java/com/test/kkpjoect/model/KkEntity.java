package com.test.kkpjoect.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Entity  // JPA 엔터티 클래스임을 나타내는 어노테이션
@Data  // 롬복 어노테이션으로, @Getter, @Setter, @EqualsAndHashCode, @ToString, @RequiredArgsConstructor 등을 한꺼번에 생성해주는 어노테이션
@AllArgsConstructor  // 롬복 어노테이션으로, 모든 필드를 파라미터로 받는 생성자를 자동으로 생성해주는 어노테이션
@NoArgsConstructor  // 롬복 어노테이션으로, 파라미터가 없는 기본 생성자를 자동으로 생성해주는 어노테이션
@Table(
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_userName", columnNames = "userName")
        }
        )  // 데이터베이스 테이블과 매핑됨을 나타내는 어노테이션
public class KkEntity {

    @Id  // 엔터티의 기본 키(primary key)를 나타내는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 자동으로 값이 증가하는 전략을 사용하는 경우를 지정하는 어노테이션
    private int userId; //

    private String userName;  // 사용자 이름을 저장하는 필드
    private String passWord;  // 비밀번호를 저장하는 필드
}
