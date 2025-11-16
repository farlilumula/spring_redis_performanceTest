package spring_redis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_redis.entity.Category;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {

    List<Category> findAllByParentIsNull();

    List<Category> findAllByParentId(String parentId);

}
