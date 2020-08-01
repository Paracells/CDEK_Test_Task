package ru.paracells.call.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.paracells.call.model.Task;
import ru.paracells.call.service.TaskService;
import ru.paracells.utils.GenerateRandomTask;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // add new task
    @GetMapping("/delay")
    public Task delayTask() {
        Task task = GenerateRandomTask.generate();
        boolean isCreated = taskService.createTask(task);
        return task;

    }

    // return List of all tasks w/o filter
    @GetMapping("/getall")
    public List<Task> getAll() {
        return taskService.allTasks();

    }

    // return List of all task with filters
    @GetMapping("/get")
    public List<Task> getTasksBetweenDateAndTime(@RequestParam("dstart") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                                                 @RequestParam("dend") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
                                                 @RequestParam(value = "number", required = false, defaultValue = "0") int number) {
        return taskService.filterTasks(dateStart, dateEnd, number);
    }
}
