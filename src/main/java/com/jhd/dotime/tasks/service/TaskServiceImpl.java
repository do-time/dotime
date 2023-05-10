package com.jhd.dotime.tasks.service;

import com.jhd.dotime.common.error.ErrorCode;
import com.jhd.dotime.common.exception.CustomException;
import com.jhd.dotime.hashtag.entity.HashTag;
import com.jhd.dotime.hashtag.entity.TaskTag;
import com.jhd.dotime.hashtag.repository.HashTagRepository;
import com.jhd.dotime.hashtag.repository.TaskTagRepository;
import com.jhd.dotime.members.common.error.MemberErrorCode;
import com.jhd.dotime.members.common.exception.NotFoundException;
import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.common.error.TaskErrorCode;
import com.jhd.dotime.tasks.common.exception.TaskException;
import com.jhd.dotime.tasks.dto.TaskDto;

import com.jhd.dotime.tasks.entity.Task;
//import com.jhd.dotime.tasks.mapper.TaskMapper;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;

    private final MemberRepository memberRepository;

    private final TaskTagRepository taskTagRepository;

    private final HashTagRepository hashTagRepository;

    private final AllocationTimeRepository allocationTimeRepository;

    private final AllocationTimeMapper allocationTimeMapper = Mappers.getMapper(AllocationTimeMapper.class);

    @Override
    @Transactional
    public Long insert(Long memberId, TaskDto.Request taskRequestDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MemberErrorCode.MEMBER_NOT_FOUND));

//        //* 같은 이름의 title이 task list에 존재하는지 확인 있다면 DUPLICATE_RESOURCE 처리 *//
//        List<Task> taskLst = taskRepository.findTaskListByMemberId(memberId).contains();
//        for (Task task : taskLst) {
//            if(task.getTitle().equals(newTask)) throw new CustomException(ErrorCode.DUPLICATE_RESOURCE);
//
//        }

        Task newTask = taskRepository.save(Task.builder()
                                    .member(member)
                                    .content(taskRequestDto.getContent())
                                    .title(taskRequestDto.getTitle())
                                    .build());

        for(AllocationTimeDto.Allocation allocation : taskRequestDto.getAllocationList()){
            if(allocationTimeRepository.existsByTaskIdAndCategory(newTask.getId(),allocation.getCategory())){
                throw new AllocationException(AllocationTimeErrorCode.ALLOCATION_TIME_DUPLICATE);
            }

            allocationTimeRepository.save(
                    allocationTimeMapper.toEntity(allocation.getCategory(), allocation.getTime(), newTask)
            );
        }

        return newTask.getId();
    }

    /**
     *
     * @param id
     * @return TaskDto.Response
     */
    @Override
    @Transactional(readOnly = true)
    public List<TaskDto.Response> findTask(Long id) {
        return taskRepository.findById(id).stream()
                .map(TaskDto.Response::of)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDto.Response> findTaskList() {
        /**
         * Task entity에서 tasktag list를 리스트로 반환하는 getHashtag()를
         * 이용하여 Hashtag 모음을 불러온다.
         * 추후에 Hashtag Response를 받아 다시 String 값으로 반환하여 return 하게 구현해야 함.
         */
        return taskRepository.findAll().stream()
                .map(TaskDto.Response::of)
                .collect(Collectors.toList());
    }


    @Transactional
    public Long delete(Long id) {
        Task tasks = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));
        taskRepository.delete(tasks);
        System.out.println("tasks.getTitle() = " + tasks.getTitle() + " tasks.id = " + tasks.getId());
        return id;
    }

    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }


    /**
     * 
     * @param id
     * @param taskRequestDto
     * @param hashtagIdList
     * @return taskId
     * 현재 로직
     * 사용자가 Task(To-do)를 등록할 때 Hashtag를 달아서 등록한다.
     * 만약 Task를 수정할 때, Hashtag도 함께 수정한다. 하지만 Hashtag를 매번 새로 등록하면 불필요한 연산과 더불어 해당 Task의 TaskTag 테이블을 모두 지웠다가 재등록하기 때문에 id값을 새로 생성
     * 등록된 Hashtag가 들어오면 삭제하는게 아닌 continue
     * 새로운 Hashtag가 들어오게 되면 TaskTag 테이블에 새로 등록.
     * 더 좋은 방법이 있는지 고민해야 함
     */
    @Override
    @Transactional
    public Long update(Long id, TaskDto.Request taskRequestDto, List<Long> hashtagIdList) {
        Task tasks = taskRepository.findById(id)
                .orElseThrow(() -> new TaskException(TaskErrorCode.TASK_NOT_FOUNT));

        List<TaskTag> taskTagList = taskTagRepository.findTaskTagByTaskId(id);
        List<TaskTag> taskTags = new ArrayList<>();


        // 있으면 하고 없으면 삭제 안함, taskTagList - 기존 taskTagList
        for (TaskTag taskTag : taskTagList) {
            if(!hashtagIdList.contains(taskTag.getHashTag().getId())) {
                taskTagRepository.delete(taskTag);
            }
        }

        List<Long> newHasktagIdList = taskTagRepository.findTaskTagByTaskId(id).stream().map(TaskTag::getHashTag).map(HashTag::getId).collect(Collectors.toList());

        // 업데이트 시에도 이미 tasktag list에 해당 해시태그 존재하면 add 일어나면 안됨
        for (Long hashtagId : hashtagIdList) {
            HashTag hashTag = hashTagRepository.findById(hashtagId).orElseThrow(() -> new NotFoundException("해시태그가 존재하지 않습니다."));
            if(newHasktagIdList.contains(hashtagId))
                continue;
            taskTags.add(TaskTag.builder().task(tasks).hashTag(hashTag).build());
        }

        tasks.update(taskRequestDto.getTitle(), taskRequestDto.getContent(), taskTags);

        List<AllocationTime> allocationTimeList = allocationTimeRepository.findAllByTaskId(id);

        // Allocation Time update
        // 현재 버전은 초기 입력시 모든 enum 타입이 모두 들어오고, 수정시에는 해당 allocation time 조회해 수정
        for(AllocationTimeDto.Allocation allocation : taskRequestDto.getAllocationList()){
            AllocationTime allocationTime = allocationTimeRepository.findByTaskIdAndCategory(tasks.getId(), allocation.getCategory()).orElseThrow(() -> new AllocationException(AllocationTimeErrorCode.ALLOCATION_TIME_NOT_FOUNT));
            allocationTime.updateTime(allocation.getTime());
        }

        return id;
    }

    /**
     *
     * @param memberId
     * @return memberId로 등록된 To-do List 반환
     */
    @Override
    @Transactional(readOnly = true)
    public List<TaskDto.Response> getTaskListByMemberId(Long memberId){
        return taskRepository.findTaskListByMemberId(memberId).stream()
                .map(TaskDto.Response::of)
                .collect(Collectors.toList());
    }

    public boolean existTask(Long taskId) {
        return taskRepository.existsById(taskId);
    }

    @Override
    public boolean existTask(Long memberId, Long taskId) {
        return taskRepository.existsByIdAndMemberId(memberId, taskId);
    }
}