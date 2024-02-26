package org.spring.example.jpa.util.request;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class HttpServletRequestServiceImpl implements HttpServletRequestService {

    @Override
    public RequestInformation requestInfo(HttpServletRequest httpServletRequest) {

        HttpSession session = httpServletRequest.getSession();

        RequestInformation information = RequestInformation.builder()
                .requestUrl(findRequestUrl(httpServletRequest))
                .httpMethod(findHttpMethod(httpServletRequest))
                .cookies(findCookies(httpServletRequest))
                .build();

        information.printInfo();

        return information;
    }

    @Override
    public Cookie findCookieByCookieName(HttpServletRequest httpServletRequest, String cookieName) {
        return findCookies(httpServletRequest).stream().filter(item -> item.getName().equals(cookieName)).findFirst().orElse(null);
    }

    private List<Cookie> findCookies(HttpServletRequest httpServletRequest) {
        List<Cookie> cookies = new LinkedList<>();
        Cookie[] requestCookies = httpServletRequest.getCookies();
        if (requestCookies != null) {
            cookies.addAll(Arrays.asList(requestCookies));
        }

        return cookies;
    }

    private String findRequestUrl(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getRequestURI();
    }

    private HttpMethod findHttpMethod(HttpServletRequest httpServletRequest) {
        return Arrays.stream(HttpMethod.values())
                .filter(item -> item.name().equals(httpServletRequest.getMethod())).findFirst().orElse(null);
    }

}
