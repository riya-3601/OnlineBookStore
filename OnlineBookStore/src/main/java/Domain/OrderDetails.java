package Domain;

public class OrderDetails {
    private int orderDetailId;
    private int orderId;
    private int bookId;
    private int quantity;
    private String bookName;
    private int bookPrice;



    public OrderDetails() {
    }

    public OrderDetails(int orderDetailId, int orderId, int bookId, int quantity) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    public int getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public void setBookName(String bookName){this.bookName=bookName;}

    public String getBookName(){return bookName;}

    public void setBookPrice(int bookPrice){this.bookPrice=bookPrice;}

    public  int getBookPrice(){return bookPrice;}
}

