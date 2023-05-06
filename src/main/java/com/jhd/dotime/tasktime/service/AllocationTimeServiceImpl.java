package com.jhd.dotime.tasktime.service;

import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.entity.Task;
import com.jhd.dotime.tasks.repository.TaskRepository;
import com.jhd.dotime.tasktime.common.error.AllocationTimeErrorCode;
import com.jhd.dotime.tasktime.common.exception.AllocationException;
import com.jhd.dotime.tasktime.dto.AllocationTimeDto;
import com.jhd.dotime.tasktime.entity.AllocationTime;
import com.jhd.dotime.tasktime.mapper.AllocationTimeMapper;
import com.jhd.dotime.tasktime.repository.AllocationTimeRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AllocationTimeServiceImpl implements AllocationTimeService{

    private final AllocationTimeRepository allocationTimeRepository;

    private final TaskRepository taskRepository;

    private final AllocationTimeMapper allocationTimeMapper = Mappers.getMapper(AllocationTimeMapper.class);

    @Override
    public void insertAllocationTime(AllocationTimeDto.Request requestDto) {
        Task task = taskRepository.findById(requestDto.getTaskId())
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        for(AllocationTimeDto.Allocation allocation : requestDto.getAllocationList()){
            if(allocationTimeRepository.existsByTaskIdAndCategory(task.getId(),allocation.getCategory())){
                throw new AllocationException(AllocationTimeErrorCode.ALLOCATION_TIME_DUPLICATE);
            }

            allocationTimeRepository.save(
                    allocationTimeMapper.toEntity(allocation.getCategory(), allocation.getTime(), task)
            );
        }
    }


    @Override
    public void updateAllocationTime(AllocationTimeDto.Request requestDto) {
        Task task = taskRepository.findById(requestDto.getTaskId())
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        List<AllocationTime> allocationTimeList = allocationTimeRepository.findAllByTaskId(task.getId());

        // 이중 반복문 vs DB connection 여러번 발생하는 것 뭐가 더 좋은지 아직 모르겠음
//        for(AllocationTime allocationTime : allocationTimeList){
//            for(AllocationTimeDto.Allocation newAllocation : requestDto.getAllocationList()){
//                if(allocationTime.getCategory().equals(newAllocation.getCategory())) {
//                    allocationTime.updateTime(newAllocation.getTime());
//                }
//            }
//        }
        for(AllocationTimeDto.Allocation allocation : requestDto.getAllocationList()){
            AllocationTime allocationTime = allocationTimeRepository.findByTaskIdAndCategory(task.getId(), allocation.getCategory()).orElseThrow(() -> new AllocationException(AllocationTimeErrorCode.ALLOCATION_TIME_NOT_FOUNT));
            allocationTime.updateTime(allocation.getTime());
        }
    }

    
    @Override
    public List<AllocationTimeDto.Response> getAllocationTime(Long taskId) {
        taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        return allocationTimeRepository.findAllByTaskId(taskId).stream()
                .map(AllocationTimeDto.Response::of)
                .collect(Collectors.toList());
    }
}
