package com.study.project.controller;

import com.study.project.entity.Leisure;
import com.study.project.entity.Review;
import com.study.project.service.LeisureService;
import com.study.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Controller
public class ReviewController {
    Integer leId = 0;
    Integer reId = 0;
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private LeisureService leisureService;

    @GetMapping("/reviewWrite")
    public String reviewWrite(HttpSession session, Model model, Leisure leisure, Integer id) {

        model.addAttribute("leisure", leisureService.leisureDetail(id));

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        leId = leisureService.leisureDetail(id).getLeid();
        // System.out.println(leId);

        return "review/reviewWrite";
    }

    @PostMapping("/reviewWriteDo")
    public String reviewWriteDo(Review review, MultipartFile file, Integer id) throws Exception {

        // System.out.println(board.getTitle());
        // System.out.println(board.getContent());
        reviewService.reviewWriteDo(review, file);
        // System.out.println("정수" + leId);


        return "redirect:/leisureDetail?id=" + leId;
    }

    @GetMapping("/reviewList")
    public String reviewList(Model model, HttpSession session, String rebname) {

        List<Review> re_list = null;

        if(rebname == null) {
            re_list = reviewService.reviewList();
        } else {
            re_list = reviewService.reviewListDetail(rebname);
        }

        model.addAttribute("re_list", re_list);

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));
        // 로그인 테스트용 출력
        // System.out.println("로그인 세션값: " + session.getAttribute("loginId"));

        return "review/reviewList";
    }

    @GetMapping("/reviewDetail")
    public String reviewDetail(Model model, Integer id, Review review, HttpSession session) {

        model.addAttribute("review", reviewService.reviewDetail(id));
        // System.out.println(board.getId());
        // System.out.println(board.getTitle());
        // System.out.println(board.getContent());

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        return "review/reviewDetail";
    }

    @GetMapping("/reviewDelete")
    public String reviewDelete(Integer id) {

        reviewService.reviewDelete(id);
        return "redirect:/reviewList";
    }

    @GetMapping("/reviewModify")
    public String reviewModify(@RequestParam("id") Integer id, Model model, HttpSession session) {

        model.addAttribute("review", reviewService.reviewDetail(id));

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        reId = reviewService.reviewDetail(id).getReid();

        return "review/reviewModify";
    }

    @PostMapping("/reviewUpdate")
    public String reviewUpdate(@RequestParam("reid") Integer reid, Review review, MultipartFile file) throws Exception {

        Review reviewTemp = reviewService.reviewDetail(reid);
        reviewTemp.setRetitle(review.getRetitle());
        reviewTemp.setRecontent(review.getRecontent());
        reviewTemp.setReauthor(review.getReauthor());
        reviewTemp.setRebname(review.getRebname());
        reviewTemp.setRedate(review.getRedate());
        reviewTemp.setRefilename(review.getRefilename());
        reviewTemp.setRefilepath(review.getRefilepath());

        reviewService.reviewUpdateDo(review, file);

        return "redirect:/reviewDetail?id=" + reId;
    }

    @GetMapping("/reviewListDetail")
    public String reviewListDetail(Integer id, Model model, HttpSession session, String rebname) {

        List<Review> re_list = null;

        rebname = leisureService.leisureDetail(id).getLebname();
        if(rebname == null) {
            re_list = reviewService.reviewList();
        } else {
            re_list = reviewService.reviewListDetail(rebname);
        }

        model.addAttribute("leisure", leisureService.leisureDetail(id));
        model.addAttribute("re_list", re_list);

        //System.out.println(leisureService.leisureDetail(id).getLebname());

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        return "review/reviewListDetail";
    }
}
