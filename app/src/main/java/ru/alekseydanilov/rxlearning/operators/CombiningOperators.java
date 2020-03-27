package ru.alekseydanilov.rxlearning.operators;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.functions.Func2;

/**
 * Created by adanilov on 27,March,2020
 * <p>
 * Операторы объединения
 */
public class CombiningOperators {

    /**
     * Оператор merge объединит элементы из двух Observable в один
     */
    public void initMerge() {
        Log.d("Merge", "Merge start");

        Observable<Integer> observable = Observable
                .from(new Integer[]{1, 2})
                .mergeWith(Observable.from(new Integer[]{6, 7}));

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("Merge", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Merge", "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d("Merge", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Оператор zip попарно сопоставит элементы из двух Observable.
     * Из каждой пары элементов с помощью функции будет получен один элемент, который будет добавлен в итоговый Observable.
     */
    public void initZip() {
        Log.d("Zip", "Zip start");

        // Описываем логику объединения
        Func2<Integer, String, String> zipIntWithString = new Func2<Integer, String, String>() {
            @Override
            public String call(Integer i, String s) {
                return s + ": " + i;
            }
        };

        Observable<String> observable = Observable
                .from(new Integer[]{1, 2, 3})
                .zipWith(Observable.from(new String[]{"One", "Two", "Three"}), zipIntWithString);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("Zip", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Zip", "onError: " + e);
            }

            @Override
            public void onNext(String s) {
                Log.d("Zip", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }
}
