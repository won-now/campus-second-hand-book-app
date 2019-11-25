package Models;

public class BookModel {
    private String name;
    private String ISBN;
    private String author;
    private double price;
    private String ImgURL;

    public BookModel() {
    }

    public BookModel(String name, String ISBN, String author, float price, String ImgURL) {
        this.name = name;
        this.ISBN = ISBN;
        this.author = author;
        this.price = price;
        this.ImgURL = ImgURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgURL() {
        return ImgURL;
    }

    public void setImgURL(String imgURL) {
        ImgURL = imgURL;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "name='" + name + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", ImgURL='" + ImgURL + '\'' +
                '}';
    }
}
