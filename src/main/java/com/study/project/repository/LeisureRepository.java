package com.study.project.repository;

import com.study.project.entity.Leisure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// DB에 접근 인터페이스
@Repository
public interface LeisureRepository extends JpaRepository<Leisure, Integer> {
    List<Leisure> findByLeradContainingAndLecnameContaining(String lerad, String lecname);
}
