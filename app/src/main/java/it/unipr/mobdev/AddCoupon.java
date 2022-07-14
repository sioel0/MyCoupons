package it.unipr.mobdev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddCoupon extends AppCompatActivity {

    public static final String LOG="AddCoupon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_coupon);
    }

    // TODO: implement it to open camera and scan data
    public void scanCode(View view) {

    }

    // TODO: display error if a coupon with that name already exists
    public void saveInput(View view) {
        EditText identifierInput = (EditText)findViewById(R.id.identifierInput);
        EditText codeInput = (EditText)findViewById(R.id.codeInput);
        EditText expirationInput = (EditText)findViewById(R.id.expirationInput);
        String identifier = identifierInput.getText().toString();
        String code = codeInput.getText().toString();
        String expiration = expirationInput.getText().toString();
        Coupon c;
        if(expiration != "")
            c = new Coupon(identifier, code, expiration);
        else
            c = new Coupon(identifier, code);

        CouponList.getInstance().addElement(c);
        finish();
    }

}