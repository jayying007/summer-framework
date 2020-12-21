package org.summer.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.summer.framework.bean.Data;
import org.summer.framework.bean.Handler;
import org.summer.framework.bean.Param;
import org.summer.framework.bean.View;
import org.summer.framework.helper.BeanHelper;
import org.summer.framework.helper.ConfigHelper;
import org.summer.framework.helper.ControllerHelper;
import org.summer.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhuangJieYing
 * @date 2020/12/21
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化相关 Helper 类
        HelperLoader.init();
        // 获取 ServletContext 对象（用来注册Servlet）
        ServletContext servletContext = config.getServletContext();

        // 注册处理 JSP 的 Servlet
        ServletRegistration jspRegistration = servletContext.getServletRegistration("jsp");
        jspRegistration.addMapping(ConfigHelper.getAppJspPath() + "*");
        // 注册处理静态资源的默认 Servlet
        ServletRegistration defaultRegistration = servletContext.getServletRegistration("default");
        defaultRegistration.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            // 获取 Controller 类及其 Bean 实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);
            // 创建请求参数对象
            Map<String, Object> paramMap = new HashMap<>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);

                paramMap.put(paramName, paramValue);
            }

            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if (StringUtil.isNotEmpty(body)) {
                String[] params = StringUtil.splitString(body, "&");
                if (ArrayUtil.isNotEmpty(params)) {
                    for (String param : params) {
                        String[] array = StringUtil.splitString(param, "=");
                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }

            Param param = new Param(paramMap);

            Object result;

            // 调用 Action 方法
            Method actionMethod = handler.getActionMethod();
            logger.debug("调用方法：" + controllerClass.getName() + "." + actionMethod.getName());
            if (param.isEmpty()) {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, null);
            } else {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            }

            // 处理 Action 方法返回值
            if (result instanceof View) {
                //
                View view = (View) result;
                String path = view.getPath();
                if (StringUtil.isNotEmpty(path)) {
                    if (path.startsWith("/")) {
                        resp.sendRedirect(req.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        for (Map.Entry<String, Object> entry : model.entrySet()) {
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
                    }
                }
            } else if (result instanceof Data) {
                //
                Data data = (Data) result;
                Object model = data.getModel();
                if (model != null) {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
        }

    }
}
