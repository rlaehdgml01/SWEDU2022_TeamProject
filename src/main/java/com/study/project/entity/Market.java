package com.study.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Market {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maid;

    @Column(name = "matitle", nullable = false, length = 45)
    private String matitle; // 마켓 제목

    @Column(name = "macontent", nullable = false, length = 100)
    private String macontent; // 마켓 내용

    @Column(name = "madate")
    private String madate; // 마켓 생성 날짜

    @Column(name = "maauthor")
    private String maauthor; // 마켓 작성자

    @Column(name = "macateg")
    private String macateg; // 마켓 작성자

    private String mafilename;

    private String mafilepath;

    /*
    create table leisure.market(
    maid int not null auto_increment primary key,
    matitle varchar(50) not null,
    macontent text not null,
    madate timestamp(6) not null,
    maauthor varchar(50) not null,
    macateg text not null,
    mafilename varchar(150),
    mafilepath varchar(300)
    );
     */
}