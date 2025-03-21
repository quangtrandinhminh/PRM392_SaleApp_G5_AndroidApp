package quangtdm.example.saleapp_g5.Repositories;

import java.util.ArrayList;
import java.util.List;

import quangtdm.example.saleapp_g5.Models.BaseResponse;
import quangtdm.example.saleapp_g5.Models.PaginatedListResponse;
import quangtdm.example.saleapp_g5.Models.ProductResponse;
import quangtdm.example.saleapp_g5.Network.RetrofitClient;
import quangtdm.example.saleapp_g5.Services.ProductService;
import quangtdm.example.saleapp_g5.Utils.ErrorHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository {
    public final ProductService productService;

    public interface ProductCallback {
        void onSuccess(Object data);
        void onError(int code, String errorMessage);
        void onFailure(Throwable throwable);
    }

    public ProductRepository() {
        productService = RetrofitClient.getClient().create(ProductService.class);
    }

    // Call the API to get all products
    public void getProducts(final int pageNumber, final ProductCallback callback) {
        // Call the API to get all products
        // add token to header

        Call<BaseResponse<PaginatedListResponse<ProductResponse>>> call =
                (Call<BaseResponse<PaginatedListResponse<ProductResponse>>>) productService.GetProducts(
                        pageNumber
                );

        call.enqueue(new Callback<BaseResponse<PaginatedListResponse<ProductResponse>>>() {
            @Override
            public void onResponse(Call<BaseResponse<PaginatedListResponse<ProductResponse>>> call,
                                   Response<BaseResponse<PaginatedListResponse<ProductResponse>>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body().getData());
                } else {
                    String errorMsg = ErrorHandler.parseError(response);
                    callback.onError(response.code(), errorMsg);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<PaginatedListResponse<ProductResponse>>> call, Throwable t) {
                // Handle network error
                callback.onFailure(t);
            }
        });
    }

    // Call the API to search for products
    public void searchProducts(String searchQuery) {
        // Call the API to search for products
    }

    // Call the API to get product details
    public void getProductDetails(int productId) {
        // Call the API to get product details
    }

    // Call the API to add a product to the cart
    public void addToCart(int productId) {
        // Call the API to add a product to the cart
    }

    // Call the API to remove a product from the cart
    public void removeFromCart(int productId) {
        // Call the API to remove a product from the cart
    }

    // Call the API to get all products in the cart
    public void getCartProducts() {
        // Call the API to get all products in the cart
    }

    // Call the API to place an order
    public void placeOrder() {
        // Call the API to place an order
    }
}
