package ru.alekseydanilov.rxlearning.subjectexample;

import android.util.Log;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.UnicastSubject;

/**
 * Created by adanilov on 30,March,2020
 * <p>
 * Subject - это объект, который является одновременно и Observer и Observable.
 * Это значит, что он может подписываться на Observable и получать от них данные,
 * а также, у него могут быть подписчики, которым он эти данные будет пересылать.
 */
public class SubjectExample {

    /**
     * Инициализация Subject, без каких-либо опций.
     * Принимает данные и отдает их всем текущим подписчикам.
     */
    public void initPublishSubjectExample() {

        Observer<Long> observer1 = createObserver("PublishSubject", 1);
        Observer<Long> observer2 = createObserver("PublishSubject", 2);

        final Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS)
                .take(10);

        final PublishSubject<Long> subject = PublishSubject.create();
        Log.d("PublishSubject", "subject subscribe");
        observable.subscribe(subject);

        try {
            Thread.sleep(2500);
            Log.d("PublishSubject", "observer1 subscribe");
            subject.subscribe(observer1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(4500);
            Log.d("PublishSubject", "observer2 subscribe");
            subject.subscribe(observer2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализация Replay Subject.
     * Replay Subject - имеет буфер, который хранит данные и передает их подписчикам в момент подписки.
     * У ReplySubject есть несколько разновидностей метода create,
     * где вы можете указать кол-во хранимых в буфере элементов или время хранения.
     * <p>
     * Методы ReplySubject, связанные с хранимыми данными:
     * getValue - получить последний элемент
     * getValues - получить все хранимые данные
     * hasAnyValue - хранит ли Subject какие-либо данные
     * size - кол-во хранимых данных
     */
    public void initReplaySubjectExample() {

        Observer<Long> observer1 = createObserver("ReplaySubject", 1);
        Observer<Long> observer2 = createObserver("ReplaySubject", 2);

        final Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).take(10);
        final ReplaySubject<Long> subject = ReplaySubject.create();
        Log.d("ReplaySubject", "subject subscribe");
        observable.subscribe(subject);

        try {
            Thread.sleep(2500);
            Log.d("ReplaySubject", "observer1 subscribe");
            subject.subscribe(observer1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Методы replay subject:
        Log.d("getValue", subject.getValue().toString());
        Log.d("getValues", Arrays.toString(subject.getValues()));
        Log.d("hasAnyValue", String.valueOf(subject.hasAnyValue()));
        Log.d("size", String.valueOf(subject.size()));

        try {
            Thread.sleep(4500);
            Log.d("ReplaySubject", "observer2 subscribe");
            subject.subscribe(observer2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализация Behavior Subject.
     * BehaviorSubject - это ReplySubject с размером буфера= 1 и возможностью указать начальный элемент.
     */
    public void initBehaviorSubjectExample() {

        Observer<Long> observer1 = createObserver("BehaviorSubject", 1);
        Observer<Long> observer2 = createObserver("BehaviorSubject", 2);

        final Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS).take(10);
        final BehaviorSubject<Long> subject = BehaviorSubject.create(-1L);
        Log.d("BehaviorSubject", "observer1 subscribe");
        subject.subscribe(observer1);

        try {
            Thread.sleep(2500);
            Log.d("ReplaySubject", "subject subscribe");
            observable.subscribe(subject);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(6500);
            Log.d("ReplaySubject", "observer2 subscribe");
            subject.subscribe(observer2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализация Async Subject.
     * Выдает только последнее значение и только в момент, когда последовательность завершена.
     * Этот Subject подходит для проверки выполнения задачи, которая вернет только один результат.
     * Любой подписчик сможет получить этот результат, даже если задача уже выполнена.
     * У AsyncSubject есть методы:
     * hasValue() - приходил уже результат или еще нет
     * getValue() - получить результат
     */
    public void initAsyncSubjectExample() {

        Observer<Long> observer1 = createObserver("BehaviorSubject", 1);
        Observer<Long> observer2 = createObserver("BehaviorSubject", 2);

        final Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS)
                .take(4);

        final AsyncSubject<Long> subject = AsyncSubject.create();
        Log.d("AsyncSubject", "subject subscribe");
        observable.subscribe(subject);

        try {
            Thread.sleep(2500);
            Log.d("AsyncSubject", "observer1 subscribe");
            subject.subscribe(observer1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Методы async subject:
        Log.d("getValue", subject.getValue().toString());
        Log.d("hasValue", String.valueOf(subject.hasValue()));

        try {
            Thread.sleep(4500);
            Log.d("AsyncSubject", "observer2 subscribe");
            subject.subscribe(observer2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Инициализация UnicastSubject.
     * Subject, на который можно подписать лишь одного получателя.
     * Даже после того как получатель отписался, никто больше не сможет подписаться.
     */
    public void initUnicastSubjectExample() {

        Observer<Long> observer1 = createObserver("UnicastSubject", 1);
        Observer<Long> observer2 = createObserver("UnicastSubject", 2);

        final Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS)
                .take(20);
        final UnicastSubject<Long> subject = UnicastSubject.create();
        Log.d("UnicastSubject", "subject subscribe");
        observable.subscribe(subject);

        Log.d("UnicastSubject", "observer1 subscribe");
        Subscription subscription1 = subject.subscribe(observer1);

        try {
            Thread.sleep(2500);
            Log.d("UnicastSubject", "observer2 subscribe");
            subject.subscribe(observer2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(2500);
            Log.d("UnicastSubject", "observer1 unsubscribe");
            subscription1.unsubscribe();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(2500);
            Log.d("UnicastSubject", "observer2 subscribe");
            subject.subscribe(observer2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для создания observer, чтобы не создавать каждый раз во всех примерах
     *
     * @param tag            -  тэг для логов, из какого именно метода мы вызываем создание observer
     * @param numberObserver - номер observer, необходим для логов, чтобы было понятно, какой из Observer инициализирован
     * @return возвращает Observer<Long>
     */
    private Observer<Long> createObserver(String tag, int numberObserver) {
        return new Observer<Long>() {
            @Override
            public void onCompleted() {
                Log.d(tag, "observer " + numberObserver + " onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                Log.e(tag, "Error in observer " + numberObserver + " : " + e.getMessage());
            }

            @Override
            public void onNext(Long aLong) {
                Log.d(tag, ("observer " + numberObserver + " onNext value = " + aLong));
            }
        };
    }
}
