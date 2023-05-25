package Domain;

import java.security.Timestamp;

public class Cart {
    private int cartId;
    private int customerId;
    private int bookId;
    private int quantity;
    private Timestamp sessionId;
    private String bookTitle;
    private double bookPrice;

    public Cart() {
    }

    public Cart(int cartId, int customerId, int bookId, int quantity, Timestamp sessionId) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.sessionId = sessionId;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Timestamp getSessionId() {
        return sessionId;
    }

    public void setSessionId(Timestamp sessionId) {
        this.sessionId = sessionId;
    }
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }
}
