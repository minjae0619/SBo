package com.mj.sbo.event.handler;


import com.mj.sbo.event.Event;
import com.mj.sbo.event.JoinEvent;
import com.mj.sbo.event.QuitEvent;
import com.mj.sbo.objects.Bot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class Handler {
    private static final int MAX_THREAD_POOL = 5;


    private static List<Listener> listeners = new CopyOnWriteArrayList<Listener>();

    private static synchronized List<Listener> getListeners() {
        return listeners;
    }

    public static synchronized void addListener(Listener eventListener) {
        if (!getListeners().contains(eventListener)) {
            listeners.add(eventListener);
        }
    }
    public static synchronized void removeLastListener() {
        if(listeners.size() == 1)
            return;
        listeners.remove(listeners.size()-1);
    }

    public static synchronized void removeListener(Listener eventListener) {
        if (getListeners().contains(eventListener)) {
            listeners.remove(eventListener);
        }
    }

    public static synchronized void callEvent(Event event) {
        callEvent(event, true);
    }

    public static synchronized void callEvent(Event event, boolean doAsynch) {
        if (doAsynch) {
            callEventByAsynch(event);
        } else {
            callEventBySynch(event);
        }
    }

    private static synchronized void callEventByAsynch(Event event) {
        ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD_POOL);
        for (final Listener listener : listeners) {
            executorService.execute(new Runnable() {
                public void run() {
                    Class<?> listenerClass = listener.getClass();
                    Method[] listenerMethods = listenerClass.getMethods();
                    for(Method method : listenerMethods){
                        if(!method.isAnnotationPresent(EventHandler.class))
                            continue;
                        Class<?> eventClass = event.getClass();
                        Class<?>[] parameterTypes = method.getParameterTypes();
                        if(parameterTypes.length == 0)
                            continue;
                        if(!parameterTypes[0].equals(eventClass))
                            continue;
                        try {
                            System.out.println(event.getClass());
                            method.invoke(listener, event);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }
        if(event instanceof JoinEvent)
             Bot.sendMessage(((JoinEvent) event).getJoinMessage());


        executorService.shutdown();
    }

    private static synchronized void callEventBySynch(Event event) {

        for (final Listener listener : listeners) {
            Class<?> listenerClass = listener.getClass();
            Method[] listenerMethods = listenerClass.getMethods();
            for(Method method : listenerMethods){
                if(!method.isAnnotationPresent(EventHandler.class))
                    continue;
                Class<?> eventClass = event.getClass();
                Class<?>[] parameterTypes = method.getParameterTypes();
                if(parameterTypes.length == 0)
                    continue;
                if(!parameterTypes[0].equals(eventClass))
                    continue;
                try {
                    method.invoke(listener, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
