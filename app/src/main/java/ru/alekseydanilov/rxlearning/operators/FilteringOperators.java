package ru.alekseydanilov.rxlearning.operators;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public class FilteringOperators {

    /**
     * Оператор take - возьмёт первое количество заданных элементов
     */
    public void initTake() {
        Log.d("Take", "Take start");

        Observable<Integer> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5})
                .take(3);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("Take", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Take", "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d("Take", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Оператор skip - пропустит заданное количество первых элементов
     */
    public void initSkip() {
        Log.d("Skip", "Skip start");

        Observable<Integer> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5})
                .skip(2);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("Skip", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Skip", "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d("Skip", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Оператор Distinct - отсеет дубликаты
     */
    public void initDistinct() {
        Log.d("Distinct", "Distinct start");

        Observable<Integer> observable = Observable
                .from(new Integer[]{5, 9, 7, 5, 8, 6, 7, 8, 9})
                .distinct();

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("Distinct", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Distinct", "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d("Distinct", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Оператор filter отсеет только нужные элементы по описаннному алгоритму фильтрации.
     */
    public void initFilter() {
        Log.d("Filter", "Filter start");

        // Описываем алгоритм фильтрации
        Func1<String, Boolean> filterFiveOnly = new Func1<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                return s.contains("5");
            }
        };

        Observable<String> observable = Observable
                .from(new String[]{"15", "27", "34", "46", "52", "63"})
                .filter(filterFiveOnly);

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("Filter", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("Filter", "onError: " + e);
            }

            @Override
            public void onNext(String s) {
                Log.d("Filter", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }
}
