package com.jhd.dotime.hashtag.service;


import com.jhd.dotime.hashtag.dto.HashTagRequestDto;
import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.repository.HashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HashTagServiceImpl implements HashTagService{

    @Autowired
    private final HashTagRepository hashTagRepository;

    @Override
    @Transactional
    public List<HashTag> getHashTagList() {
        return hashTagRepository.findAll();
    }

    @Override
    @Transactional
    public Long createHashtag(HashTagRequestDto hashTagRequestDto) {
        return hashTagRepository.save(hashTagRequestDto.toEntity()).getId();

    }
}
