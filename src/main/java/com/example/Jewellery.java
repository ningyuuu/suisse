package com.example;

import java.util.*;
import com.example.*;

public class Jewellery {
    private ArrayList<Bag> vault;
    private double maxWeight;
    private double result = 0;
    // private double[] testWeight = new double[4];
    // private double[] testValue = new double[4];


    public Jewellery(JewelleryWrapper input) {
        vault = new ArrayList<Bag>();

        for (HashMap<String, Double> item: input.getVault()) {
            vault.add(new Bag(item.get("weight"), item.get("value")));
        }

        this.maxWeight = input.getMaxWeight();

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
            index++;
        }
    }

    public double getResult() {
        return result;
    }

    public ArrayList<Bag> getVault(){
        return vault;
    }
}

class Bag implements Comparable<Bag> {
    private double weight, value, unitvalue;

    public Bag(double weight, double value) {
        this.weight = weight;
        this.value = value;
        unitvalue = value / weight;
    }

    public int compareTo(Bag other) {
        return (int)(other.getUnitValue() - unitvalue);
    }

    public double getUnitValue() {
        return unitvalue;
    }

    public double getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }

    public String toString(){
        String response = "weight: " + weight + ", value: " + value + ", unitvalue: " + unitvalue;
        return response;
    }
}