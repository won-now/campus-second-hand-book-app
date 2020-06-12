package demo.com.campussecondbookrecycle.Models;

import java.math.BigDecimal;
import java.util.List;

public class SellCartVo {

    private List<SellCartBookVo> sellCartBookVoList;
    private BigDecimal totalPrice;
    private Boolean allChecked;//是否已经都勾选

    public List<SellCartBookVo> getSellCartBookVoList() {
        return sellCartBookVoList;
    }

    public void setSellCartBookVoList(List<SellCartBookVo> sellCartBookVoList) {
        this.sellCartBookVoList = sellCartBookVoList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Boolean getAllChecked() {
        return allChecked;
    }

    public void setAllChecked(Boolean allChecked) {
        this.allChecked = allChecked;
    }
}
