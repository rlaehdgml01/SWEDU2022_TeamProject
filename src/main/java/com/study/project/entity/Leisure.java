package com.study.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.Date;

@Entity(name="leisure") // 테이블 명
@Getter
@Setter
public class Leisure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
    private Integer leid; // 기본 키

    @Column(name = "lemad", nullable = false, length = 45)
    private String lemad; // 소재지주소

    @Column(name = "lerad", nullable = false, length = 45)
    private String lerad; // 도로명주소

    @Column(name = "lezc", nullable = false, length = 45)
    private String lezc; // 우편번호

    @Column(name = "lebname", nullable = false, length = 45)
    private String lebname; // 사업자명

    @Column(name = "lecname", nullable = false, length = 45)
    private String lecname; // 종류

}
