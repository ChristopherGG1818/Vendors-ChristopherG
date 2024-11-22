//Christopher Garcia

class Item {
    String name;
    double price;
    int stock;
    private String description;

    Item(double price, int numPieces) {
        this.price = price;
        this.stock = numPieces;
    }
    public void restock(int amount) {
        this.stock = this.stock + amount;
    }

    public void purchase(int amount) {
        this.stock = this.stock - amount;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return (this.description + " \nPrice: " + this.price);
    }
}