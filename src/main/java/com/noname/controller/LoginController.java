package com.noname.controller;

import com.noname.entity.Admin;
import com.noname.mapper.AdminMapper;
import com.noname.shiro.ShiroRealm;
import com.noname.util.EncrypUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
public class LoginController {

    @Resource
    AdminMapper adminMapper;

    @RequestMapping("/login")
    public String login(Admin admin) throws Exception{

        List<Admin> adminList = adminMapper.select(admin);
        if(adminList.size()>0){
            admin = adminList.get(0);
            return EncrypUtils.encryp(admin.getUsername(), admin.getPassword());
        }else{
            return "账号或密码错误!";
        }
    }

    @RequestMapping("/myLogin")
    @ResponseBody
    public Map<String, Object> submitLogin(String username, String password, Model model){

        Map<String, Object> resultMap = new LinkedHashMap<>();
        try {

            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            System.out.println("i am here");

            System.out.println(token);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", "200");
            resultMap.put("message", "登陆成功!");
        }catch (Exception e){
            resultMap.put("status", "502");
            resultMap.put("message", e.getMessage());
        }

        return resultMap;
    }

}
