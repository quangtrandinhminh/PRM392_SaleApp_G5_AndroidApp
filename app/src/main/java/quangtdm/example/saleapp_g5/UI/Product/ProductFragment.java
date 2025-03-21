package quangtdm.example.saleapp_g5.UI.Product;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import quangtdm.example.saleapp_g5.Adapters.ProductAdapter;
import quangtdm.example.saleapp_g5.Models.PaginatedListResponse;
import quangtdm.example.saleapp_g5.Models.ProductResponse;
import quangtdm.example.saleapp_g5.R;
import quangtdm.example.saleapp_g5.Repositories.ProductRepository;
import quangtdm.example.saleapp_g5.Utils.ToastManager;
import quangtdm.example.saleapp_g5.Utils.TokenManager;

public class ProductFragment extends Fragment {
    private RecyclerView rvProducts;
    private ProductAdapter productAdapter;
    private TextInputEditText edtSearch;
    private ProductRepository productRepository;
    private GridLayoutManager layoutManager;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int currentPage = 1;
    private int pageSize = 10; // Expected items per page

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setupRecyclerView();
        loadProducts(currentPage);
        productAdapter.setOnProductClickListener(product -> {
            // Check the role and start the appropriate activity
            if ("Admin".equalsIgnoreCase(TokenManager.getInstance(getContext()).getRole())) {
                Intent intent = new Intent(getContext(), ProductDetailsAdminActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getContext(), ProductDetailsCustomerActivity.class);
                intent.putExtra("product", product);
                startActivity(intent);
            }
        });
    }

    private void initViews(View view) {
        rvProducts = view.findViewById(R.id.rvProducts);
        edtSearch = view.findViewById(R.id.edtSearch);
        productRepository = new ProductRepository();

        // Initialize the adapter with an empty list
        productAdapter = new ProductAdapter(new ArrayList<>(), getContext());
        rvProducts.setAdapter(productAdapter);
    }

    private void setupRecyclerView() {
        layoutManager = new GridLayoutManager(getContext(), 2); // 2 columns
        rvProducts.setLayoutManager(layoutManager);

        rvProducts.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                // Check if we need to load more items
                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 10
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= pageSize) {
                        loadProducts(++currentPage);
                    }
                }
            }
        });
    }

    private void loadProducts(final int page) {
        isLoading = true;
        // Ensure your repository method accepts a page parameter.
        productRepository.getProducts(page, new ProductRepository.ProductCallback() {
            @Override
            public void onSuccess(Object data) {
                isLoading = false;
                if (data == null) {
                    // No data returned, consider this the last page or show error
                    isLastPage = true;
                    return;
                }

                // Cast the data to your paginated response model
                PaginatedListResponse<ProductResponse> response =
                        (PaginatedListResponse<ProductResponse>) data;
                List<ProductResponse> products = response.getItems();

                // Append the new products to the adapter
                if (products != null && !products.isEmpty()) {
                    productAdapter.addRange(products);
                }

                // Check if we've reached the last page
                // If totalPages is not properly set or is <= 0, assume we've reached the last page
                if (response.getTotalPages() <= 0) {
                    isLastPage = true;
                } else {
                    isLastPage = page >= response.getTotalPages();
                }
            }

            @Override
            public void onError(int code, String errorMessage) {
                isLoading = false;
                ToastManager.show(getContext(), errorMessage);
            }

            @Override
            public void onFailure(Throwable throwable) {
                isLoading = false;
                ToastManager.show(getContext(), throwable.getMessage());
            }
        });
    }

}
