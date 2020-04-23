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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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
        given(restaurantService.getRestaurant(2004L)).willReturn(restaurant);
        mvc.perform(get("/restaurant/2004"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"id\":2004")))
                .andExpect(content().string(containsString("\"name\":\"joon\"")));

    }

    @Test
    public void createWithValidData() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant=invocation.getArgument(0);
            return Restaurant.builder()
            .id(1234L)
            .name(restaurant.getName())
            .address(restaurant.getAddress())
                    .build();
        });

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\" :\"BeRyong\", \"address\":\"Seoul\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1234"))
                .andExpect(content().string("{}"));
        verify(restaurantService).addRestaurant(any());//post 방식일때 가짜 객체로 확인
        //모키토에서  any()는 모든 값 확인


    }
    @Test
    public void createWithInvalidData() throws Exception {
        given(restaurantService.addRestaurant(any())).will(invocation -> {
            Restaurant restaurant=invocation.getArgument(0);
            return Restaurant.builder()
                    .id(1234L)
                    .name(restaurant.getName())
                    .address(restaurant.getAddress())
                    .build();
        });

        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"name\" :\"\", \"address\":\"\"}"))
                .andExpect(status().isBadRequest());
        //모키토에서  any()는 모든 값 확인

    }
    @Test
    public void update() throws Exception {
    mvc.perform(patch("/restaurant/1234")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"name\":\"joon\", \"address\":\"sockcho\"}"))
            .andExpect(status().isOk());
    verify(restaurantService).updateRestaurant(1234L, "joon","sockcho");

    }
    @Test
    public void updateWithInvaliData() throws Exception {
        mvc.perform(patch("/restaurant/1234")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"\", \"address\":\"\"}"))
                .andExpect(status().isBadRequest());

    }
}