package ru.alekseydanilov.rxlearning.operators;

import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class CreatingOperators {

    /**
     * Создаёт observable из массива
     */
    public void initFromArray() {
        Log.d("FromArray", "initFromArray start");

        Observable<String> observable = Observable.from(new String[]{"one", "two", "three"});

        Observer<String> observer = new Observer<String>() {

            @Override
            public void onError(Throwable e) {
                Log.d("FromArray", "onError: " + e);
            }

            @Override
            public void onCompleted() {
                Log.d("FromArray", "onCompleted");
            }

            @Override
            public void onNext(String s) {
                Log.d("FromArray", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Создаёт observable из последовательности чисел
     *
     * @param start - начальное значение int
     * @param count - количество элементов
     */
    public void initFromRange(int start, int count) {
        Log.d("FromRange", "initFromRange start");
        Observable<Integer> observable = Observable.range(start, count);

        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onCompleted() {
                Log.d("FromRange", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("FromRange", "onError: " + e);
            }

            @Override
            public void onNext(Integer s) {
                Log.d("FromRange", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Оператор interval выдает последовательность long чисел начиная с 0
     * onComplete выполнится только когда число long дойдёт до максимального значения
     * (В данном случае добавляется условие takeWhile при котором,
     * как только число будет больше 5 метод будет завершён и вызывается onCompleted)
     *
     * @param period - значение временного интервала, через который числа будут приходить
     */
    public void initFromInterval(long period) {
        Log.d("FromInterval", "initFromInterval start");

        final Observable<Long> observable = Observable.interval(period, TimeUnit.MILLISECONDS)
                .takeWhile(val -> val < 6);

        final Observer<Long> observer = new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d("FromInterval", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.d("FromInterval", "onError: " + e);
            }

            @Override
            public void onNext(Long s) {
                Log.d("FromInterval", "onNext: " + s);
            }
        };

        observable.subscribe(observer);
    }

    /**
     * Оператор fromCallable поможет вам сделать синхронный метод - асинхронным
     */
    public void initFromCallable() {
        Log.d("FromCallable", "initFromCallable start");

        Observable.fromCallable(new CallableLongAction("5"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d("FromCallable", "onNext: " + integer);
                    }
                });
    }

    /**
     * Метод, выполнение которого долго ждать
     *
     * @param text - текст, который будет преобразован в int
     * @return - возвращает преобразованное значение
     */
    private int longAction(String text) {
        Log.d("LongAction", "longAction");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(text);
    }

    class CallableLongAction implements Callable<Integer> {
        private final String data;

        CallableLongAction(String data) {
            this.data = data;
        }

        @Override
        public Integer call() throws Exception {
            return longAction(data);
        }
    }

}
