package quangtdm.example.saleapp_g5.Models;

import com.google.gson.annotations.SerializedName;

public class CategoryResponse {
    @SerializedName("categoryId")
    private int categoryId;

    @SerializedName("categoryName")
    private String categoryName;

    public CategoryResponse(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}
