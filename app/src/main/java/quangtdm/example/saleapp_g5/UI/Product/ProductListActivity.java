package quangtdm.example.saleapp_g5.UI.Product;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import quangtdm.example.saleapp_g5.Network.WebAPIEndpoint;
import quangtdm.example.saleapp_g5.R;

public class ProductListActivity extends AppCompatActivity {
    private List<String> productList =  new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.actvity_product_list), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //var adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, productList);
        var adapter = new ProductAdapter(productList);
        var listView = findViewById(R.id.actvity_product_list);

        var button = findViewById(R.id.button);

    }
}