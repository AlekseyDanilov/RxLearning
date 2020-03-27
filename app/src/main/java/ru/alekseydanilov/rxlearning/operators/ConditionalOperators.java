package ru.alekseydanilov.rxlearning.operators;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public class ConditionalOperators {

    /**
     * Оператор takeUntil будет брать элементы пока не попадется элемент, удовлетворяющий определенному условию.
     */
    public void initTakeUntil() {
        Log.d("TakeUntil", "TakeUntil start");

        // Заданное нами условие
        Func1<Integer, Boolean> isFive = new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer i) {
                return i == 5;
            }
        };

        Observable<Integer> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8})
                .takeUntil(isFive);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("TakeUntil", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("TakeUntil", "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d("TakeUntil", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Оператор all позволяет узнать все ли элементы удовлетворяют указанному условию.
     * Условие нам необходимо оформить в виде функции.
     */
    public void initAll() {
        Log.d("All", "All start");

        // Функция проверки условия
        Func1<Integer, Boolean> lessThanTen = new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer i) {
                return i < 10;
            }
        };

        Observable<Boolean> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8})
                .all(lessThanTen);

        Observer<Boolean> observer = new Observer<Boolean>() {
            @Override
            public void onCompleted() {
                Log.d("All", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("All", "onError: " + e);
            }

            @Override
            public void onNext(Boolean s) {
                Log.d("All", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }
}
