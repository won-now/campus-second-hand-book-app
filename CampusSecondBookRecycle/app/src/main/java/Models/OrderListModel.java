package Models;

import java.util.ArrayList;
import java.util.List;

public class OrderListModel {
    private List<OrderModel> orderList = new ArrayList<>();

    public OrderListModel() {
    }

    public OrderListModel(List<OrderModel> orderList) {
        this.orderList = orderList;
    }

    public List<OrderModel> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderModel> orderList) {
        this.orderList = orderList;
    }
}
