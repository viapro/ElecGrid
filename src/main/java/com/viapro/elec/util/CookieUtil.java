package com.viapro.elec.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.viapro.elec.vo.User;

public class CookieUtil {

    /**
     * @Name:remberMe
     * @Description:if the user chose to remember, then this method will remain the name and password into a cookie
     * @Author:ViaPro
     * @Version:V1.00
     * @Create Date:2013-9-28 PM9:42:36
     * @Parameters:~
     * @Return:void
     */
    public static void remberMe(User user, HttpServletRequest request, HttpServletResponse response) {
        String name = null;
        try {
            name = URLEncoder.encode(user.getName(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String password = user.getPassword();
        String remberMe = user.getRemeberMe();

        Cookie nameCookie = new Cookie("name", name);
        Cookie passwordCookie = new Cookie("password", password);

        nameCookie.setPath(request.getContextPath() + "/");
        passwordCookie.setPath(request.getContextPath() + "/");

        if ("yes".equals(remberMe)) {
            nameCookie.setMaxAge(60 * 60 * 24 * 7);
            passwordCookie.setMaxAge(60 * 60 * 24 * 7);
        } else {
            nameCookie.setMaxAge(0);
            passwordCookie.setMaxAge(0);
        }

        response.addCookie(nameCookie);
        response.addCookie(passwordCookie);
    }

}
