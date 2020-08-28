package com.app;

import com.injector.Inject;

public class AppRunnerImpl implements AppRunner{

    @Inject
    private Service service;

    @Inject
    private NotasService serviceNotas;

    public void print(){
        service.print();
    }

    public void printNota(){
        System.out.println(serviceNotas.getNota());
    }
}
