package com.supply_chain.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.supply_chain.pojo.Category;
import com.supply_chain.service.CategoryService;
import com.supply_chain.vo.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Category")
public class CategoryController {
    private final CategoryService categoryService;

    @DeleteMapping("/{ids}")
    Result delById(@PathVariable List<Integer> ids) {
        categoryService.delByIds(ids);
        return Result.success();
    }

    @PutMapping
    Result update(@RequestBody Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @GetMapping("/{id}")
    Result getById(@PathVariable Integer id) {
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    @GetMapping
    Result getCategories(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String name
    ) {
        Page<Category> categoryPage = categoryService.getCategories(page, pageSize, name);
        return Result.success(categoryPage);
    }
}
