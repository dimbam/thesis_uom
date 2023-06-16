public class ShoppingCartTest {
    
    @Test
    void testAddItem() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Apple", 0.50);
        cart.addItem(item);
        
        assertAll("cart",
            () -> assertEquals(1, cart.getItemCount(), "item count"),
            () -> assertEquals(0.50, cart.getTotal(), "total price")
        );
    }
    
    @Test
    void testRemoveItem() {
        ShoppingCart cart = new ShoppingCart();
        Item item = new Item("Banana", 0.25);
        cart.addItem(item);
        cart.removeItem(item);
        
        assertAll("cart",
            () -> assertEquals(0, cart.getItemCount(), "item count"),
            () -> assertEquals(0, cart.getTotal(), "total price")
        );
    }
}

class Item {
    private String name;
    private double price;
    
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public double getPrice() {
        return price;
    }
}

class ShoppingCart {
    private List<Item> items = new ArrayList<>();
    
    public void addItem(Item item) {
        items.add(item);
    }
    
    public void removeItem(Item item) {
        items.remove(item);
    }
    
    public int getItemCount() {
        return items.size();
    }
    
    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total;
    }
}