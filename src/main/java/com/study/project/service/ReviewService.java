package com.study.project.service;

import com.study.project.entity.Board;
import com.study.project.entity.Review;
import com.study.project.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    // 글 작성 저장
    public void reviewWriteDo(Review review, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        review.setRefilename(fileName);
        review.setRefilepath("/files/"+ fileName);

        reviewRepository.save(review);
    }

    // 글 목록 출력
    public List<Review> reviewList() {
        return reviewRepository.findAll();
    }

    // 상세 페이지
    public Review reviewDetail(Integer id) {
        return reviewRepository.findById(id).get();
    }

    // 글 삭제
    public void reviewDelete(Integer id) {
        reviewRepository.deleteById(id);
    }

    // 사업자명에 해당하는 상세 리뷰 목록 찾기
    public List<Review> reviewListDetail(String rebname) {
        return reviewRepository.findByRebnameContaining(rebname);
    }

    // 수정 처리
    public void reviewUpdateDo(Review review, MultipartFile file) throws Exception {
        String projectPath = System.getProperty("user.dir") + "/src/main/resources/static/files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectPath, fileName);

        file.transferTo(saveFile);

        review.setRefilename(fileName);
        review.setRefilepath("/files/"+ fileName);

        reviewRepository.save(review);
    }


}
