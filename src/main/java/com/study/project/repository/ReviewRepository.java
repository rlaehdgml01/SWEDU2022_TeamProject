package com.study.project.repository;

import com.study.project.entity.Board;
import com.study.project.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByRebnameContaining(String rebname);
}
