package com.jhd.dotime.hashtag.service;


import com.jhd.dotime.hashtag.dto.HashTagRequestDto;
import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.repository.HashTagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface HashTagService {

    List<HashTag> getHashTagList();
    Long createHashtag(Long taskId, HashTagRequestDto hashTagRequestDto);

}
