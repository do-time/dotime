package com.jhd.dotime.tasktime.service;

import com.jhd.dotime.tasktime.dto.AllocationTimeDto;

import java.util.List;

public interface AllocationTimeService {

    void insertAllocationTime(AllocationTimeDto.Request requestDto);

    void updateAllocationTime(AllocationTimeDto.Request requestDto);

    List<AllocationTimeDto.Response> getAllocationTime(Long taskId);
}
