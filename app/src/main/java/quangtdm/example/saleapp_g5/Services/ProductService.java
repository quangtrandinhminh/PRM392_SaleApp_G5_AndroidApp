package quangtdm.example.saleapp_g5.Services;

import android.content.Context;

import java.util.List;

import quangtdm.example.saleapp_g5.Models.BaseResponse;
import quangtdm.example.saleapp_g5.Models.PaginatedListResponse;
import quangtdm.example.saleapp_g5.Models.ProductResponse;
import quangtdm.example.saleapp_g5.Network.WebAPIEndpoint;
import quangtdm.example.saleapp_g5.Utils.TokenManager;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ProductService {
    @GET(WebAPIEndpoint.Product.GetProducts)
    Call<BaseResponse<PaginatedListResponse<ProductResponse>>> GetProducts(
            @Query("pageNumber") int pageNumber);

    @GET(WebAPIEndpoint.Product.GetProductById)
    Call<BaseResponse<ProductResponse>> GetProductById(int productId);

//    @GET(WebAPIEndpoint.Product.SearchProducts)
//    Call<BaseResponse<List<ProductResponse>>> SearchProducts(String searchQuery);

    @POST(WebAPIEndpoint.Product.CreateProduct)
    Call<BaseResponse<Integer>> CreateProduct(ProductResponse product);

    @PUT(WebAPIEndpoint.Product.UpdateProduct)
    Call<BaseResponse<Integer>> UpdateProduct(ProductResponse product);

    @PUT(WebAPIEndpoint.Product.DeleteProduct)
    Call<BaseResponse<Integer>> DeleteProduct(int productId);
}
