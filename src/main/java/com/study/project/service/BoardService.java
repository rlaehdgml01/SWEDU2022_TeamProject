package com.study.project.service;

import com.study.project.entity.Board;
import com.study.project.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    // 인스턴스(객체) 생성을 간단하게 해주는 @Autowired
    // BoardRepository boardRepository = new BoardRepository();
    @Autowired
    private BoardRepository boardRepository;

    // 글 작성 저장
    public void boardWriteDo(Board board) {
        boardRepository.save(board);
    }

    // 글 목록 출력
    public List<Board> boardList() {
        return boardRepository.findAll();
    }

    // 상세 페이지
    public Board boardDetail(Integer bid) {
        return boardRepository.findById(bid).get();
    }

    // 글 삭제
    public void boardDelete(Integer bid) {
        boardRepository.deleteById(bid);
    }

    // 검색 처리
    public List<Board> boardSearchList(String searchKeyword) {
        return boardRepository.findByTitleContaining(searchKeyword);
    }

}
