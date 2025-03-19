//package quangtdm.example.saleapp_g5.Adapters;
//
//import static android.os.Build.VERSION_CODES.R;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import quangtdm.example.saleapp_g5.Models.ProductResponse;
//
//public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.SubjectViewHolder> {
//    private List<ProductResponse> subjects;
//    private Context context;
//    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//    // SubjectAdapter constructor
//    public ProductAdapter(
//            List<ProductResponse> subjects,
//            Context context) {
//        this.subjects = subjects;
//        this.context = context;
//    }
//
//    // Create a new ViewHolder instance to represent an item in the list
//    public static class SubjectViewHolder extends RecyclerView.ViewHolder {
//        ImageView ivSubject;
//        TextView tvSubject;
//
//        public SubjectViewHolder(@NonNull View itemView) {
//            super(itemView);
//            // map the view from the layout file
////            ivSubject = itemView.findViewById(R.id.ivSubject);
////            tvSubject = itemView.findViewById(R.id.tvSubject);
//        }
//    }
//
//    // Create a new ViewHolder instance, inflate the layout for an item in the list
//    @NonNull
//    @Override
//    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.item_subject, parent, false);
//        return new SubjectViewHolder(view);
//    }
//
//    // Bind data to ViewHolder instance at position in RecyclerView to display data at that position in the list
//    @Override
//    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {
////        Subject subject = subjects.get(position);
////        holder.tvSubject.setText(subject.getName());
////        holder.ivSubject.setImageResource(subject.getImage());
////        holder.ivSubject.setImageResource(
////        ImageUtils.loadImageFromUrl(subject.getImageUrl(), holder.ivSubject));
//    }
//
//    // Return number of items in RecyclerView
//    @Override
//    public int getItemCount() {
//        return subjects.size();
//    }
//}
