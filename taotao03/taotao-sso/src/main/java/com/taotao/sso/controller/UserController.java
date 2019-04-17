package com.taotao.sso.controller;

import com.taotao.common.utils.ExceptionUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    //注册检测
    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param,@PathVariable Integer type,String callback){
        TaotaoResult result=null;
        if(StringUtils.isBlank(param)){
            result = TaotaoResult.build(400,"输入内容不能为空");
        }
        if(type==null){
            result = TaotaoResult.build(400,"输入类型不能为空");
        }
        if(type!=1&&type!=2&&type!=3){
            result = TaotaoResult.build(400,"类型输入有误");
        }
        if (result!=null){
            if(callback!=null){
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            }else {
                return result;
            }
        }

        try {
            result = userService.checkData(param, type);
        } catch (Exception e) {
            result = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        if(callback!=null){
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }else{
            return result;
        }
    }

    //用户注册
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createUser(TbUser user){
        //判断注册条件  没写
        try {
            TaotaoResult result = userService.createUser(user);
            return result;
        } catch (Exception e) {
            return  TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

    //用户登录
    @RequestMapping(value="/login", method=RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String username, String password
      ,HttpServletRequest request, HttpServletResponse response) {
        try {
            TaotaoResult result = userService.userLogin(username, password,request,response);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
    }

    //根据token取信息
    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token,String callback){
        TaotaoResult result = null;
        try {
            result = userService.getUserByToken(token);
        } catch (Exception e) {
            result= TaotaoResult.build(500,ExceptionUtil.getStackTrace(e));
        }
        if (StringUtils.isBlank(callback)){
            return result;
        }else{
            MappingJacksonValue mappingJacksonValue =new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }
    }
    //安全退出
    @RequestMapping("/logout/{token}")
    public Object Userlogout(@PathVariable String token,String callback){
        TaotaoResult result = userService.Userlogout(token, callback);
        return  result;
    }
}

