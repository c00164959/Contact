package com.example.contactapp.web;

import com.britesnow.snow.web.RequestContext;
import com.britesnow.snow.web.auth.AuthRequest;
import com.britesnow.snow.web.auth.AuthToken;
import com.example.contactapp.entity.Contact;

public class AppAuthRequest implements AuthRequest {

    @Override
    public AuthToken authRequest(RequestContext rc) {
        // Note for this sample app, we store the user in the session, 
        // but for production application, use stateless authentication mechanism for 
        // better sclability.
        
        // RequestContext is a convenient Snow wrapper on top of HttpServletRequest, 
        //  and HttpServletResponse


        return null;
    }

}
