package com.jhd.dotime.tasks.repository;


import com.jhd.dotime.tasks.entity.Task;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@DataJpaTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;


    @AfterEach
    public void cleanup(){
        taskRepository.deleteById(1L);
    }

    @Test
    @DisplayName("task 저장")
    public void store() {

        //given
        LocalDateTime now = LocalDateTime.now();
        Task task = Task.builder().title("task").content("nono").created_date(now).updated_date(now).build();
        //when
        taskRepository.save(task);
        List<Task> taskList = taskRepository.findAll();

        //then
        Assertions.assertThat(task.getTitle()).isEqualTo(taskList.get(0).getTitle());
    }



    @Test
    @DisplayName("task 업데이트")
    public void update(){
        //given
        LocalDateTime now = LocalDateTime.now();
        final Task task = Task.builder().title("task").content("nono").created_date(now).updated_date(now).build();
        final Task newTask = Task.builder().title("fff task").content("nono").created_date(now).updated_date(now).build();
        //when
        taskRepository.save(task);
        taskRepository.save(newTask);
        List<Task> taskList = taskRepository.findAll();
        Assertions.assertThat(newTask.getTitle()).isEqualTo(taskList.get(1).getTitle());

        for (Task task1 : taskList) {
            System.out.println("task1.getTitle() = " + task1.getTitle());
        }


        //then
//        Assertions.assertThat(taskRepository.findById(1L)).isEqualTo(task.getId());
    }

    @Test
    @DisplayName("task 삭제")
    public void delete(){
        //given
        LocalDateTime now = LocalDateTime.now();
        Task task = Task.builder().title("new task").content("nono").created_date(now).updated_date(now).build();
        //when
        taskRepository.delete(task);
        //then
        Assertions.assertThat(taskRepository.findById(1L)).isEqualTo(Optional.empty());
    }

}