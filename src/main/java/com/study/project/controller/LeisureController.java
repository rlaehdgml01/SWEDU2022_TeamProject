package com.study.project.controller;

import com.study.project.dto.MemberDto;
import com.study.project.entity.Address;
import com.study.project.entity.Board;
import com.study.project.entity.Leisure;
import com.study.project.entity.Review;
import com.study.project.service.AddressService;
import com.study.project.service.LeisureService;
import com.study.project.service.MemberService;
import com.study.project.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Collections;

/**
 * 1. 사용자에게 웹을 통해 요청을 받음
 * 2. 요청받은 것을 서비스에게 전달
 * 3. 비즈니스 로직 처리
 * 4. 처리된 데이터를 모델을 통해 뷰에 전달
 * 5. 뷰를 통해 사용자가 요청사항을 확인
 * 클라이언트 -> 컨트롤러 -> 서비스 -> DAO(Repository) -> DB -> DAO(Repository) -> 서비스 -> 컨트롤러(모델로 전달) -> 뷰
           DTO        DTO      Entity            Entity
 */
@Controller
public class LeisureController {

    // new 인스턴스 생성자 역할 @Autowired
    @Autowired
    private LeisureService leisureService;
    @Autowired
    private AddressService addressService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ReviewService reviewService;

    // 사용자가 웹을 통해 요청한 주소 localhost:8090/leisureSearch
    @GetMapping("/leisureSearch")
    public String leisureSearch(Model model, String searchKeyword, HttpSession session, String lerad, String lecname) throws Exception {

        List<Leisure> le_list = null;

        List<Address> ad_list = null; // Address(주소) 객체를 담은 리스트 ad_list

        HashSet<String> ad_hashset = new HashSet<>(); // 주소의 불필요한 중복을 없애기 위해 옮겨 담을 임시 HashSet

        ArrayList<String> new_le_list = new ArrayList<String>();

        String result;

        result = searchKeyword + ' ' + lerad;

        // searchKeyword: address.addo (지역 설정) 객체
        if (searchKeyword == null) { // 만약 객체가 없다면
            // ad_list = 전체 주소 정보를 담은 리스트 저장 => findAll()
            ad_list = addressService.addressList();
        } else {
            // ad_list = 해당 지역의 주소 정보를 담은 리스트 저장 => findByAddo(String searchKeyword)
            ad_list = addressService.addressSearchList(searchKeyword);
        }

        if (lerad == null && lecname == null) { // 만약 값이 없다면
            // ad_list = 전체 주소 정보를 담은 목록 => findAll()
            le_list = leisureService.leisureList();
        } else {
            // ad_list = 해당 지역의 주소 정보를 담은 목록 => findByAddo(String searchKeyword)
            le_list = leisureService.leisureSearch(result, lecname);
        }

        // System.out.println(searchKeyword);
        // System.out.println(ad_list.get(0).getAdsi());

        // 중복을 없애기 위해 ad_hashset에 ad_list 데이터를 저장한다.
        for (int i = 0; i < ad_list.size(); i++) {
            ad_hashset.add(ad_list.get(i).getAdsi());
        }

        // 중복 없애기 작업을 마친 후 다시 새로운 리스트 형태의 new_ad_list에 데이터를 저장한다.
        List<String> new_ad_list = new ArrayList<String>(new HashSet<String>(ad_hashset));
        Collections.sort(new_ad_list); // 리스트 오름차순 정렬 (경산시 경주시 고령군 ...)

        // 테스트용 출력
        // System.out.println("ad_hashset: " + ad_hashset);
        // System.out.println("new_ad_list: " + new_ad_list);

        // 데이터 값을 각각의 모델에 담아서 view에 전달한다.
        model.addAttribute("ad_list", new_ad_list);
        model.addAttribute("le_list", le_list);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("lerad", lerad);
        model.addAttribute("lecname", lecname);

        for (int i = 0; i < le_list.size(); i++) {
            new_le_list.add(le_list.get(i).getLebname());
        }
        model.addAttribute("new_le_list", new_le_list);

        // System.out.println("전체 주소: " + result);
        // System.out.println("도: " + searchKeyword);
        // System.out.println("시군구: " + lerad);
        // System.out.println("종류: " + lecname);
        // System.out.println("주소풀네임: " + le_list.get(0).getLebname());

        // 로그인 정보 확인
        // 로그인 성공 시 => 네비게이션 바에 로그아웃 메뉴 활성화
        // 비로그인 시 => 로그인/회원가입 메뉴 활성화
        session.getAttribute("loginId");
        model.addAttribute("loginId", session.getAttribute("loginId"));


        return "leisure/leisureSearch"; // <= leisureSearch.html
    }

    @GetMapping("/leisureTest")
    public String leisureTest(Model model, String lerad, String lecname) {

        List<Leisure> le_list = null;

        // searchKeyword: address.addo (지역 설정) 객체
        if(lerad == null && lecname == null) { // 만약 객체가 없다면
            // ad_list = 전체 주소 정보를 담은 목록 => findAll()
            le_list = leisureService.leisureList();
        } else {
            // ad_list = 해당 지역의 주소 정보를 담은 목록 => findByAddo(String searchKeyword)
            le_list = leisureService.leisureSearch(lerad, lecname);
        }

        model.addAttribute("le_list", le_list);
        model.addAttribute("lemad", lerad);
        model.addAttribute("lecname", lecname);

        /*System.out.println(lemad);
        System.out.println(lecname);
        System.out.println(le_list.get(0).getLemad());*/

        return "leisure/leisureTest";
    }

    @GetMapping("/leisureDetail")
    public String leisureDetail(Model model, Integer id, String rebname) {

        List<Review> re_list = null;

        rebname = leisureService.leisureDetail(id).getLebname();
        if(rebname == null) {
            re_list = reviewService.reviewList(); // findAll(), (selectList select * from review)
        } else {
            re_list = reviewService.reviewListDetail(rebname);
        }
        model.addAttribute("leisure", leisureService.leisureDetail(id));
        model.addAttribute("re_list", re_list);
        // System.out.println(board.getId());
        // System.out.println(board.getTitle());
        // System.out.println(board.getContent());

        // 리뷰 평균 구하기
        int sum = 0;
        double avg = 0.0;

        for(int i = 0; i < re_list.size(); i++) {
            sum = sum + re_list.get(i).getRegrade();
        }

        if(re_list.size() == 0) {
            avg = 0;
        } else {
            avg = (double) sum / (double) re_list.size();
        }

        model.addAttribute("avg", Math.round(avg * 10) / 10.0);

//        System.out.println("평균: " + avg);
//        System.out.println("사이즈: " + re_list.size());
//        System.out.println("썸: " + sum);

        return "leisure/leisureDetail";
    }

}
