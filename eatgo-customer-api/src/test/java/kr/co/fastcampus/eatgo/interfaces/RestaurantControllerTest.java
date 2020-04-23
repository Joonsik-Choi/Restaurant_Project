package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean//가짜 객체 생성
    private RestaurantService restaurantService;
    //    @SpyBean(RestaurantService.class)
//    private RestaurantService restaurantService;
//    @SpyBean(RestaurantRepositoryImpl.class) //의존성 주입
//    private RestaurantRepository repository;
//    @SpyBean(MenuItemRepositoryImpl.class)
//    private MenuItemRepository menuItemRepository;
    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        given(restaurantService.getRestaurants()).willReturn(restaurants);//가짜 처리
        mvc.perform(get("/restaurants"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"name\":\"Bob zip\"")));

    }

    @Test
    public void detail() throws Exception {
        Restaurant restaurant =Restaurant.builder()
                .id(2004L)
                .name("joon")
                .address("Seoul")
                .build();
        restaurant.setMenuItems(Arrays.asList(MenuItem.builder().name("kmichi").build()));// 리스트로 만들어서 입력
        Review review= Review.builder().name("Jooon").score(5).description("good").build();
        restaurant.setReviews(Arrays.asList((review)));
        given(restaurantService.getRestaurant(2004L)).willReturn(restaurant);
        mvc.perform(get("/restaurant/2004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2004")))
                .andExpect(content().string(containsString("\"name\":\"joon\"")))
                .andExpect(content().string(containsString("kmichi")))
                .andExpect(content().string(containsString("good")));
    }
}