package com.ltnc.be.adapter.facade;

import com.ltnc.be.domain.task.Task;
import com.ltnc.be.dto.TaskDTO;
import com.ltnc.be.port.facade.TaskFacade;
import com.ltnc.be.port.repository.TaskRepository;
import com.ltnc.be.rest.response.TaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TaskFacadeImpl implements TaskFacade {
    private final TaskRepository taskRepository;


    @Override
    public List<TaskResponse> getAllTaskByEmployeeIdAndMonth(Long employeeId) {
        Optional<List<Task>> optionalTasks = taskRepository.findAllByEmployeeIdAndCriteria(employeeId);
        List<TaskResponse> taskResponses = new ArrayList<>();
        if(optionalTasks.isPresent()){
            for(Task task: optionalTasks.get()){
                TaskDTO taskDTO = TaskDTO.fromDomain(task);
                TaskResponse taskResponse = TaskResponse.toTaskResponse(taskDTO);
                taskResponses.add(taskResponse);
            }
        }
        return taskResponses;
    }
}
