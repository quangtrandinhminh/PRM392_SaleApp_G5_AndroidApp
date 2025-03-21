package quangtdm.example.saleapp_g5.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PaginatedListResponse<T> {
    @SerializedName("items")
    private List<T> items;

    @SerializedName("pageNumber")
    private int pageNumber;

    @SerializedName("totalPages")
    private int totalPages;

    @SerializedName("totalCount")
    private int totalCount;

    @SerializedName("hasPreviousPage")
    private boolean hasPreviousPage;

    @SerializedName("hasNextPage")
    private boolean hasNextPage;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }
}
