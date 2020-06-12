package demo.com.campussecondbookrecycle.Models;

import java.util.List;

public class CategoryBody {
    private int status;
    private List<CategoryModel> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CategoryModel> getData() {
        return data;
    }

    public void setData(List<CategoryModel> data) {
        this.data = data;
    }
}
