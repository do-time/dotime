package com.jhd.dotime.tasktime.service;

import com.jhd.dotime.tasktime.dto.AllocationTimeDto;

public interface AllocationTimeService {
    void getAllocationTime(Long taskId);

    void createAllocationTime(AllocationTimeDto.Request requestDto);

    void updateAllocationTime(AllocationTimeDto.Request requestDto);
}
