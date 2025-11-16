package spring_redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring_redis.entity.Category;
import spring_redis.model.CategoryResponse;
import spring_redis.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponse> findAll() {
        // cari semua parent categories

        List<Category> parents = categoryRepository.findAllForApi();
        return parents.stream().map(category -> {

            List<Category> children = category.getChildren();
            List<CategoryResponse> childrenResponses = children.stream().map(child -> {
                return  CategoryResponse.builder().id(child.getId()).name(child.getName()).build();
            }).toList();
           return  CategoryResponse.builder().id(category.getId()).name(category.getName()).children(childrenResponses).build();
        }).toList();
    }
}
