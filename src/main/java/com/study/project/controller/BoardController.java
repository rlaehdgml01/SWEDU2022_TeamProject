package com.study.project.controller;

import com.study.project.dto.MemberDto;
import com.study.project.entity.Board;
import com.study.project.service.BoardService;
import com.study.project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

// 1. 사용자에게 웹을 통해 요청을 받음
// 2. 요청받은 것을 서비스를 통해 전달
// 3. 비즈니스 로직 처리
// 4. 처리된 데이터를 모델을 통해 뷰에 전달
// 5. 뷰를 통해 사용자가 전달 된 것을 확인
// 클라이언트 -> 컨트롤러 -> 서비스 -> DAO(Repository) -> DB -> DAO(Repository) -> 서비스 -> 컨트롤러(모델로 전달) -> 뷰
//               DTO(객체를 담은 클래스)       Entity(객체를 담은 클래스)
@Controller
public class BoardController {

    // new 인스턴스 생성자 역할 @Autowired
    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    // 사용자가 웹을 통해 요청한 주소 localhost:8090/boardList
    @GetMapping("/boardWrite")
    public String boardWrite(HttpSession session, Model model) {

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        return "board/boardWrite";
    }

    @PostMapping("/boardWriteDo")
    public String boardWriteDo(Board board) {

        // System.out.println(board.getTitle());
        // System.out.println(board.getContent());
        boardService.boardWriteDo(board);
        return "redirect:/boardList";
    }

    @GetMapping("/boardList")
    public String boardList(Model model, Board board, String searchKeyword, HttpSession session) {

        List<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList();
        } else {
            list = boardService.boardSearchList(searchKeyword);
        }

        model.addAttribute("list", list);

        // 테스트용 출력
        // System.out.println(list.get(0).getTitle());

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));
        // 로그인 테스트용 출력
        // System.out.println("로그인 세션값: " + session.getAttribute("loginId"));

        return "board/boardList";
    }

    @GetMapping("/boardDetail")
    public String boardDetail(Model model, Integer bid, Board board, HttpSession session) {

        model.addAttribute("board", boardService.boardDetail(bid));
        // System.out.println(board.getId());
        // System.out.println(board.getTitle());
        // System.out.println(board.getContent());

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        return "board/boardDetail";
    }

    @GetMapping("/boardDelete")
    public String boardDelete(Integer bid) {

        boardService.boardDelete(bid);
        return "redirect:/boardList";
    }

    @GetMapping("/boardModify")
    public String boardModify(@RequestParam("bid") Integer bid, Model model, HttpSession session) {

        model.addAttribute("board", boardService.boardDetail(bid));

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        return "board/boardModify";
    }

    @PostMapping("/boardUpdate")
    public String boardUpdate(@RequestParam("bid") Integer bid, Board board) {

        Board boardTemp = boardService.boardDetail(bid);
        boardTemp.setTitle(board.getTitle());
        boardTemp.setContent(board.getContent());

        boardService.boardWriteDo(board);

        return "redirect:/boardList";
    }

    @GetMapping("/test")
    public String test() {

        return "test";
    }
}
