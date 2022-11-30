package com.study.project.controller;

import com.study.project.dto.MemberDto;
import com.study.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
    @Autowired
    private MemberService memberService;

    // 로그인 페이지
    @GetMapping("/memberLogin")
    public String member() {
        return "member/memberLogin";
    }

    // 회원가입 페이지
    @GetMapping("/memberSignup")
    public String memberLoginDo() {
        return "member/memberSignup";
    }

    // 로그인 시도
    @PostMapping("/memberLoginDo")
    public String memberLoginDo(@ModelAttribute MemberDto memberDto, HttpSession session, Model model) {
        MemberDto loginResult = memberService.memberLoginDo(memberDto);
        if(loginResult != null) {
            session.setAttribute("loginId", loginResult.getMemberId());
            session.setAttribute("id", loginResult.getId());
            // 테스트용 출력
            // System.out.println(loginResult.getMemberId());
            return "redirect:/leisureSearch"; // 성공 시 메인 페이지
        } else {
            return "member/memberLogin"; // 실패 시 로그인 페이지
        }
    }

    // 회원 등록
    @PostMapping("/memberSignupSave")
    public String memberSignupSave(@ModelAttribute MemberDto memberDto) {
        memberService.memberSignupSave(memberDto);
        return "member/memberLogin";
    }

}
