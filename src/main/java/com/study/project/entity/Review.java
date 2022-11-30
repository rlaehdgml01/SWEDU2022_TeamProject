package com.study.project.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "review")
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reid;

    @Column(name = "retitle")
    private String retitle; // 리뷰 제목

    @Column(name = "recontent")
    private String recontent; // 리뷰 내용

    @Column(name = "reauthor")
    private String reauthor; // 작성자

    @Column(name = "redate")
    private String redate; // 리뷰 생성 날짜

    @Column(name = "regrade")
    private Integer regrade; // 리뷰 평점

    @Column(name = "rebname")
    private String rebname; // 사업자 명

    private String refilename;

    private String refilepath;

    /*
    create table leisure.review(
    reid int not null auto_increment primary key,
    retitle varchar(45),
    recontent varchar(100) not null,
    redate timestamp not null,
    regrade int(11) not null,
    refilename varchar(150),
    refilepath varchar(300),
    reauthor text not null,
    rebname varchar(45) not null
    );
     */
}
