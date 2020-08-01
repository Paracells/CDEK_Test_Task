package ru.paracells.call.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.paracells.call.model.Task;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByDateBetween(LocalDate dateStart, LocalDate dateEnd);

    List<Task> findAllByDateBetweenAndNumber(LocalDate dateStart, LocalDate dateEnd, int number);

    Task findByNumber(int number);

}
