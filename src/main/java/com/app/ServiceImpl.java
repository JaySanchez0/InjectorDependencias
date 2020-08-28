package com.app;

import com.injector.Inject;

public class ServiceImpl implements Service{

    @Inject
    private AppRunner runner;

    @Override
    public void print() {
        System.out.println("Hola Mundo");
    }
}
