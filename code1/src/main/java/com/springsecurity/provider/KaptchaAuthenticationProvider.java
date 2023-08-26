package com.springsecurity.provider;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * {@code @Description:}
 */
public class KaptchaAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String kaptcha = request.getParameter("kaptcha");
        String code = (String) request.getSession().getAttribute("kaptcha");
        if (!ObjectUtils.isEmpty(kaptcha) && !ObjectUtils.isEmpty(code) && code.equalsIgnoreCase(kaptcha)) {
            // 调用父类DaoAuthenticationProvider的authenticate()
            return super.authenticate(authentication);
        }
        throw new AuthenticationServiceException("验证码错误");
    }
}