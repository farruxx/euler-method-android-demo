package com.farruxbahodirov.euler;

/**
 * Created by Farruxx on 29.01.2015.
 */
public class EulerAnswer extends MethodAnswer{

    private String fullAnswer;
    private double x;
    private double y;

    public EulerAnswer(String fullAnswer, double x, double y) {
        this.fullAnswer = fullAnswer;
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String getAnswerString() {
        return fullAnswer;
    }
}
