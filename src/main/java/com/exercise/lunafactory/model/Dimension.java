package com.exercise.lunafactory.model;

public class Dimension {

    private double width;
    private double depth;
    private double heigth;

    public Dimension() {}

    public Dimension(double width, double depth, double heigth) {
        this.width = width;
        this.depth = depth;
        this.heigth = heigth;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) {
        this.heigth = heigth;
    }

    @Override
    public String toString() {
        return "Dimension{" +
                "width=" + width +
                ", depth=" + depth +
                ", heigth=" + heigth +
                '}';
    }
}
