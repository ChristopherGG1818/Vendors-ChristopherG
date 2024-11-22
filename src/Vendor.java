import java.util.HashMap;
import java.util.HashSet;
//Christopher Garcia

/**
 * Class for a Vending Machine.  Contains a hashtable mapping item names to item data, as
 * well as the current balance of money that has been deposited into the machine.
 */
class Vending {
    public static HashMap<String, Item> Stock = new HashMap<>();
    public HashMap<String, Integer> purchases;
    private double balance;
    private String vendorName;
    public HashSet<String> bestSellers = new HashSet<>();

    Vending(int numCandy, int numGum, String vendorName) {
        this.vendorName = vendorName;
        Stock.put("Candy", new Item(1.25, numCandy));
        Stock.put("Chrips", new Item(.5, numGum));
        this.balance = 0;
        this.purchases = new HashMap<>();
    }

    /** resets the Balance to 0 */
    void resetBalance () {
        this.balance = 0;
    }
    /** returns the current balance */
    double getBalance () {
        return this.balance;
    }

    /** adds money to the machine's balance
     * @param amt how much money to add
     * */
    void addMoney (double amt) {
        this.balance = this.balance + amt;
    }

    /** attempt to purchase named item.  Message returned if
     * the balance isn't sufficient to cover the item cost.
     *
     * @param name The name of the item to purchase ("Candy" or "Gum")
     */
    void select (String name) {
        if (Stock.containsKey(name)) {
            Item item = Stock.get(name);
            if (balance >= item.price) {
                item.purchase(1);
                this.balance = this.balance - item.price;
                purchases.put(name, purchases.getOrDefault(name, 0) + 1);
            }
            else
                System.out.println("Gimme more money");
        }
        else System.out.println("Sorry, we don't know that item");
    }

    void emptyStock() { //Nothig exists
        for (Item item : Stock.values()) {
            item.stock = 0;
        }
    }
    void moreorNew(String itemName, int amount, double price) {
        if (Stock.containsKey(itemName)) {
            Stock.get(itemName).restock(amount);
        }
        else {
            Stock.put(itemName, new Item(price, amount));
        }
    }

    void renameItem(String oldName, String newName) {
        if (Stock.containsKey(oldName)) {
            Stock.put(newName, Stock.get(oldName));
            Stock.remove(oldName);
        }
    }

    String Inventory() {
        StringBuilder build = new StringBuilder();
        build.append(vendorName + "Stock:");
        for(String key: Stock.keySet()) {
            build.append(key + Stock.get(key).stock);
        }
        return build.toString();
    }

    void removeItem(String itemName) {
        Stock.remove(itemName);
    }

    public void discount(String itemName, double discountPercentage) {
        if(discountPercentage > 0.0 && discountPercentage < 1.0) {
            Stock.get(itemName).price = Stock.get(itemName).price * (1 - discountPercentage);
        }
    }

    public void BestSeller(String itemName) {
        if(Stock.containsKey(itemName)) {
            bestSellers.add(itemName);
        }
    }

}
