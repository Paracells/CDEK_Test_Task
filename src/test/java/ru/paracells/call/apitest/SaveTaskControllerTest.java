package ru.paracells.call.apitest;

import org.apache.tomcat.jni.Local;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.paracells.call.model.Task;
import ru.paracells.call.repository.TaskRepository;
import ru.paracells.call.service.TaskService;
import ru.paracells.utils.GenerateRandomTask;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SaveTaskControllerTest {

    @Autowired
    private TaskService taskService;

    @MockBean
    private TaskRepository taskRepository;

    private Task task;


    @Before
    public void setUp() throws Exception {
        task = new Task(555,
                LocalDate.of(2015, 5, 25),
                LocalTime.of(11, 11, 55),
                true);

    }

    @Test
    public void whenSaveTask_ShouldReturnTrue_AnotherTask() {

        Mockito.when(taskRepository.save(task))
                .thenReturn(task);
        boolean isTaskCreated = taskService.createTask(task);
        Assert.assertTrue(isTaskCreated);

    }

    @Test
    public void whenSaveTask_thenShouldReturnFalse_TaskHaveSameName() {


        Mockito.when(taskRepository.findByNumber(task.getNumber()))
                .thenReturn(task);

        boolean isTaskCreated = taskService.createTask(task);
        Assert.assertFalse(isTaskCreated);
    }

    @Test
    public void whenSaveTask_thenShouldReturnListOfAllTasks() {

        int count = 0;
        Mockito.when(taskRepository.save(task))
                .thenReturn(task);
        for (int i = 0; i < 10; i++) {
            Mockito.when(taskRepository.save(GenerateRandomTask.generate())).thenReturn(new Task());
            if (taskService.createTask(task))
                count++;
        }
        Assert.assertEquals(10, count);
    }


    @Test
    public void whenFilterTask_thenShouldReturn_ZeroList_DateTaskCantBeMoreThanToday() {
        List<Task> resultList = new ArrayList<>();
        Mockito.when(taskRepository.findAllByDateBetween(
                LocalDate.of(2021, 1, 1),
                LocalDate.of(2021, 1, 1))).thenReturn(resultList);

        List<Task> tasks = taskService.filterTasks(
                LocalDate.of(3000, 1, 1),
                LocalDate.of(3000, 1, 1), 0
        );

        Assert.assertEquals(0, resultList.size());

    }

    @Test
    public void whenFilterTask_thenShouldReturn_OneTask() {
        Mockito.when(taskRepository.findAllByDateBetween(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2016, 1, 1))).thenReturn(
                Stream.of(
                        new Task(555,
                                LocalDate.of(2015, 5, 25),
                                LocalTime.of(11, 11, 55),
                                true)).collect(Collectors.toList()));
        Assert.assertEquals(1, taskService.filterTasks(
                LocalDate.of(2015, 1, 1),
                LocalDate.of(2016, 1, 1), 0
        ).size());


    }

}
