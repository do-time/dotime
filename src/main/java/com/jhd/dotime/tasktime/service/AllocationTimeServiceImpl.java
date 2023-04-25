package com.jhd.dotime.tasktime.service;

import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasktime.common.error.AllocationTimeErrorCode;
import com.jhd.dotime.tasktime.common.exception.AllocationException;
import com.jhd.dotime.tasktime.dto.AllocationTimeDto;
import com.jhd.dotime.tasktime.mapper.AllocationTimeMapper;
import com.jhd.dotime.tasktime.repository.AllocationTimeRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllocationTimeServiceImpl implements AllocationTimeService{

    private final AllocationTimeRepository allocationTimeRepository;

    private final TaskRepository taskRepository;

    private final AllocationTimeMapper allocationTimeMapper = Mappers.getMapper(AllocationTimeMapper.class);

    @Override
    public void createAllocationTime(AllocationTimeDto.Request requestDto) {
//        Task task = taskRepository.findById(requestDto.getTaskId())
//                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));
//
//        if(allocationTimeRepository.existsByTaskAndType(task,requestDto.getType())){
//            throw new AllocationException(AllocationTimeErrorCode.ALLOCATION_TIME_DUPLICATE);
//        }
//
//        allocationTimeRepository.save(
//            allocationTimeMapper.toEntity(requestDto, task)
//        );
    }


    @Override
    public void updateAllocationTime(AllocationTimeDto.Request requestDto) {

    }

    
    @Override
    public void getAllocationTime(Long taskId) {

    }
}
