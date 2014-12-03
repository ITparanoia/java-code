package me.arthinking.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 拦截App请求，这个Filter中嵌入通用的处理
 * 配置拦截App请求路径
 * @author  Jason Peng
 * @create date 2014年12月3日
 */
public class AppFilter implements Filter {
    
    FilterConfig config = null;

    public void init(FilterConfig config) throws ServletException {

        this.config = config;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        request = new ParamRequestWarpper((HttpServletRequest)request);
        chain.doFilter(request, response);
    }

    public void destroy() {
        
    }
}

/**
 * 自定义的request包装类，处理参数。
 * @author  Jason Peng
 * @create date 2014年12月3日
 */
class ParamRequestWarpper extends HttpServletRequestWrapper {

    private HttpServletRequest request;

    public ParamRequestWarpper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String parameter) {
        String value = request.getParameter(parameter);
        // pageSize参数校验
        if ("pageSize".equals(parameter)) {
            int pageSize = NumberUtils.toInt(value);
            if(pageSize < 0){
                return "0";
            } else if(pageSize > 200){
                return "200";
            }
        }
        return value;
    }
}