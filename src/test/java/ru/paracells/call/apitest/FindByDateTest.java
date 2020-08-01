package ru.paracells.call.apitest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.paracells.call.model.Task;
import ru.paracells.call.service.TaskService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindByDateTest {

    @Autowired
    private TaskService taskService;

//test base data in DB
    @Test
    public void findAllByDateBetween_HaveTaskNumber_FalseResult() {
        List<Task> tasks = taskService.filterTasks(
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2018, 1, 1),
                67264
        );

        Assert.assertEquals(0, tasks.size());
    }

    @Test
    public void findAllByDateBetween_Have_TaskNumber_TrueResult() {
        List<Task> tasks = taskService.filterTasks(
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2019, 12, 30),
                67264
        );

        Assert.assertEquals(1, tasks.size());
    }

    // default value for Task Number is 0, if we got it, than we run methow where NO task number
    @Test
    public void findAllByDateBetween_NO_TaskNumber_FalseResult() {
        List<Task> tasks = taskService.filterTasks(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 1),
                0
        );

        Assert.assertEquals(0, tasks.size());
    }

    @Test
    public void findAllByDateBetween_NO_TaskNumber_TrueResult() {
        List<Task> tasks = taskService.filterTasks(
                LocalDate.of(2017, 1, 1),
                LocalDate.of(2017, 12, 31),0
        );

        Assert.assertEquals(7, tasks.size());
    }
}
