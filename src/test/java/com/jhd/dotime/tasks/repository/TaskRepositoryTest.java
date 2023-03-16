package com.jhd.dotime.tasks.repository;


import com.jhd.dotime.members.entity.Member;
import com.jhd.dotime.members.repository.MemberRepository;
import com.jhd.dotime.tasks.entity.Task;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test") // application-test
@SpringBootTest
class TaskRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private TaskRepository taskRepository;

//    @BeforeEach
//    public void cleanup(){
//        memberRepository.deleteAllInBatch();
//        taskRepository.deleteAllInBatch();
//    }

    @Test
    @DisplayName("[Repository] task 조회")
    public void getTask() {
        //given
        Task task = Task.builder().title("new task").content("nono").hashtag("#hello#bye").build();

        //when
        taskRepository.save(task);
        List<Task> lst = taskRepository.findAll();

        //then
        Assertions.assertThat(taskRepository.findById(lst.get(lst.size() - 1).getId()).get().getTitle()).isEqualTo(task.getTitle());

    }

    @Test
    @DisplayName("member로 task 목록 조회")
    public void insertTaskWithMember() {
        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        memberRepository.save(newMember);
        Long memberId = memberRepository.findByEmail(newMember.getEmail()).get().getId();
        Task task1 = new Task(1L, newMember,  "new task", "test1", "testtest");

        taskRepository.save(task1);
//        taskRepository.save(task2);
//        taskRepository.save(task3);


        //when
        List<Task> findTask = taskRepository.findTaskListByMemberId(memberId);
        for (Task task : findTask) {
            System.out.println("task.getTitle() = " + task.getTitle());

        }

        //then
//        Assertions.assertThat(findTask.stream().count()).isEqualTo(1);
        Assertions.assertThat(findTask.get(0).getTitle()).isEqualTo(task1.getTitle());
    }

    @Test
    @DisplayName("[Repository] task 저장")
    public void store() {
        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        //when
        memberRepository.save(newMember);
        Task newTask = new Task(1L, newMember,  "new task", "test1", "testtest");
        taskRepository.save(newTask);
        List<Task> taskList = taskRepository.findTaskListByMemberId(newMember.getId());
//        newMember.setTask(taskList);


        //then
        Assertions.assertThat(newTask.getTitle()).isEqualTo(taskList.get(taskList.size() - 1).getTitle());
    }

    @Test
    @DisplayName("[Repository] task 삭제")
    public void delete() {
        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("testMan")
                .profileImage("")
                .build();

        //when
        memberRepository.save(newMember);
        Task newTask = new Task(1L, newMember,  "new task", "test1", "testtest");
        taskRepository.save(newTask);
        List<Task> taskList = taskRepository.findAll();
        for (Task task : taskList) {
            System.out.println("task.getTitle() = " + task.getTitle());
        }
        taskRepository.delete(newTask);
        taskList = taskRepository.findTaskListByMemberId(memberRepository.findByEmail(newMember.getEmail()).get().getId());

        boolean success = taskList.contains(newTask);

        //then
        Assertions.assertThat(success).isEqualTo(false);
    }


}