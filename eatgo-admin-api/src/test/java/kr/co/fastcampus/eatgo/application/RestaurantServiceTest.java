package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

public class RestaurantServiceTest {
    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;

    @Before  //모든 테스트 실행전 실행
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockRestaurantRepository();
        restaurantService=new RestaurantService(restaurantRepository);
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
        assertThat(restaurant.getId(), is(1004L));
    }
    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants=restaurantService.getRestaurants();
        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1004L));
        restaurant = restaurants.get(1);
        assertThat(restaurant.getId(), is(2004L));
    }
    @Test
    public void addRestaurant(){
        given(restaurantRepository.save(any())).will(invocation -> {
            Restaurant restaurant1=invocation.getArgument(0);
            restaurant1.setId(1234L);
            return restaurant1;
        });
        Restaurant restaurant=Restaurant.builder()
                .name("BeRyong")
                .address("Busan")
                .build();
        Restaurant saved=Restaurant.builder()
                .id(1234L)
                .name("BeRyong")
                .address("Busan")
                .build();
        Restaurant create= restaurantService.addRestaurant(restaurant);

        assertThat(create.getId(), is(1234L));
    }
    @Test
    public void updateRestaurant(){
        Restaurant restaurant=Restaurant.builder()
                .id(1004L)
                .name("bobzip")
                .address("seoul")
                .build();
        given(restaurantRepository.findById(1004L)).willReturn(java.util.Optional.of(restaurant));
        restaurantService.updateRestaurant(1004,"soolzip", "busan");
        assertThat(restaurant.getName(), is("soolzip"));
        assertThat(restaurant.getAddress(),is("busan"));
    }

}