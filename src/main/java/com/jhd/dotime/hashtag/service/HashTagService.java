package com.jhd.dotime.hashtag.service;


import com.jhd.dotime.hashtag.entity.HashTag;

import java.util.List;

public interface HashTagService {

    List<HashTag> getHashTagList();
//    Long createHashtag(Long taskId, HashTagRequestDto hashTagRequestDto);

    List<Long> createHashtag(String hashtag);

}
