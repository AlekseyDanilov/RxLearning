package ru.alekseydanilov.rxlearning;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import ru.alekseydanilov.rxlearning.errorexample.ErrorExample;
import ru.alekseydanilov.rxlearning.operators.CombiningOperators;
import ru.alekseydanilov.rxlearning.operators.ConditionalOperators;
import ru.alekseydanilov.rxlearning.operators.CreatingOperators;
import ru.alekseydanilov.rxlearning.operators.FilteringOperators;
import ru.alekseydanilov.rxlearning.operators.TransformingOperators;
import ru.alekseydanilov.rxlearning.subjectexample.SubjectExample;

/**
 * Created by adanilov on 27,March,2020
 */
public class MainActivity extends AppCompatActivity {

    CreatingOperators creatingOperators = new CreatingOperators();
    TransformingOperators transformingOperators = new TransformingOperators();
    FilteringOperators filteringOperators = new FilteringOperators();
    CombiningOperators combiningOperators = new CombiningOperators();
    ConditionalOperators conditionalOperators = new ConditionalOperators();

    ColdObservableExample coldObservableExample = new ColdObservableExample();
    HotObservableExample hotObservableExample = new HotObservableExample();

    SubjectExample subjectExample = new SubjectExample();

    ErrorExample errorExample = new ErrorExample();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Примеры операторов создания
        creatingOperators.initFromArray(); // from
        creatingOperators.initFromRange(10, 4); // range
        creatingOperators.initFromInterval(500); // interval
        creatingOperators.initFromCallable(); // callable

        // Примеры операторов преобразования
        transformingOperators.initMapOperator(); // map
        transformingOperators.initBufferOperator(3); // buffer

        // Примеры операторов фильтрации
        filteringOperators.initTake(); //take
        filteringOperators.initSkip();  //skip
        filteringOperators.initDistinct(); // distinct
        filteringOperators.initFilter(); // filter

        // Примеры операторов объединений
        combiningOperators.initMerge(); // merge
        combiningOperators.initConcatExample(); // concat
        combiningOperators.initAmbExample(); // amb
        combiningOperators.initZip(); // zip
        combiningOperators.combineLatestExample(); // combineLatest

        // Примеры операторов условий
        conditionalOperators.initTakeUntil(); // takeUntil
        conditionalOperators.initAll(); // all

        // Пример реализации ColdObservable
        coldObservableExample.initColdObservableExample();

        // Пример реализации HotObservable
        hotObservableExample.iniHotObservableExample();

        // Примеры Subject
        subjectExample.initPublishSubjectExample(); // PublishSubject
        subjectExample.initReplaySubjectExample(); // ReplaySubject
        subjectExample.initBehaviorSubjectExample(); // BehaviorSubject
        subjectExample.initAsyncSubjectExample(); // AsyncSubject
        subjectExample.initUnicastSubjectExample(); // UnicastSubject

        // Примеры обработки ошибок
        errorExample.onErrorReturnExample();
        errorExample.onErrorResumeNextExample();
        errorExample.retryExample();
    }
}
