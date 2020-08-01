package ru.paracells.call.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import ru.paracells.call.model.Task;
import ru.paracells.call.repository.TaskRepository;
import ru.paracells.utils.GenerateRandomTask;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository taskRepository;


    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    public boolean createTask(Task task) {
        if (taskRepository.findByNumber(task.getNumber())!=null)
            return false;
        taskRepository.save(task);
        return true;
    }

    public List<Task> allTasks() {
        return taskRepository.findAll();
    }

    public List<Task> filterTasks(LocalDate dateStart,
                                  LocalDate dateEnd, int number) {
        if (number == 0)
            return taskRepository.findAllByDateBetween(dateStart, dateEnd);
        return taskRepository.findAllByDateBetweenAndNumber(dateStart, dateEnd, number);

    }
}
