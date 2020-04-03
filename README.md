# RxLearning
Проект, в котором рассматриваются основные операторы RxJava:

### Операторы создания
- from
- range
- interval
- callable

### Операторы преобразования
- map
- buffer
 
### Операторы фильтрации
- take
- skip
- distinct
- filter

### Операторы объединения
- merge
- concat
- amb
- zip
- combineLatest

### Операторы условий
- takeUntil
- all

## Приведены примеры Subject:

- Subject без опций
- Replay Subject
- Behavior Subject
- Async Subject
- Unicast Subject

## Приведены примеры обработок ошибок:
 
 - При помощи onErrorReturn
 - При помощи  onErrorResumeNext
 - При помощи retry
 
## Приведены примеры тестов RxJava

- assertValues - проверка, что подписчик получил указанные данные
- assertValueCount - проверка количества полученных данных
- assertNoValues - проверка, что данных не было.
- assertNoErrors - проверка, что не было ошибок.
- assertError - проверка, что приходила ошибка определенного типа.
- awaitTerminalEvent - позволяет дождаться окончания работы Observable.
 
Также в данном проекте есть пример Cold Observable и Hot Observable