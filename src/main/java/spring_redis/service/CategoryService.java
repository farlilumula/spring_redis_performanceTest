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

        List<Category> parents = categoryRepository.findAllByParentIsNull();
        List<CategoryResponse> responses = parents.stream().map(category -> {
           return  CategoryResponse.builder().id(category.getId()).name(category.getName()).build();
        }).toList();

        // iterate
        // tambahkan childern ke parent categories
        for (CategoryResponse response : responses) {
            List<Category> children = categoryRepository.findAllByParentId(response.getId());
            List<CategoryResponse> childrenResponses = children.stream().map(category -> {
                return  CategoryResponse.builder().id(category.getId()).name(category.getName()).build();
            }).toList();
            response.setChildren(childrenResponses);
        }
        return responses;
    }
}
