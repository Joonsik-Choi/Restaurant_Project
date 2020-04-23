package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository=restaurantRepository;

    }
    public Restaurant getRestaurant(Long id){
        Restaurant restaurant=restaurantRepository.findById(id).orElse(null);//못찾을 경우 null로 초기화
        return restaurant;
    }
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants=restaurantRepository.findAll();
        return  restaurants;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

@Transactional//db에 자동 업데이트
    public Restaurant updateRestaurant(long id, String name, String address) {
        Restaurant restaurant=restaurantRepository.findById(id).orElse(null);
        restaurant.setName(name);
        restaurant.setAddress(address);
        return restaurant;
    }
}
