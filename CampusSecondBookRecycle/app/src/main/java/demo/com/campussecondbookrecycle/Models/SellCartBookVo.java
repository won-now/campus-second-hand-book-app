package demo.com.campussecondbookrecycle.Models;

import java.math.BigDecimal;

public class SellCartBookVo {

    private Integer id;
    private Integer userId;
    private String bookISBN;
    private String bookName;
    private String bookMainImage;
    private BigDecimal valuation;//估价
    private Integer bookStatus;
    private Integer bookStock;
    private Integer bookChecked;//此书是否勾选

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookMainImage() {
        return bookMainImage;
    }

    public void setBookMainImage(String bookMainImage) {
        this.bookMainImage = bookMainImage;
    }

    public BigDecimal getValuation() {
        return valuation;
    }

    public void setValuation(BigDecimal valuation) {
        this.valuation = valuation;
    }

    public Integer getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Integer getBookStock() {
        return bookStock;
    }

    public void setBookStock(Integer bookStock) {
        this.bookStock = bookStock;
    }

    public Integer getBookChecked() {
        return bookChecked;
    }

    public void setBookChecked(Integer bookChecked) {
        this.bookChecked = bookChecked;
    }
}
