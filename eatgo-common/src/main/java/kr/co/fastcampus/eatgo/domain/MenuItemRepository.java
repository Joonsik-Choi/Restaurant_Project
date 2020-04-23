package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
//extends CrudRepository<MenuItem, Long> 사용시 구현 없이 JPA 사용가능
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);
    void deleteById(Long id);
}
