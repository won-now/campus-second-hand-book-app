package demo.com.campussecondbookrecycle.Models;

import java.util.Date;

public class UserBook {
    private Integer id;

    private Integer userId;

    private String bookName;

    private String mainImage;

    private String bookIsbn;

    private Date createTime;

    private Date updateTime;

    public UserBook(Integer id, Integer userId, String bookName, String mainImage, String bookIsbn, Date createTime, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.bookName = bookName;
        this.mainImage = mainImage;
        this.bookIsbn = bookIsbn;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public UserBook() {
        super();
    }

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

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage == null ? null : mainImage.trim();
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn == null ? null : bookIsbn.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}