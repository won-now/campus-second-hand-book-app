package demo.com.campussecondbookrecycle.Models;

import java.math.BigDecimal;
import java.util.List;

public class BuyCartBook {

    private List<BuyCartBookVo> buyCartBookVoList;
    private BigDecimal totalPrice;
    private boolean allChecked;
    private String imageHost;

    public List<BuyCartBookVo> getBuyCartBookVoList() {
        return buyCartBookVoList;
    }

    public void setBuyCartBookVoList(List<BuyCartBookVo> buyCartBookVoList) {
        this.buyCartBookVoList = buyCartBookVoList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isAllChecked() {
        return allChecked;
    }

    public void setAllChecked(boolean allChecked) {
        this.allChecked = allChecked;
    }

    public String getImageHost() {
        return imageHost;
    }

    public void setImageHost(String imageHost) {
        this.imageHost = imageHost;
    }
}
