package com.company.business.controller;

import com.company.business.model.User;
import com.company.business.service.UserService;
import com.company.common.constant.Constants;
import com.company.common.constant.Message;
import com.company.common.controller.BaseController;
import com.company.common.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class UserController extends BaseController {

    @Resource
    private UserService userService;

    @RequestMapping(value = "/select.html")
    @ResponseBody
    public Object getUser(User user) {
        String id = "0";//user.getId();
        String name = "1"; //user.getName();
        if (StringUtil.isEmpty(id)) {
            if (StringUtil.isEmpty(name)) {
                return Message.noContent(null);
            }
            user = userService.getUserByName(name.trim());
        } else {
            user = userService.getUserById(id);
        }
        if (null == user) {
            return Message.noContent(user);
        }
        return Message.ok(user);
    }

    @RequestMapping(value = "/add.html", method = RequestMethod.POST)
    @ResponseBody
    public Object addUser(User user) {

        String name = user.getName();
        String password = user.getPassword();
        String address = user.getAddress();

        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(password) || StringUtil.isEmpty(address)) {
            return Message.error(Constants.INCORRECT_PARAMETER);
        }
        boolean status = userService.addUser(user);
        if (status) {
            return Message.ok(Constants.SUCCESS);
        }
        return Message.noContent(Constants.FAILED);
    }


    @RequestMapping(value = "/insert.html")
    public String insert() {
        return "add";
    }

}