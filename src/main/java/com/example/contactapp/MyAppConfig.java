package com.example.contactapp;

import com.google.inject.AbstractModule;


public class MyAppConfig extends AbstractModule {

    @Override
    protected void configure() {
        System.out.println("success");
    }

}
