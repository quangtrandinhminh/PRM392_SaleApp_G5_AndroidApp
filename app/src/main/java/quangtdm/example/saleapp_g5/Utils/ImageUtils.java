package quangtdm.example.saleapp_g5.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.io.IOException;
import java.net.URL;

public class ImageUtils {
    public static boolean loadImageFromUrl(String imageUrl, ImageView imageView) {
        try {
            URL url = new URL(imageUrl);
            final Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());

            // Update the UI thread using the ImageView's context
            imageView.setImageBitmap(bmp);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}
