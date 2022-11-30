package com.study.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="address") // 테이블 명
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
    private Integer adid; // 기본 키

    @Column(name = "addo", nullable = false, length = 45)
    private String addo; // 특별시, 광역시, 도

    @Column(name = "adsi", nullable = false, length = 45)
    private String adsi; // 시, 군, 구

    @Column(name = "addong", nullable = false, length = 45)
    private String addong; // 동

    @Column(name = "adlat", nullable = false, length = 45)
    private String adlat; // 위도

    @Column(name = "adlng", nullable = false, length = 45)
    private String adlng; // 경도
}
