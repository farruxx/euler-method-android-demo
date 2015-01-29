package com.farruxbahodirov.euler;

/**
 * Created by Farruxx on 29.01.2015.
 * Абстрактный класс для описания всех методов решения дифф. уравнений
 */
public abstract class Method<Answer extends MethodAnswer> {
    public abstract Answer calculate();//абстрактный метод для старта вычислений
}
