package quangtdm.example.saleapp_g5.Utils;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import quangtdm.example.saleapp_g5.R;

public class ImageUtils {
    public static void loadImage(String imageUrl, ImageView imageView) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_launcher_background)  // Default placeholder image
                .error(R.drawable.ic_launcher_background)        // Image if error occurs
                .into(imageView);
    }
}

