package org.spring.example.jpa.configure.security.handler;

import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

public class CustomCsrfTokenRequestAttributeHandler extends CsrfTokenRequestAttributeHandler {
    public CustomCsrfTokenRequestAttributeHandler() {
        super.setCsrfRequestAttributeName("_csrf");
    }
    
}
