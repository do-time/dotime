package com.jhd.dotime.tasktime.service;

import com.jhd.dotime.tasktime.dto.AllocationTimeDto;

public interface AllocationTimeService {

    void createAllocationTime(AllocationTimeDto.Request requestDto);

    void updateAllocationTime(AllocationTimeDto.Request requestDto);

    void getAllocationTime(Long taskId);
}
