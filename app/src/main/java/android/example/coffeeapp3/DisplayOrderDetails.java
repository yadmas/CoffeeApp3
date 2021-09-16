package android.example.coffeeapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import coffeeapp3.R;

public class DisplayOrderDetails extends AppCompatActivity {
String message;
String name;
String totalPrice;
CoffeeDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_details);
        dbHandler = new CoffeeDBHandler(this,null,null,1);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("message");
        totalPrice = intent.getStringExtra("totalPrice");

        TextView textView = findViewById(R.id.details);
        textView.setText(message);


    }

    public void sendEmail(View view) {
        String [] addresses = {"s.d.dayshine@gmail.com", "email1@email.com"};
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee android.example.coffeeapp3.Order");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(intent);
    }

    public void addButtonClicked(View view) {
        Order order = new Order(name, Integer.parseInt(totalPrice));
        dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(),"Data Saved!", Toast.LENGTH_SHORT).show();
    }

    public void salesReport(View view) {
        String dbString = dbHandler.databaseToString();
        Intent salesIntent = new Intent(this, DisplaySalesDetails.class);
        salesIntent.putExtra("db", dbString);
        startActivity(salesIntent);
    }
}