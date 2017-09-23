import java.util.*;

public class Jewellery {
    private ArrayList<Bag> vault;
    private int maxWeight;
    private double result;

    public Jewellery(JsonObject input) {
        vault = new ArrayList<Bag>();

        for (JsonObject item: input.vault) {
            vault.add(new Bag(item.weight, item.value));
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
    private int weight;
    private double value, unitvalue;

    public Bag(int weight, double value) {
        weight = weight;
        value = value;
        unitvalue = value / weight;
    }

    public int compareTo(Bag other) {
        return Integer.parseInt(other.getUnitValue() - unitvalue);
    }

    public double getUnitValue() {
        return unitvalue;
    }

    public int getWeight() {
        return weight;
    }

    public double getValue() {
        return value;
    }
}