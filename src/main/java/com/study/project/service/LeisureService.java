package com.study.project.service;

import com.study.project.entity.Board;
import com.study.project.entity.Leisure;
import com.study.project.repository.LeisureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeisureService {
    @Autowired
    private LeisureRepository leisureRepository;

    // 전체 데이터를 조회하기 위한 메소드
    public List<Leisure> leisureList() {

        return leisureRepository.findAll();
    }

    // 검색 리스트
    public List<Leisure> leisureSearch(String lerad, String lecname) {
        return leisureRepository.findByLeradContainingAndLecnameContaining(lerad, lecname);
    }

    // 상세 페이지
    public Leisure leisureDetail(Integer id) {
        return leisureRepository.findById(id).get();
    }


}
