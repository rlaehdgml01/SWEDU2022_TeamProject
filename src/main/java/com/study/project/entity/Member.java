package com.study.project.entity;

import com.study.project.dto.MemberDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@Entity(name="member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 자동 생성
    private Integer id;

    @Column(length = 50)
    private String memberId;

    @Column(length = 20)
    private String memberPassword;

    @Column(length = 20)
    private String memberName;

    @Column(length = 20)
    private int memberAge;

    @Column(length = 30)
    private String memberHp;

    // 서비스에서 Dto를 Entity 타입으로 사용하기 위해 Dto 타입을 Entity 타입으로 변환
    public static Member toSaveEntity(MemberDto memberDto){
        Member member = new Member();
        member.setMemberId(memberDto.getMemberId());
        member.setMemberPassword(memberDto.getMemberPassword());
        member.setMemberName(memberDto.getMemberName());
        member.setMemberAge(memberDto.getMemberAge());
        member.setMemberHp(memberDto.getMemberHp());

        return member;
    }

     /*
    create table leisure.member(
    id int(11) not null auto_increment primary key,
    member_id varchar(45) not null,
    member_password varchar(45) not null,
    member_name varchar(45) not null,
    member_age int(11) not null,
    member_hp varchar(45) not null
    );
     */
}
