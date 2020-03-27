package ru.alekseydanilov.rxlearning;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;

/**
 * Created by adanilov on 27,March,2020
 * <p>
 * Пример Cold Observable. Основные приницпы Cold Observable:
 * 1. Созданный Observable ничего не будет делать до тех пор пока на него кто-нибудь не подпишется.
 * 2. Для каждого нового подписчика он будет начинать работу заново, независимо от предыдущих подписчиков.
 */
public class ColdObservableExample {

    private final String TAG = "ColdObservableExample";

    void initColdObservableExample() {
        Log.d(TAG, "init Cold Observable");

        final Observer<Long> observer1 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer1 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer1 onNext value = " + aLong);
            }
        };


        final Observer<Long> observer2 = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "observer2 onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Error: " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(TAG, "observer2 onNext value = " + aLong);
            }
        };

        final Observable<Long> observable = Observable
                .interval(1, TimeUnit.SECONDS).take(5);

        try {
            Thread.sleep(3000);
            observable.subscribe(observer1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(5500);
            observable.subscribe(observer2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
