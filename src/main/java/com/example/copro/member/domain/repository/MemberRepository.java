package com.example.copro.member.domain.repository;

import com.example.copro.member.domain.Member;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

    Optional<Member> findByMemberName(String memberName);

    Optional<Member> findByNickName(String nickName);

    Page<Member> findByMemberId(Long memberId, Pageable pageable);

    boolean existsByNickName(String nickName);
}
