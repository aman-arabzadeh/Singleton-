package com.aman.factory.Example1;

public class ChickenPizza implements Pizza {

    @Override
    public void prepare() {
        System.out.println("Preparing Chicken Pizza");
    }

    @Override
    public void bake() {
        System.out.println("Baking Chicken Pizza");
    }

    @Override
    public void cut() {
        System.out.println("Cut Chicken Pizza");
    }

    @Override
    public void box() {
        System.out.println("Boxing Chicken Pizza");
    }

    @Override
    public String getName() {
        return "Chicken Pizza";
    }
}
