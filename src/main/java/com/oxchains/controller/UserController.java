package com.oxchains.controller;

import com.oxchains.base.BaseController;
import com.oxchains.common.RespDTO;
import com.oxchains.model.User;
import com.oxchains.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * UserController
 * 
 * @author liuruichao
 * Created on 2016-01-15 10:08
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping("/{userId}")
    public String index(HttpServletRequest request, @PathVariable Integer userId, @RequestParam(defaultValue = "") String flag) {
        User user = userService.getUser(userId);
        request.getSession().setAttribute("user", user);
        request.getSession().setAttribute("flag", flag);
        return "user/index";
    }

    /*@RequestMapping("/list")
    @ResponseBody
    public RespDTO<List<User>> list() {
        RespDTO<List<User>> result = null;
        try {
            List<User> users = userService.list();
            result = RespDTO.success("success", users);
        } catch (Exception e) {
            logger.error("UserController.list() error: ", e);
            result = RespDTO.fail("系统繁忙，请稍后再试！");
        }
        return result;
    }*/
}
