package com.ltnc.be.rest.controller;


import com.ltnc.be.domain.task.Task;
import com.ltnc.be.port.facade.TaskFacade;
import com.ltnc.be.rest.response.BaseResponse;
import com.ltnc.be.rest.response.TaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@AllArgsConstructor
public class TaskController {
    TaskFacade taskFacade;

    @GetMapping("/")
    @Operation(tags = "Task APIs")
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<List<TaskResponse>> getAllTaskByEmployeeIdAndMonth(@RequestParam Long employeeId){
        return BaseResponse.of(taskFacade.getAllTaskByEmployeeIdAndMonth(employeeId));
    }
}
