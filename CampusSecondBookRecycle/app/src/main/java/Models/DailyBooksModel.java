package Models;

import java.util.ArrayList;
import java.util.List;

public class DailyBooksModel {
    private List<BookModel> list = new ArrayList<>();

    public DailyBooksModel() {
    }

    public DailyBooksModel(List<BookModel> list) {
        this.list = list;
    }

    public List<BookModel> getList() {
        return list;
    }

    public void setList(List<BookModel> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "DailyBooksModel{" +
                "list=" + list +
                '}';
    }
}
