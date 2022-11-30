package com.study.project.service;

import com.study.project.dto.MemberDto;
import com.study.project.entity.Member;
import com.study.project.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    // 회원가입 저장
    public Integer memberSignupSave(MemberDto memberDto) {

        Member member = Member.toSaveEntity(memberDto);
        memberRepository.save(member);
        //이 2줄을 1줄로 표현한게 아래 코드
        // memberRepository.save(Member.toSaveEntity(memberDto));

        Integer savedId = memberRepository.save(member).getId(); // 반환값으로 넘겨줄 기본키
        // String name = memberRepository.save(member).getMemberName(); // 이름을 반환해주려면 이렇게

        return savedId;
    }

    /**
     * 로그인페이지에서 입력받은 아이디와, 비밀번호를 받아오고
     * DB로부터 해당 아이디의 정보를 가져와서
     * 입력받은 비밀번호와 DB에서 조회한 비밀번호의 일치여부를 판단하고
     * 일치하면 로그인 성공, 일치하지 않으면 로그인 실패로 처리
     */
    public MemberDto memberLoginDo(MemberDto memberDto) {
        Optional<Member> optionalMember = memberRepository.findByMemberId(memberDto.getMemberId());
        if (optionalMember.isPresent()) {
            Member loginEntity = optionalMember.get();
            if (loginEntity.getMemberPassword().equals(memberDto.getMemberPassword())) { // 로그인 성공
                return MemberDto.toMemberDto(loginEntity);
            } else { // 계정은 맞지만 비밀번호가 틀림
                return null;
            }
        } else { // 해당 계정이 없을 때
            return null;
        }
    }
}
