package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.State;
import kr.co.fastcampus.eatgo.domain.UserInfo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//private String id;
//private String email;
//private String password;
//private String name;
//private String gender;
@RestController
public class UserInfoController {
    List<UserInfo> userInfoList=new ArrayList<>();
    UserInfoController(){
        userInfoList.add(new UserInfo("0", "wnstlr0615@naver.com", "1234", "최준식", "man"));
        userInfoList.add(new UserInfo("1", "wnstlr0615@naver.com", "1234", "최준식", "man"));
        userInfoList.add(new UserInfo("2", "wnstlr0615@naver.com", "1234", "최준식", "man"));
        userInfoList.add(new UserInfo("3", "wnstlr0615@naver.com", "1234", "최준식", "man"));
        userInfoList.add(new UserInfo("4", "wnstlr0615@naver.com", "1234", "최준식", "man"));


    }
//    @GetMapping("/userInfo")
//    public UserInfo userInfo(@RequestParam Long id){
//       UserInfo userInfo=userInfoList.stream().filter(q->q.getId().equals(id)).findFirst().get();
//        return userInfo;
//    }
    @GetMapping("/userInfo")
    public List<UserInfo> userInfo(){
        return userInfoList;
    }
//    @PostMapping("/userInfo")
//    public UserInfo userInfo1(@RequestParam Long id){
//
//        return userInfo;
//    }
    @GetMapping("/userId")
    public State CheckUserId(@RequestParam String id){
        UserInfo searchUser=userInfoList.stream().filter(a->a.getId().equals(id)).findFirst().orElse(null);
        State  state=new State();
        if(searchUser!=null)
            state.setResponse("sorry");
        else
            state.setResponse("ok");
        return  state;
    }
//    @PostMapping("/userInfo")
//    public String postUserInfo(@RequestBody String id){
//        return id;
//    }
    @PostMapping("/userInfoTest")
    public UserInfo postUserInfo(@RequestBody UserInfo user){
        return user;
    }
    @PostMapping("/userRegister")
    public State createUser( @RequestBody UserInfo userInfo){
    String id=userInfo.getId();
    String email=userInfo.getEmail();
    String password=userInfo.getPassword();
    String name=userInfo.getName();
    String gender=userInfo.getGender();
    UserInfo newUser=new UserInfo(id,email, password, name, gender);
    int oldCount=userInfoList.size();
    userInfoList.add(newUser);
    int newCount=userInfoList.size();
    State  state=new State();
    if(newCount>oldCount)
            state.setResponse("Join Ok");
    else
        state.setResponse("Join Fail");

    return state;
    }
}
