package org.spring.example.jpa.util.request;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public interface HttpServletRequestService {

    RequestInformation requestInfo(HttpServletRequest httpServletRequest);

    Cookie findCookieByCookieName(HttpServletRequest httpServletRequest, String cookieName);

}
