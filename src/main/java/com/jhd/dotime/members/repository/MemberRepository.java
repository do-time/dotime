package com.jhd.dotime.members.repository;

import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.tasks.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String Email);

    void deleteByEmail(String email);

//    List<Task> findTaskList(Long memberId);
}
