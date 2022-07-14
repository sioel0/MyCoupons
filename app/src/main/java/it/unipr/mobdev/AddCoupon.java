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

    public void saveInput(View view) {
        EditText companyInput = (EditText)findViewById(R.id.companyInput);
        EditText codeInput = (EditText)findViewById(R.id.codeInput);
        EditText expirationInput = (EditText)findViewById(R.id.expirationInput);
        String company = companyInput.getText().toString();
        String code = codeInput.getText().toString();
        String expiration = expirationInput.getText().toString();
        Coupon c;
        if(expiration != "")
            c = new Coupon(company, code, expiration);
        else
            c = new Coupon(company, code);

        CouponList.getInstance().addElement(c);
        finish();
    }

}