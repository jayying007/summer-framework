package org.summer.framework.helper;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.summer.controller.UserController;
import org.summer.framework.bean.Handler;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
public class ControllerHelperTest {
    private static final Logger logger = LoggerFactory.getLogger(ControllerHelperTest.class);

    @Test
    public void getHandlerTest() {
        Handler handler = ControllerHelper.getHandler("get", "/customer");
        Assert.assertEquals(UserController.class, handler.getControllerClass());
    }

}
