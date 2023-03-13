package com.jhd.dotime.auth.repository;

import com.jhd.dotime.auth.entity.Authority;
import com.jhd.dotime.members.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
