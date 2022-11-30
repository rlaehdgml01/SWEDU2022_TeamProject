package com.study.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name="board") // 테이블 명
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
    private Integer bid;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "content", nullable = false, length = 150)
    private String content;

    @Column(name = "date")
    private String date; // 리뷰 생성 날짜

}
