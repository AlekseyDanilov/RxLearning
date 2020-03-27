package ru.alekseydanilov.rxlearning;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CreatingOperators creatingOperators = new CreatingOperators();
    TransformingOperators transformingOperators = new TransformingOperators();
    FilteringOperators filteringOperators = new FilteringOperators();
    CombiningOperators combiningOperators = new CombiningOperators();
    ConditionalOperators conditionalOperators = new ConditionalOperators();

    ColdObservableExample coldObservableExample = new ColdObservableExample();
    HotObservableExample hotObservableExample = new HotObservableExample();

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
        combiningOperators.initZip(); // zip

        // Примеры операторов условий
        conditionalOperators.initTakeUntil(); // takeUntil
        conditionalOperators.initAll(); // all

        // Пример реализации ColdObservable
        coldObservableExample.initColdObservableExample();

        // Пример реализации HotObservable
        hotObservableExample.iniHotObservableExample();
    }


}
