package spring_redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring_redis.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findAllByParentIsNull();

    List<Category> findAllByParentId(String parentId);

    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.children WHERE c.parent IS NULL")
    List<Category> findAllForApi();



}
