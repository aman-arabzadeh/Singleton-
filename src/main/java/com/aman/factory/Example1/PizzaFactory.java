package com.aman.factory.Example1;

public class PizzaFactory {
    public static Pizza createPizza(String type){
        return switch (type) {
            case "cheese" -> new CheesePizza();
            case "veggie" -> new VeggiePizza();
            case "chicken" -> new ChickenPizza();
            default -> null;
        };
    }
}
