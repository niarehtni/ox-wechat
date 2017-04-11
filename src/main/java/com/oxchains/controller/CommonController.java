package com.oxchains.controller;

import com.oxchains.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * CommonController
 *
 * @author liuruichao
 * Created on 2016-01-06 13:11
 */
@Controller
@RequestMapping("")
public class CommonController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @RequestMapping("/")
    @ResponseBody
    public String hello(String userName, String password) {
        return "success";
    }
}
