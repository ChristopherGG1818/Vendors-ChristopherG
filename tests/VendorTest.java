import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
//Christopher Garcia

public class VendorTest {
    static Vending vendingMachine;

    @BeforeEach
    void setingUp() {
        vendingMachine = new Vending(5, 5, "vendorOne");
    }

    @Test
    void adding() {
        assertEquals(2, 1 + 1);
    }

    @Test
    void addingMoney() {
        vendingMachine.addMoney(1.0);
        assertEquals(1.0, vendingMachine.getBalance());
    }

    @Test
    void buyingCandy() {
        vendingMachine.addMoney(1.25);
        vendingMachine.select("Candy");
        assertEquals(0.0, vendingMachine.getBalance());
    }

//    @Test
//    void outOfInventory() {
//        vendingMachine.emptyStock();
//        assertEquals(0, (Vending.Stock.get("Candy")).stock);
//        assertEquals(0, (Vending.Stock.get("Gum")).stock);
//    }

//    @Test
//    void restockedProducts() {
//        int preCandyStock = Vending.Stock.get("Candy").stock;
//        int preGumStock = Vending.Stock.get("Gum").stock;
//        Vending.Stock.get("Candy").restock(10);
//        Vending.Stock.get("Gum").restock(5);
//        assertEquals(preCandyStock + 10, Vending.Stock.get("Candy").stock);
//        assertEquals(preGumStock + 5, Vending.Stock.get("Gum").stock);
//    }

    @Test
    void moreOrNew() {
        vendingMachine.moreorNew("Hershey", 5, 3);
        assertEquals(5, Vending.Stock.get("Hershey").stock);
    }

    @Test
    void renameItem() {
        double price = Vending.Stock.get("Candy").price;
        int quantity = Vending.Stock.get("Candy").stock;
        vendingMachine.renameItem("Candy", "Reese's");
        assertFalse(Vending.Stock.containsKey("Candy"));
        assertTrue(Vending.Stock.containsKey("Reese's"));
        assertEquals(price, Vending.Stock.get("Reese's").price);
        assertEquals(quantity, Vending.Stock.get("Reese's").stock);
    }

    @Test
    void multipleVendors() {
        Vending Two = new Vending(10, 10, "vendorTwo");
        Vending Thre = new Vending(15, 15, "vendorThree");
        Vending four = new Vending(20, 20, "vendorFoours");
        Vending five = new Vending(25, 25, "vendorFive");
        Two.addMoney(1.0);
        Thre.addMoney(15.0);
        four.addMoney(400.0);
        five.addMoney(0.25);
        assertNotEquals(vendingMachine.Inventory(), Two.Inventory());
        assertNotEquals(Two.Inventory(), Thre.Inventory());
        assertNotEquals(Thre.Inventory(), four.Inventory());
        assertNotEquals(four.Inventory(), five.Inventory());
    }

    @Test
    void removeItem() {
        vendingMachine.removeItem("Candy");
        assertFalse(Vending.Stock.containsKey("Candy"));
    }

    @Test
    void buyingHistory() {
        vendingMachine.addMoney(1.25);
        vendingMachine.select("Candy");
        assertEquals(1, vendingMachine.purchases.size());
        assertTrue(vendingMachine.purchases.containsKey("Candy"));
    }

    @Test
    void checkDescription() {
        Vending.Stock.get("Candy").setDescription("");
        Vending.Stock.get("Gum").setDescription("Fresh");
        assertEquals("Price: $5.00", Vending.Stock.get("Candy").getDescription());
        assertEquals("Freash gum: $5.00", Vending.Stock.get("Gum").getDescription());
    }

//    @Test
//    void discountedItem() {
//        vendingMachine.discount("Candy", 0.5);
//        assertEquals(1.0, Vending.Stock.get("Candy").price);
//    }
    //??

    @Test
    void bestBuys() {
        vendingMachine.BestSeller("Candy");
        assertTrue(vendingMachine.bestSellers.contains("Candy"));
    }
}
