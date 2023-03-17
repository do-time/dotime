package com.jhd.dotime.hashtag.repository;

import com.jhd.dotime.hashtag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    HashTag findByName(String name);
}
