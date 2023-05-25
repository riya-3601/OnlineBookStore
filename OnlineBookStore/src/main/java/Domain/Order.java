package Domain;

import java.util.Date;

public class Order {
    private int orderId;
    private int customerId;
    private int addressId;
    private int paymentId;
    private Date orderDate;
    private String orderStatus;
    private double orderTotal;
    private String customerName;

    public Order() {
    }

    public Order(int orderId, int customerId, int addressId, int paymentId, Date orderDate, String orderStatus, double orderTotal) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.addressId = addressId;
        this.paymentId = paymentId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }



    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public void setCustomerName(String customerName){this.customerName=customerName;}

    public String getCustomerName(){return  customerName;}
}
