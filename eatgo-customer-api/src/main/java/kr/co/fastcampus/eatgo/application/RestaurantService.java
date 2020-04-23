package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;
    private MenuItemRepository menuItemRepository;
    private ReviewRepository reviewRepository;
    @Autowired
    public RestaurantService(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository,  ReviewRepository reviewRepository) {
        this.restaurantRepository=restaurantRepository;
        this.menuItemRepository=menuItemRepository;
        this.reviewRepository=reviewRepository;
    }
    public Restaurant getRestaurant(Long id){
        Restaurant restaurant=restaurantRepository.findById(id).orElse(null);//못찾을 경우 null로 초기화
        List<MenuItem> menuItems=menuItemRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        List<Review> reviews=reviewRepository.findAllByRestaurantId(id);
        restaurant.setReviews(reviews);
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
