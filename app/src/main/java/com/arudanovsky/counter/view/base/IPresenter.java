package com.arudanovsky.counter.view.base;

/**
 * Created by arudanovskiy on 8/6/17.
 * Базовый интерфейс для всех презентеров
 * Тут описаны методы, которые должен иметь каждый презентер
 */

public interface IPresenter {
    /**
     * Этим методом мы инициируем начало работы презентера.
     * Например подгрузку данных.
     */
    void subscribe();

    /**
     * С помощью этого метода, мы привязываем презентер к фрагменту
     * имплементирующему интерфейс {@link IView}
     * @param view является экземпляром фрагмента
     */
    void onCreate(IView view);

    /**
     * Используем этот метод, чтобы завершить работу презентера.
     * Например: закрыть инстанс базы данных, если таковая имеется
     */
    void unsubscribe();

    /**
     * Общий метод обработки ошибок
     * Реализован в {@link BasePresenter#handleError(Throwable)}
     * @param throwable инстанс ошибки, которую нужно обработать
     */
    void handleError(Throwable throwable);
}
