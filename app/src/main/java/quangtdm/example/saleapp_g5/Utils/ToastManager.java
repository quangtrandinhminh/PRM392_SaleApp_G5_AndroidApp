package quangtdm.example.saleapp_g5.Utils;

import static okhttp3.internal.Internal.instance;

import android.content.Context;
import android.widget.Toast;

public class ToastManager {
    public static void show(Context context, String message) {
        // Show toast
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
