package com.jhd.dotime.members.repository;

import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.entity.Task;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @EntityGraph(attributePaths = "authorities") // 엔티티그래프 통해 EAGER로 가져온다
    Optional<Member> findByEmail(String Email);

    void deleteByEmail(String email);

    boolean existsByEmail(String email);

//    @EntityGraph(attributePaths = "authorities") // 엔티티그래프 통해 EAGER로 가져온다
//    Optional<Member>findOneOfAuthorityByUsername(String username);

//    List<Task> findTaskList(Long memberId);
}
