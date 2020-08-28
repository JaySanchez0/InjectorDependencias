package com.injector;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class Injector {
    private ArrayList<Class<?>[]> clases = new ArrayList<>();
    private ArrayList<Object> objects = new ArrayList<>();
    public Injector(){

    }

    private boolean exist(Class<?> interfaceApp){
        for(Class<?>[] pair:clases){
            if(pair[0].equals(interfaceApp)) return true;
        }
        return false;
    }

    private boolean implementsInterface(Class<?> classApp,Class<?> interfaceApp){
        for(Class<?> inte: classApp.getInterfaces()){
            if(inte.equals(interfaceApp)) return true;
        }
        return false;
    }

    public void bind(Class<?> interfaceApp,Class<?> classApp){
        if(!exist(interfaceApp) && interfaceApp.isInterface() && implementsInterface(classApp,interfaceApp)){
            try {
                clases.add(new Class<?>[]{interfaceApp,classApp});
                objects.add(classApp.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private Object getClassImplement(Class<?> interfaceApp){
        int i=0;
        for(Class<?>[] pair:clases){
            if(pair[0].equals(interfaceApp)) return objects.get(i);
            i+=1;
        }
        return null;
    }

    private void injectionObject(Object obj){
        try{
            for(Field field:obj.getClass().getDeclaredFields()){
                field.setAccessible(true);
                if(field.getAnnotation(Inject.class)!=null && field.getType().isInterface() && field.get(obj)==null && exist(field.getType())){
                    Object inst = getClassImplement(field.getType());
                    field.set(obj,inst);
                    //System.out.println(field.get(obj));
                    injectionObject(inst);
                }
        }}catch (Exception e){
            e.printStackTrace();
        }

    }

    public Object getIntance(Class<?> classApp){
        try {
            Object obj;
            if(classApp.isInterface()) obj = getClassImplement(classApp);
            else obj =  classApp.newInstance();
            injectionObject(obj);
            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
