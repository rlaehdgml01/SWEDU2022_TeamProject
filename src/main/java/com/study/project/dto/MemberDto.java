package com.study.project.dto;

import com.study.project.entity.Member;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;

@Data
public class MemberDto {
    private Integer id;
    private String memberId;
    private String memberPassword;
    private String memberName;
    private int memberAge;
    private String memberHp;

    // 엔티티타입을 DTO 타입으로 변환
    public static MemberDto toMemberDto(Member member) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(member.getId());
        memberDto.setMemberId(member.getMemberId());
        memberDto.setMemberPassword(member.getMemberPassword());
        memberDto.setMemberName(member.getMemberName());
        memberDto.setMemberAge(member.getMemberAge());
        memberDto.setMemberHp(member.getMemberHp());

        return memberDto;
    }

}
