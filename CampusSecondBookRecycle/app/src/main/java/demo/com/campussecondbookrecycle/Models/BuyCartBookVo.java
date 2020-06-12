package demo.com.campussecondbookrecycle.Models;

import java.math.BigDecimal;

public class BuyCartBookVo {

    private int id;
    private int userId;
    private int bookId;
    private String bookName;
    private int bookQuality;
    private String bookMainImage;
    private BigDecimal bookPrice;
    private int bookStatus;
    private int bookStock;
    private int bookChecked;
    private boolean existed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getBookQuality() {
        return bookQuality;
    }

    public void setBookQuality(int bookQuality) {
        this.bookQuality = bookQuality;
    }

    public String getBookMainImage() {
        return bookMainImage;
    }

    public void setBookMainImage(String bookMainImage) {
        this.bookMainImage = bookMainImage;
    }

    public BigDecimal getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(BigDecimal bookPrice) {
        this.bookPrice = bookPrice;
    }

    public int getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(int bookStatus) {
        this.bookStatus = bookStatus;
    }

    public int getBookStock() {
        return bookStock;
    }

    public void setBookStock(int bookStock) {
        this.bookStock = bookStock;
    }

    public int getBookChecked() {
        return bookChecked;
    }

    public void setBookChecked(int bookChecked) {
        this.bookChecked = bookChecked;
    }

    public boolean isExisted() {
        return existed;
    }

    public void setExisted(boolean existed) {
        this.existed = existed;
    }
}
