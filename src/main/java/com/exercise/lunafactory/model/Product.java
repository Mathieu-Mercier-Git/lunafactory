package com.exercise.lunafactory.model;

public class Product {

    private int id;
    private String ean;
    private String name;
    private String description;
    private double price;
    private boolean assembled;
    private double weigth;
    private Dimension dimension;

    public Product() {}

    public Product(int id, String ean, String name, String description, double price, boolean assembled, double weigth, Dimension dimension) {
        this.id = id;
        this.ean = ean;
        this.name = name;
        this.description = description;
        this.price = price;
        this.assembled = assembled;
        this.weigth = weigth;
        this.dimension = dimension;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAssembled() {
        return assembled;
    }

    public void setAssembled(boolean assembled) {
        this.assembled = assembled;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) {
        this.weigth = weigth;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", ean='" + ean + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", assembled=" + assembled +
                ", weigth=" + weigth +
                ", dimension=" + dimension +
                '}';
    }
}
