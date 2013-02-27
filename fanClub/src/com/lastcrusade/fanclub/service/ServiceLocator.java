package com.lastcrusade.fanclub.service;

import com.lastcrusade.fanclub.service.AudioPlayerService.AudioPlayerBinder;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * A generic class for launching/locating services.  As a general pattern, create locators
 * in onCreate or onResume for any services you may need in that activity.  The binding/launching process
 * happens in the message loop, so you must return from the on* method for the service to actually launch.
 * After the service is bound, use getService to get the service object.
 * 
 * @author Jesse Rosalia
 *
 * @param <T>
 */
public class ServiceLocator<T extends Service> implements ServiceConnection {

    private T service;
    private boolean bound;
    private Class<? extends ILocalBinder<T>> serviceBinderClass;
    private Class<T> serviceClass;

    public ServiceLocator(Context context, Class<T> serviceClass, Class<? extends ILocalBinder<T>> binderClass) {
        this.serviceClass = serviceClass;
        this.serviceBinderClass = binderClass;
        Intent intent = new Intent();
        intent.setClass(context, serviceClass);
        context.bindService(intent, this, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder iservice) {
        service = serviceBinderClass.cast(iservice).getService();
        bound = true;
    }

    public void onServiceDisconnected(ComponentName className) {
        service = null;
        bound = false;
    }
    
    public T getService() throws ServiceNotBoundException {
        if (!this.bound) {
            throw new ServiceNotBoundException(this.serviceClass);
        }
        return this.service;
    }

}