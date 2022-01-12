package model;

public class OrderDetail {
    private String detailCode;
    private String detailOrderId;
    private int qtyOnHand;
    private double price;
    private double discount;

    public OrderDetail(String detailCode, double unitPrice, int qty) {
    }

    public OrderDetail(String detailCode, String detailOrderId, int qtyOnHand, double price, double discount) {
        this.setDetailCode(detailCode);
        this.setDetailOrderId(detailOrderId);
        this.setQtyOnHand(qtyOnHand);
        this.setPrice(price);
        this.setDiscount(discount);
    }

    public String getDetailCode() {
        return detailCode;
    }

    public void setDetailCode(String detailCode) {
        this.detailCode = detailCode;
    }

    public String getDetailOrderId() {
        return detailOrderId;
    }

    public void setDetailOrderId(String detailOrderId) {
        this.detailOrderId = detailOrderId;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
