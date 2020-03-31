package ru.alekseydanilov.rxlearning.errorexample;

import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

/**
 * Created by adanilov on 30,March,2020
 * <p>
 * Класс, который содержит примеры разных видов обработчиков ошибок в RxJava
 */
public class ErrorExample {

    /**
     * Иницализация onErrorReturn
     * Оператор onErrorReturn позволит перехватить ошибку и вместо неё передать значение.
     */
    public void onErrorReturnExample() {
        Observable<String> stringData = Observable.just("1", "2", "a", "4", "5");
        Observable<Long> observable = stringData.map(new Func1<String, Long>() {
            @Override
            public Long call(String s) {
                return Long.parseLong(s);
            }
        }).onErrorReturn(new Func1<Throwable, Long>() {
            @Override
            public Long call(Throwable throwable) {
                Log.e("onErrorReturnExample", "onErrorReturn " + throwable.getMessage());
                return 0L;
            }
        });

        observable.subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d("onErrorReturnExample", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onErrorReturnExample", "onError " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d("onErrorReturnExample", "onNext " + aLong);
            }
        });
    }

    /**
     * Иницализация onErrorResumeNext.
     * Позволяет вместо ошибки отправить в Observer не одно значение, а несколько - в виде Observable.
     */
    public void onErrorResumeNextExample() {
        Observable<String> stringData = Observable.just("1", "2", "a", "4", "5");
        Observable<Long> observable = stringData.map(new Func1<String, Long>() {
            @Override
            public Long call(String s) {
                return Long.parseLong(s);
            }
        }).onErrorResumeNext(Observable.just(8L, 9L, 10L));

        observable.subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d("onErrorResumeNext", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onErrorResumeNext", "onError " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d("onErrorResumeNext", "onNext " + aLong);
            }
        });
    }

    /**
     * Иницализация примера retry.
     * Retry используется для перезапуска Observable.
     * В случае ошибки, ваш Observer может сам еще раз подписаться и попробовать получить данные.
     * По умолчанию retry повторяет попытку бесконечное количество раз.
     * Чтобы избежать бесконечных повторов, мы можем явно указать оператору retry число попыток
     */
    public void retryExample() {
        Observable<String> stringData = Observable.just("1", "2", "a", "4", "5");
        Observable<Long> observable = stringData.map(new Func1<String, Long>() {
            @Override
            public Long call(String s) {
                return Long.parseLong(s);
            }
        }).retry(2);

        observable.subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d("retryExample", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e("retryExample", "onError " + e);
            }

            @Override
            public void onNext(Long aLong) {
                Log.d("retryExample", "onNext " + aLong);
            }
        });
    }
}
