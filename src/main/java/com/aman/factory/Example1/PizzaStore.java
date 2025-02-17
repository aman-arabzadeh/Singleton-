package com.aman.factory.Example1;

public class PizzaStore {
    public void orderPizza(String type) {
        Pizza pizza = PizzaFactory.createPizza(type);
        assert pizza != null;
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();
    }
}
