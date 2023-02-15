package com.jhd.dotime.tasks.repository;


import com.jhd.dotime.tasks.entity.Task;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import java.util.List;


@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;


    @BeforeEach
    public void cleanup(){
        taskRepository.deleteAll();
    }

    @Test
    @DisplayName("task 저장")
    public void store() {
        //given
        Task task = Task.builder().title("task").content("nono").build();

        //when
        taskRepository.save(task);
        List<Task> taskList = taskRepository.findAll();

        //then
        Assertions.assertThat(task.getTitle()).isEqualTo(taskList.get(taskList.size()-1).getTitle());
    }

    @Test
    @DisplayName("task 삭제")
    public void delete(){
        //given
        Task task = Task.builder().title("new task").content("nono").build();

        //when
        taskRepository.delete(task);
        List<Task> taskList = taskRepository.findAll();
        boolean success = taskList.contains(task);

        //then
        Assertions.assertThat(success).isEqualTo(false);
    }

    @Test
    @DisplayName("task 조회")
    public void getTask(){
        //given
        Task task = Task.builder().title("new task").content("nono").build();

        //when
        taskRepository.save(task);
        List<Task> lst = taskRepository.findAll();

        //then
        Assertions.assertThat(taskRepository.findById(lst.get(lst.size()-1).getId()).get().getTitle()).isEqualTo(task.getTitle());

    }

}