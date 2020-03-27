package ru.alekseydanilov.rxlearning;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.observables.ConnectableObservable;

/**
 * Пример Hot Observable. Основные приницпы Hot Observable:
 * 1. Hot Observable генерирует данные независимо от подписчиков;
 * 2. Все подписчики получают одни и те же данные в одно и то же время;
 */
public class HotObservableExample {

    private final String TAG = "HotObservableExample";

    void iniHotObservableExample() {
        Log.d(TAG, "init Hot Observable");

        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Hot observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "Hot observer1 onNext value = " + aLong);
            }
        };


        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "Hot observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "Hot observer2 onNext value = " + aLong);
            }
        };

        final ConnectableObservable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS)
                .take(11)
                .publish();

        Log.d(TAG, "Hot observable connect");

        observable.connect();

        try {
            Thread.sleep(3000);
            Log.d(TAG, "Hot observable subscribe observer1");
            observable.subscribe(observer1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(6500);
            Log.d(TAG, "Hot observable subscribe observer2");
            observable.subscribe(observer2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
