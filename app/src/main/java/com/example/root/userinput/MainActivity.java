package com.example.root.userinput;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

                CheckBox whippedCheckBox = findViewById(R.id.whipped);
        boolean haswhippedCheckBox = whippedCheckBox.isChecked();
        Log.v("MainActivity", "Has whipped Cream " + haswhippedCheckBox);

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate);
        boolean hasChocolateCheckBox = chocolateCheckBox.isChecked();
        Log.v("MainActivity", "Has Chocolate " + hasChocolateCheckBox);

        EditText getName = findViewById(R.id.clientName);
        String clientName = getName.getText().toString();
        Log.v("MainActivity", "Name: " + clientName);

        int price = calculatePrice();
        String priceMessage = createOrderSummary(price, haswhippedCheckBox, hasChocolateCheckBox, clientName);
        displayOrderMessage(priceMessage);

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for: " + clientName);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        // String priceMessage = "Thank You !";
        // displayMessage(priceMessage);
    }

    /**
     * This method is called when the Increment button is clicked.
     */
    public void increment(View view) {

        numberOfCoffees = numberOfCoffees + 1;
        displayMessage(numberOfCoffees);
        displayPrice(numberOfCoffees);
    }

    /**
     * This method is called when the Decrement button is clicked.
     */
    public void decrement(View view) {

        numberOfCoffees = numberOfCoffees - 1;
        displayMessage(numberOfCoffees);

    }

    /**
     * Calculates the price of the order
     *
     * @ param int Price displays the price of the cup of coffee
     * @ param addWhippedCream displays the boolean value of the check box. Whether it has been
     * checked or not
     * @ param nameOfClient displays the name of the client
     */
    private int calculatePrice() {

        return numberOfCoffees * 5;
    }

    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate, String nameOfClient) {
        String priceMessage = "Name: " + nameOfClient;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage = priceMessage + "\nQuantity: " + numberOfCoffees;
        priceMessage = priceMessage + "\nTotal: " + price;
        priceMessage = priceMessage + "\n" + getString(R.string.thank_you);

        return priceMessage;

    }

    private void displayOrderMessage(String message) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private void displayMessage(int message) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private void displayPrice(int number) {
        TextView priceTextView = findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


}
