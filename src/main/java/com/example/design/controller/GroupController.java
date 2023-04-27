package com.example.design.controller;

import com.example.design.domain.ErrorTemplate;
import com.example.design.domain.UserInfo;
import com.example.design.domain.UserRequest;
import com.example.design.door.annotation.DoDoor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

@Controller
public class GroupController {

    @DoDoor(key = "UserRequest")
    @PostMapping(value = "/v1/selectUserInfo")
    public ErrorTemplate selectUserInfo(@RequestBody UserRequest userRequest) {
        return ErrorTemplate.success(new UserInfo("nihao"+userRequest.getUserId(),1,"杭州市"));
    }

    @GetMapping(value = "/v1/openUrl")
    public String openUrl(HttpServletResponse resp) throws IOException {
        String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath();//注意getResource("")里面是空字符串
        String str = path+"com/example/design/util/东疆备案表.xlsx";
        File file = new File(str);
        FileInputStream fileInputStream = new FileInputStream(file);
        System.out.println(path);
        return "redirect:http://www.baidu.com/";
    }


    @GetMapping(value = "/v1/invoke")
    public void invoke(HttpServletResponse resp) throws Exception{

    }
}
