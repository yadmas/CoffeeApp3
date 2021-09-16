package android.example.coffeeapp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import coffeeapp3.R;

public class MainActivity extends AppCompatActivity {

    int noOfCoffee = 0;
    int priceOfCoffee = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    //submit order button
    public void submitOrder(View view){
        //call the method display
        //display(1);
        EditText nameField = findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        CheckBox whippedCreamCheckBox = findViewById(R.id.cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckBox = findViewById(R.id.choc_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        String message = createOrderSummary(name,price,hasWhippedCream,hasChocolate);


        //TextView priceTextView = findViewById(R.id.price_text_view);
        //priceTextView.setText("$" + price
        //        + "\n" + "Thankyou!");

        //intent
        Intent intent = new Intent(this,DisplayOrderDetails.class);
        intent.putExtra("name", name);
        intent.putExtra("message",message);
        intent.putExtra("totalPrice", Integer.toString(calculatePrice(hasWhippedCream, hasChocolate)));
        startActivity(intent);
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name "+name+"\n" +
                "Add Whipped Cream? " + addWhippedCream + "\n" +
                "Add Chocolate? " + addChocolate + "\n" +
                "Quantity : " + noOfCoffee + "\n" +
                "Total : $" + price + "\n" +
                "Thank you !";
        return priceMessage;
    }

    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = priceOfCoffee;
        if(hasWhippedCream){basePrice++;}
        if(hasChocolate){basePrice=basePrice+2;}
        int finalPrice = basePrice * noOfCoffee;

        return finalPrice;
    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    public void increment(View view){
        if(noOfCoffee < 10) {
            noOfCoffee = noOfCoffee + 1;
        }
        display(noOfCoffee);
    }

    public void decrement(View view){
        if(noOfCoffee > 1) {
            noOfCoffee = noOfCoffee -1;
        }
        display(noOfCoffee);
    }
}