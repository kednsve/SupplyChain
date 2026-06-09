package com.supply_chain.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.supply_chain.pojo.Department;
import com.supply_chain.vo.Result;
import com.supply_chain.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @DeleteMapping("/{ids}")
    Result delById(@PathVariable List<Integer> ids) {
        departmentService.delById(ids);
        return Result.success();
    }

    @PutMapping
    Result update(@RequestBody Department department) {
        departmentService.update(department);
        return Result.success();
    }

    @GetMapping("/{id}")
    Result getById(@PathVariable Integer id) {
        return Result.success(departmentService.getById(id));
    }

    @GetMapping
    Result getDepartments(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name
    ) {
        Page<Department> departmentPage = departmentService.getDepartments(page, pageSize, name);
        return Result.success(departmentPage);
    }
}
