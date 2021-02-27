package com.acsk.demo.controller;

import com.acsk.demo.permission.Authorization;
import com.acsk.demo.permission.PermissionEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class BaseController {


//    @Authorization(isAllMatch = false, value = PermissionEnum.demo, code = {}, group = {})
    @RequestMapping("/index.html")
    public String indexInput(ModelMap modelMap) {
        System.out.println("11111");
        modelMap.put("hello","hello world!");
        return "demo";
    }
}
