package com.jhd.dotime.hashtag.entity;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class HashTagTest {
    @Test
    public  void equalsTest(){
        //given
        Map<HashTag, String> map= new HashMap<>();
        List<HashTag> list = new ArrayList<>();
        HashTag hashTag1 = new HashTag(1L, "해시코드");
        HashTag hashTag2 = new HashTag(1L, "해시코드");

        //when
        map.put(hashTag1, "hashTag");
        map.put(hashTag2, "HashTag");
        list.add(hashTag1);
        list.add(hashTag2);

        //then
        assertAll(
                () -> assertTrue(hashTag1.equals(hashTag2)),
                () -> assertTrue(map.size() == 1),
                () -> assertTrue(list.contains(hashTag1)),
                () -> assertTrue(list.contains(hashTag2))
        );
    }
}