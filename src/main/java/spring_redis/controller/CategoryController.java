package spring_redis.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_redis.model.CategoryResponse;
import spring_redis.service.CategoryService;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private CategoryService categoryService;

    @GetMapping(value = "/api/categories", produces = "application/json")
    public List<CategoryResponse> findAll(){
        return categoryService.findAll();
    }
}
