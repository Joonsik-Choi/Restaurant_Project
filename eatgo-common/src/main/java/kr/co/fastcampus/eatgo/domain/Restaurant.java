package kr.co.fastcampus.eatgo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private  String name;
    @NotEmpty
    private  String address;
    @Transient//에러 무시
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MenuItem> menuItems;
    @Transient
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ArrayList<Review> reviews;

    public Restaurant(long id, String name, String address) {
        this.id=id;
        this.name=name;
        this.address=address;
    }
    public void setMenuItems(List<MenuItem> menuItems){
        this.menuItems=new ArrayList<>(menuItems);
    }
    public Restaurant(String name, String address) {
        this.name=name;
        this.address=address;
    }

    public void addMenuItem(MenuItem name) {
        if(menuItems==null)
            menuItems=new ArrayList<>();
        menuItems.add(name);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews=new ArrayList<>(reviews);
    }

    public String getInformation() {
        return name+" in "+address;
    }
}
