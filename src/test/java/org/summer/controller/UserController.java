package org.summer.controller;

import org.summer.framework.annotation.Action;
import org.summer.framework.annotation.Controller;
import org.summer.framework.bean.Param;
import org.summer.framework.bean.View;

import java.util.List;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
@Controller
public class UserController {
    @Action("get:/customer")
    public View index(Param param) {
        return new View("customer.jsp");
    }
}
