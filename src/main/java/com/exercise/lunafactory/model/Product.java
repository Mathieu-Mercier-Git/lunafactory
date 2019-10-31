package com.exercise.lunafactory.model;

public class Product {

    private int id;
    private String ean;
    private String name;
    private double weight;
    private String description;
    private double price;
    private boolean assembled;
    private Dimension dimension;

    public Product() {}

    public Product(int id, String ean, String name, double weight, String description, double price, boolean assembled, Dimension dimension) {
        this.id = id;
        this.ean = ean;
        this.name = name;
        this.weight = weight;
        this.description = description;
        this.price = price;
        this.assembled = assembled;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
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
                ", weight=" + weight +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", assembled=" + assembled +
                ", dimension=" + dimension +
                '}';
    }
}
