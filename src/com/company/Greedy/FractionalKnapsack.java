package com.company.Greedy;
import java.util.*;

class FractionalKnapsack {

    // Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(List<Integer> val, List<Integer> wt, int capacity) {
        int n = val.size();
        List<Item> items = new ArrayList<>();

        // Populate the ArrayList with Item objects
        for (int i = 0; i < n; i++) {
            items.add(new Item(val.get(i), wt.get(i)));
        }

        // Sort items by value-to-weight ratio in descending order
        items.sort((a, b) -> Double.compare(b.ratio, a.ratio));

        double maxValue = 0.0;

        for (Item item : items) {
            if (capacity >= item.weight) {
                // Take the whole item
                maxValue += item.value;
                capacity -= item.weight;
            } else {
                // Take fraction of the item
                maxValue += (double) item.value * capacity / item.weight;
                break; // Knapsack is full
            }
        }
        return maxValue;
    }

    // Helper class to store item properties
    static class Item {
        int value, weight;
        double ratio;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight;
        }
    }

    public static void main(String[] args) {
        FractionalKnapsack fk = new FractionalKnapsack();
        List<Integer> values = Arrays.asList(60, 100, 120);
        List<Integer> weights = Arrays.asList(10, 20, 30);
        int capacity = 50;

        double maxValue = fk.fractionalKnapsack(values, weights, capacity);
        System.out.println("Maximum value in Knapsack = " + maxValue);
    }
}


