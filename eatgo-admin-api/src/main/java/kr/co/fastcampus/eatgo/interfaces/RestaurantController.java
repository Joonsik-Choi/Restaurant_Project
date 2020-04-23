package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin //다른 서버에서 가져오도록 호환성 적용
@NoArgsConstructor
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants=restaurantService.getRestaurants();
        return restaurants;
    }
    @GetMapping("/restaurant/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        Restaurant restaurant= restaurantService.getRestaurant(id);
        return restaurant;
    }
    @PostMapping("/restaurants")
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant resourece)
        //RequestBody 서 클래스 받을 경우 Restaurant 클래스에 기본 생성자 가 있어야한다
            throws URISyntaxException {
        Restaurant restaurant= restaurantService.addRestaurant(
                Restaurant.builder()
                .name(resourece.getName())
                .address(resourece.getAddress())
                .build());
        URI location=new URI("/restaurants/"+restaurant.getId());
        return ResponseEntity.created(location).body("{}");
    }
    @PatchMapping("/restaurant/{id}")
    public String update(@PathVariable("id") long id,
                         @Valid @RequestBody Restaurant resource){
             restaurantService.updateRestaurant(id, resource.getName(), resource.getAddress());
     return "{}";
    }
}
