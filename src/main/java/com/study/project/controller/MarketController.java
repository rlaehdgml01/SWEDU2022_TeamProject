package com.study.project.controller;

import com.study.project.entity.Market;
import com.study.project.service.LeisureService;
import com.study.project.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MarketController {
    @Autowired
    private MarketService marketService;

    @Autowired
    private LeisureService leisureService;

    @GetMapping("/marketWrite")
    public String marketWrite(HttpSession session, Model model) {

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        return "market/marketWrite";
    }

    @PostMapping("/marketWriteDo")
    public String marketWriteDo(Market market, MultipartFile file) throws Exception {

        // System.out.println(board.getTitle());
        // System.out.println(board.getContent());
        marketService.marketWriteDo(market, file);
        return "redirect:/marketList";
    }

    @GetMapping("/marketList")
    public String marketList(Model model, Market market, HttpSession session) {

        List<Market> ma_list = marketService.marketList();

        model.addAttribute("ma_list", ma_list);

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));
        // 로그인 테스트용 출력
        // System.out.println("로그인 세션값: " + session.getAttribute("loginId"));

        return "market/marketList";
    }

    @GetMapping("/marketDetail")
    public String marketDetail(Model model, Integer id, Market market, HttpSession session) {

        model.addAttribute("market", marketService.marketDetail(id));
        // System.out.println(board.getId());
        // System.out.println(board.getTitle());
        // System.out.println(board.getContent());

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        return "market/marketDetail";
    }

    @GetMapping("/marketDelete")
    public String marketDelete(Integer id) {

        marketService.marketDelete(id);
        return "redirect:/marketList";
    }

    @GetMapping("/marketModify")
    public String marketModify(@RequestParam("id") Integer id, Model model, HttpSession session) {

        model.addAttribute("market", marketService.marketDetail(id));

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));

        return "market/marketModify";
    }

    @PostMapping("/marketUpdate")
    public String marketUpdate(@RequestParam("maid") Integer maid, Market market, MultipartFile file) throws Exception {

        Market marketTemp = marketService.marketDetail(maid);
        marketTemp.setMatitle(market.getMatitle());
        marketTemp.setMacontent(market.getMacontent());
        marketTemp.setMadate(market.getMadate());
        marketTemp.setMacateg(market.getMacateg());
        marketTemp.setMafilepath(market.getMafilepath());
        marketTemp.setMafilename(market.getMafilename());

        marketService.marketUpdateDo(market, file);

        return "redirect:/marketList";
    }

}