package com.example;

import java.util.*;
import com.example.*;

public class Jewellery {
    private ArrayList<Bag> vault;
    private int maxWeight;
    private double result;

    public Jewellery(JewelleryWrapper input) {
        vault = new ArrayList<Bag>();

        for (HashMap<Double, Double> item: input.getVault()) {
            vault.add(new Bag(item.get("weight"), item.get("value")));
        }

        Collections.sort(vault);
    }

    public void calcResult() {
        int index = 0;
        // calculate our result
        while (maxWeight > 0) {
            Bag curr = vault.get(index);
            if (maxWeight >= curr.getWeight()) {
                maxWeight -= curr.getWeight();
                result += curr.getValue();
            } else {
                result += curr.getUnitValue() * maxWeight;
                maxWeight = 0;
            }
        }
    }

    public double getResult() {
        return result;
    }
}

class Bag implements Comparable<Bag> {
    private double weight, value, unitvalue;

    public Bag(double weight, double value) {
        weight = weight;
        value = value;
        unitvalue = value / weight;
    }

    public int compareTo(Bag other) {
        return (int)(other.getUnitValue() - unitvalue);
    }

    public double getUnitValue() {
        resulteturn unitvalue;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }
}