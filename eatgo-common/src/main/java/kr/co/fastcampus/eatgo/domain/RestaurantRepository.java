package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findAll();
    Optional<Restaurant> findById(Long id);//반환값이 Restaurant 이냐 아니냐로 판단
    Restaurant save(Restaurant restaurant);
}
