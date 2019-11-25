package Models;

import java.util.ArrayList;
import java.util.List;

/**
 * "orderID": "0123456789",
 *          "date": "2019-11-24",
 *          "bookNum": 10,
 *          "prices": 36.5,
 *          "status": 1,
 *          "type": 1,
 *          "bookList": []
 */

public class OrderModel {
    private String orderID;
    private String date;
    private int bookNum;
    private double prices;
    private int status;//订单状态 未支付 0 已完成 1 已取消 -1
    private int type;//订单类型 购买 0 卖出 1
    private List<BookModel> bookList = new ArrayList<>();

    public OrderModel() {
    }

    public OrderModel(String orderID, String date, int bookNum, double prices,
                      int status, int type, List<BookModel> bookList) {
        this.orderID = orderID;
        this.date = date;
        this.bookNum = bookNum;
        this.prices = prices;
        this.status = status;
        this.type = type;
        this.bookList = bookList;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getData() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBookNum() {
        return bookNum;
    }

    public void setBookNum(int bookNum) {
        this.bookNum = bookNum;
    }

    public double getPrices() {
        return prices;
    }

    public void setPrices(double prices) {
        this.prices = prices;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<BookModel> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookModel> bookList) {
        this.bookList = bookList;
    }
}
