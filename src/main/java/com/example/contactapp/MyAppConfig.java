package com.example.contactapp;

import com.britesnow.snow.util.PackageScanner;
import com.britesnow.snow.web.auth.AuthRequest;
import com.britesnow.snow.web.binding.EntityClasses;
import com.example.contactapp.entity.BaseEntity;
import com.example.contactapp.web.AppAuthRequest;
import com.example.contactapp.web.ContactDaoWebHandlers;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyAppConfig extends AbstractModule {

//    @Override
//    protected void configure() {
//        System.out.println("success");
//    }

//    private static Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Override
    protected void configure() {
        System.out.println("success");
        //bind(AuthRequest.class).to(AppAuthRequest.class);
    }


    // Used by the Snow Hibernate helpers to inject the entity class
    // Just need to provide the @EntityClasses
    @Provides
    @Singleton
    @EntityClasses
    public Class[] provideEntityClasses() {
        // The simplest implementation, would be to hardcode like
        // return new Class[]{com.example.samplebookmarks.entity.User.class,
        //                    com.example.samplebookmarks.entity.Item.class};

        // However, with few more line of code, we can have a maintenance free implementation
        // by scanning the application entity.* java package.
        try {
            return new PackageScanner(BaseEntity.class.getPackage().getName()).findAnnotatedClasses(javax.persistence.Entity.class);
        } catch (Throwable e) {
            //log.error(e.getMessage(), e);
            throw new RuntimeException("Cannot get all the enity class: " + e.getMessage());
        }

    }
}
