package com.study.project.repository;

import com.study.project.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    // select * from member_table where memberId = ? 이런 쿼리를 적용 시키려한다.
    // 리턴타입: Member (Entity)
    // 매개변수: memberId(String 타입)
    // Optional: 사용자가 계정을 잘못 넣었을 경우를 방지함
    Optional<Member> findByMemberId(String memberId);
}
