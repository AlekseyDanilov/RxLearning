package ru.alekseydanilov.rxlearning;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.observers.TestSubscriber;

/**
 * Примеры тестов RxJava
 */
public class ExampleRxTest {

    private TestSubscriber<Integer> testObserver = new TestSubscriber<>();


    /**
     * Пример работы метода assertValues - проверка, что подписчик получил указанные данные.
     */
    @Test
    public void exampleAssertValues() {
        Observable.just(1, 2, 3, 4, 5).subscribe(testObserver);
        testObserver.assertValues(1, 2, 3, 4, 5);
    }

    /**
     * Пример работы метода assertValueCount - проверка количества полученных данных.
     */
    @Test
    public void exampleAssertValueCount() {
        Observable.just(1, 2, 3, 4, 5).subscribe(testObserver);
        testObserver.assertValueCount(5);
    }

    /**
     * Пример работы метода assertNoValues - проверка, что данных не было.
     */
    @Test
    public void exampleAssertNoValues() {
        Observable.<Integer>empty().subscribe(testObserver);
        testObserver.assertNoValues();
    }

    /**
     * Пример работы метода assertNoErrors - проверка, что не было ошибок.
     */
    @Test
    public void exampleAssertNoErrors() {
        Observable.just("1", "2", "3", "4", "5").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return Integer.parseInt(s);
            }
        }).subscribe(testObserver);
        testObserver.assertNoErrors();
    }

    /**
     * Пример работы метода assertError - проверка, что приходила ошибка определенного типа.
     */
    @Test
    public void exampleAssertError() {
        Observable.just("1", "2", "3", "a", "5").map(new Func1<String, Integer>() {
            @Override
            public Integer call(String s) {
                return Integer.parseInt(s);
            }
        }).subscribe(testObserver);
        testObserver.assertValues(1, 2, 3);
        testObserver.assertError(NumberFormatException.class);
    }

    /**
     * Пример работы метода awaitTerminalEvent - позволяет дождаться окончания работы Observable.
     * Во второй версии Rx - был заменён на await()
     */
    @Test
    public void exampleAwaitTerminalEvent() {
        TestSubscriber<Long> longTestObserver = new TestSubscriber<>();
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(10)
                .subscribe(longTestObserver);

        longTestObserver.awaitTerminalEvent();
        longTestObserver.assertCompleted();
    }

    /**
     * Пример работы метода awaitTerminalEvent с параметром -
     * ждёт указанное в параметре время, и только потом проверяет.
     * Во второй версии Rx - был заменён на await()
     */
    @Test
    public void exampleAwaitTerminalEventWithTimeout() {
        TestSubscriber<Long> longTestObserver = new TestSubscriber<>();
        Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(4)
                .subscribe(longTestObserver);

        longTestObserver.awaitTerminalEvent(500, TimeUnit.MILLISECONDS);
        longTestObserver.assertCompleted();
    }

}