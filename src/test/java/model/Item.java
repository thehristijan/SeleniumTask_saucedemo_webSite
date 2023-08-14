package model;

public class Item implements Comparable<Item> {

    public String itemName;
    public String itemPrice;

    public Item(String itemName, String itemPrice) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }

    @Override
    public int compareTo(Item other) {
        double onePrice = Double.parseDouble(this.itemPrice.replaceAll("[^\\d.]", ""));
        double otherPrice = Double.parseDouble(other.itemPrice.replaceAll("[^\\d.]", ""));

        return Double.compare(onePrice, otherPrice);
    }

    @Override
    public String toString() {
        return "Name: " + itemName + " - Price: " + itemPrice;
    }
}