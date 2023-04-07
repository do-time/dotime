package com.jhd.dotime.hashtag.service;


import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.repository.HashTagRepository;
import com.jhd.dotime.hashtag.repository.TaskTagRepository;
import com.jhd.dotime.tasks.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class HashTagServiceImpl implements HashTagService {

    private final TaskRepository taskRepository;

    private final TaskTagRepository taskTagRepository;

    private final HashTagRepository hashTagRepository;

    @Override
    @Transactional
    public List<HashTag> getHashTagList() {
        return hashTagRepository.findAll();
    }

    @Override
    @Transactional
    public List<Long> createHashtag(String hashtag) {
        List<Long> hashtagIdLst = new ArrayList<>();
        String pat = "#(\\S+)";
        Pattern pattern = Pattern.compile(pat);
        Matcher res = pattern.matcher(hashtag);

        while(res.find()){
//            System.out.println("res.group() = " + res.group(1));
            HashTag newTag = HashTag.builder().name(res.group(1)).build();
            HashTag hashTag = hashTagRepository.findByName(res.group(1));
            if (hashTag == null){
                hashTagRepository.save(newTag);
                hashtagIdLst.add(newTag.getId());
            }else
                hashtagIdLst.add(hashTag.getId());

        }

        return hashtagIdLst;
    }


}
