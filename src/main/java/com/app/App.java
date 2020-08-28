package com.app;

import com.injector.Injector;

public class App {
    public static void main(String[] args){
        Injector injector = new Injector();
        injector.bind(AppRunner.class,AppRunnerImpl.class);
        injector.bind(NotasService.class,NotasServiceImpl.class);
        injector.bind(Service.class,ServiceImpl.class);
        AppRunner run = (AppRunner) injector.getIntance(AppRunner.class);
        run.print();
        run.printNota();
        Service s = (Service) injector.getIntance(ServiceImpl.class);
        s.print();
    }
}
