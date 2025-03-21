package quangtdm.example.saleapp_g5.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import quangtdm.example.saleapp_g5.Models.ProductResponse;
import quangtdm.example.saleapp_g5.R;
import quangtdm.example.saleapp_g5.Utils.ImageUtils;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private List<ProductResponse> items;
    private Context context;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private OnProductClickListener onItemClickListner;

    // SubjectAdapter constructor
    public ProductAdapter(
            List<ProductResponse> products,
            Context context) {
        this.items = products;
        this.context = context;
    }

    // Create a new ViewHolder instance to represent an item in the list
    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView tvProductName;
        TextView tvProductPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
        }
    }

    // Create a new ViewHolder instance, inflate the layout for an item in the list
    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    // Bind data to ViewHolder instance at position in RecyclerView to display data at that position in the list
    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        ProductResponse product = items.get(position);
        holder.tvProductName.setText(product.getProductName());
        holder.tvProductPrice.setText(String.valueOf(product.getPrice()));
        ImageUtils.loadImage(product.getImageUrl(), holder.ivProductImage);

        // Set click listener on the entire item view
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListner != null) {
                onItemClickListner.onItemClick(product);
            }
        });
    }

    // Define an interface for item clicks
    public interface OnProductClickListener {
        void onItemClick(ProductResponse product);
    }

    // Setter for the listener
    public void setOnProductClickListener(OnProductClickListener listener) {
        this.onItemClickListner = listener;
    }

    // Return number of items in RecyclerView
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Update the list of items in the RecyclerView
    public void updateItems(List<ProductResponse> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    // Add a new item to the list of items in the RecyclerView
    public void addItem(ProductResponse item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    // Remove an item from the list of items in the RecyclerView
    public void removeItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    // Clear all items from the list of items in the RecyclerView
    public void clearItems() {
        items.clear();
        notifyDataSetChanged();
    }

    // Get the item at a specific position in the list of items in the RecyclerView
    public ProductResponse getItem(int position) {
        return items.get(position);
    }

    // add range of items to the list of items in the RecyclerView
    public void addRange(List<ProductResponse> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
