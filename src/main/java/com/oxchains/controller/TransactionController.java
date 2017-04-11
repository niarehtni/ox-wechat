package com.oxchains.controller;

import com.oxchains.base.BaseController;
import com.oxchains.common.RespDTO;
import com.oxchains.model.Transaction;
import com.oxchains.model.User;
import com.oxchains.service.TransactionService;
import com.oxchains.service.UserService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * TransactionController
 *
 * @author liuruichao
 * Created on 2016/12/19 10:29
 */
@Controller
@RequestMapping("/transaction")
public class TransactionController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @Resource
    private TransactionService transactionService;

    @Resource
    private UserService userService;

    @RequestMapping("/create")
    @ResponseBody
    public RespDTO<Integer> create(Transaction transaction) {
        RespDTO<Integer> result = null;
        try {
            result = transactionService.create(transaction);
        } catch (Exception e) {
            logger.error("create transaction error!!!", e);
            result = RespDTO.fail("系统繁忙，请稍后再试！");
        }
        return result;
    }

    @RequestMapping("/receipt")
    public String receipt() {
        return "transaction/receipt";
    }

    @RequestMapping("/payment")
    public String payment(HttpServletRequest request, Integer toId, String flag) {
        Integer fromId = 140;
        if (toId == 140) {
            fromId = 141;
        }
        request.setAttribute("fromId", fromId);
        request.setAttribute("toUser", userService.getUser(toId));
        request.setAttribute("flag", flag);
        return "transaction/payment";
    }

    @RequestMapping("/after")
    @ResponseBody
    public RespDTO<List<Transaction>> afterTransaction(HttpServletRequest request, long date) throws InterruptedException {
        User user = (User) request.getSession().getAttribute("user");
        List<Transaction> list = null;
        int total = 0;
        do {
            list = transactionService.getAfterTransaction(user.getId(), new Date(date));
            Thread.sleep(1000);
            ++total;
            if (total >= 30) {
                break;
            }
        } while (list.isEmpty());
        return RespDTO.success("", list);
    }

    @RequestMapping("/list/{userId}")
    public String list(HttpServletRequest request, @PathVariable  Integer userId) {
        try {
            List<Transaction> list = transactionService.list(userId);
            request.setAttribute("list", list);
            return "transaction/list";
        } catch (Exception e) {
            logger.error("query transaction list error!!!", e);
        }
        return ERROR_PAGE_404;
    }
}
