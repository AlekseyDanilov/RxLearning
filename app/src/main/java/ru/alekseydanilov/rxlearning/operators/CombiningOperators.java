package ru.alekseydanilov.rxlearning.operators;

import android.util.Log;

import java.util.concurrent.TimeUnit;

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
     * Оператор merge объединит элементы из двух Observable в один.
     * При этом элементы из двух Observable идут параллельно друг другу.
     * Когда оба потока закончатся, мы получим onCompleted.
     * <p>
     * Метод merge позволяет задать maxConcurrent.
     * maxConcurrent - это количество одновременно и параллельно работающих Observable
     * <p>
     * Если задать maxConcurrent =  1, то мы подписываемся на один Observable, ждем пока он
     * не закончится, затем подписываемся на второй Observable.
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
     * Оператор concat представляет собой merge c maxConcurrent = 1.
     * Он всегда последовательно запускает переданные ему Observable.
     */
    public void initConcatExample() {
        Observable<Long> observable1 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(10);

        Observable<Long> observable2 = Observable.interval(500, TimeUnit.MILLISECONDS)
                .take(10)
                .map(aLong -> aLong + 100);

        observable1.concatWith(observable2).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d("Concat", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("Concat", "Error : " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                Log.d("Concat", "onNext " + aLong);
            }
        });
    }

    /**
     * Оператор amb из нескольких Observable выбирает того, кто первый пришлет данные.
     * Далее, оператор будет возвращать элементы только из выбранного Observable.
     */
    public void initAmbExample() {

        Observable<Long> observable1 = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .take(10);

        Observable<Long> observable2 = Observable.interval(700, TimeUnit.MILLISECONDS)
                .take(10)
                .map(aLong -> aLong + 100);

        Observable.amb(observable1, observable2).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d("amb", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("amb", "Error: " + e.getMessage());
            }

            @Override
            public void onNext(Long next) {
                Log.d("amb", "onNext " + next);
            }
        });
    }

    /**
     * Оператор zip попарно сопоставит элементы из двух Observable.
     * Из каждой пары элементов с помощью функции будет получен один элемент, который будет добавлен в итоговый Observable.
     * <p>
     * Когда элементы закончатся в одном из Observable, то zip прекратит свою работу.
     * Итоговое количество элементов из zip равно количеству элементов самого малого Observable.
     * <p>
     * Так как zip ждёт самый медленный Observable, чтобы собрать элементы из n Observable в один,
     * то с помощью zip можно сделать паузу между элементами вашего основного Observable.
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

    /**
     * Оператор combineLatest берет элементы из нескольких Observable и собирает из них один.
     * При этом отличается тем, что не ждет, когда придет самый медленный элемент пары,
     * а берёт последние полученные элементы с каждого Observable каждый раз когда приходит
     * новый элемент из любого Observable.
     */
    public void combineLatestExample() {

        Observable<Long> observable1 = Observable.interval(300, TimeUnit.MILLISECONDS).take(10);

        Observable<Long> observable2 = Observable.interval(500, TimeUnit.MILLISECONDS).take(10)
                .map(aLong -> aLong + 100);

        Observable.combineLatest(observable1, observable2, new Func2<Long, Long, String>() {
            @Override
            public String call(Long aLong, Long aLong2) {
                return String.format("%s and %s", aLong, aLong2);
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d("combineLatest", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("combineLatest", "Error: " + e.getMessage());
            }

            @Override
            public void onNext(String next) {
                Log.d("combineLatest", "onNext " + next);
            }
        });
    }


}
