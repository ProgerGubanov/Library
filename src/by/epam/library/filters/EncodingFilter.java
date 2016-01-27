package by.epam.library.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by Gubanov Andrey on 04.01.2016.
 */

/**
 * Фильтр кодировки
 */
public class EncodingFilter implements Filter {

    /**
     * Инициализация
     *
     * @param filterConfig конфигурация
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Фильтрация
     *
     * @param request  запрос
     * @param response ответ
     * @param chain    цепочка
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    /**
     * Уничтожение
     */
    @Override
    public void destroy() {
    }
}
