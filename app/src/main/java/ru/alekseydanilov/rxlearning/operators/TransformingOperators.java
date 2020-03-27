package ru.alekseydanilov.rxlearning.operators;

import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by adanilov on 27,March,2020
 * <p>
 * Операторы преобразования
 */
public class TransformingOperators {

    /**
     * Оператор map преобразует все элементы последовательности
     */
    public void initMapOperator() {
        Log.d("MapOperator", "MapOperator start");

        // Сперва необходимо написать функцию преобразования
        Func1<String, Integer> stringToInteger = new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return Integer.parseInt(s);
            }
        };

        Observable<Integer> observable = Observable
                .from(new String[]{"1", "2", "3", "4", "5"})
                .map(stringToInteger);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("MapOperator", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("MapOperator", "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d("MapOperator", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Оператор buffer собирает элементы и по мере накопления заданного кол-ва отправляет их дальше одним пакетом.
     *
     * @param count - количество накапливаемых элементов
     */
    public void initBufferOperator(int count) {
        Log.d("BufferOperator", "BufferOperator start");

        Observable<List<Integer>> observable = Observable
                .from(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8})
                .buffer(count);

        Observer<List<Integer>> observer = new Observer<List<Integer>>() {
            @Override
            public void onCompleted() {
                Log.d("BufferOperator", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("BufferOperator", "onError: " + e);
            }

            @Override
            public void onNext(List<Integer> s) {
                Log.d("BufferOperator", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }
}
