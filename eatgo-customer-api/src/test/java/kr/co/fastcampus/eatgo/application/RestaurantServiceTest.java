package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {
    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;
    @Mock
    private ReviewRepository reviewRepository;
    @Before  //모든 테스트 실행전 실행
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        mockMenuItemRepository();
        mockReviewRepository();
        restaurantService=new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository
        );
    }

    private void mockReviewRepository() {
        List<Review> reviews=new ArrayList<>();
        reviews.add(Review.builder()
                    .name("BeRyong")
                    .score(1)
                    .description("Bad")
                    .build()
        );
        given(reviewRepository.findAllByRestaurantId(1004L))
                .willReturn(reviews);
    }

    private void mockMenuItemRepository() {
        List<MenuItem> menuItems=new ArrayList<>();
        menuItems.add(MenuItem.builder()
        .name("kmichi").build());
        given(menuItemRepository.findAllByRestaurantId(1004L)).willReturn(menuItems);
    }

    public void mockRestaurantRepository() {
        List<Restaurant> restaurants=new ArrayList<>();
        Restaurant restaurant1=Restaurant.builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();
        Restaurant restaurant2=Restaurant.builder()
                .id(2004L)
                .name("joon")
                .address("Seoul")
                .build();
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);
        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant1));
        given(restaurantRepository.findById(2004L)).willReturn(java.util.Optional.of(restaurant2));
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant=restaurantService.getRestaurant(1004L);
        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));
        assertThat(restaurant.getId(), is(1004L));
        MenuItem menuItem=restaurant.getMenuItems().get(0);
        assertThat(menuItem.getName(), is("kmichi"));
        Review review=restaurant.getReviews().get(0);
        assertThat(review.getDescription(), is("Bad"));
    }
    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants=restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
        restaurant = restaurants.get(1);
        assertThat(restaurant.getId(), is(2004L));
    }
}