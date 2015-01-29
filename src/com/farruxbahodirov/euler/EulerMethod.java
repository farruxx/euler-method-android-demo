package com.farruxbahodirov.euler;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * Created by Farruxx on 18.01.2015.
 * Реализация класса Method. Вычисления происходит на основе данных из конструктора
 */
public class EulerMethod extends Method<EulerAnswer>{
    private String eq;
    private Double y0;
    private Double a;
    private Double b;
    private Double eps;
    private Expression expression;

    //конструктор
    public EulerMethod(String eq, String y0, String a, String b, String eps) throws NumberFormatException {
        this.eq = eq;
        this.y0 = Double.parseDouble(y0);
        this.a = Double.parseDouble(a);
        this.b = Double.parseDouble(b);
        this.eps = Double.parseDouble(eps);
    }

    @Override   //переопределённый метод
    public EulerAnswer calculate() throws IllegalArgumentException {
        Double x = a;
        double y = y0;
        expression = new ExpressionBuilder(eq).variables("x", "y").build();
        StringBuffer stringBuffer = new StringBuffer();

        //начало алгоритма Эйлера
        do {
            stringBuffer.append("x=" + String.format("%.2f", x)
                    + "   y=" + String.format("%1.2f", y) + "\n");
            y += eps * f(x, y);
            x += eps;
        }
        while (x <= b);
        return new EulerAnswer(stringBuffer.toString(),x,y);
    }

    //значение уравнения в точках x и y
    private double f(double x, double y) {
        expression.setVariable("x", x);
        expression.setVariable("y", y);
        return expression.evaluate();
    }
}