package it.unipr.mobdev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    public static final String LOG="AddCoupon";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    // TODO: implement it to open camera and scan data
    public void scanCode(View view) {

    }

    public void saveInput(View view) {
        EditText identifierInput = (EditText)findViewById(R.id.identifierInput);
        EditText codeInput = (EditText)findViewById(R.id.codeInput);
        EditText expirationInput = (EditText)findViewById(R.id.expirationInput);
        String identifier = identifierInput.getText().toString();
        if(CouponList.getInstance().containsCouponNamed(identifier)) {
            Toast errorMessage = Toast.makeText(getApplicationContext(), "Nome giá esistente", Toast.LENGTH_SHORT);
            errorMessage.show();
            TextView idTitle = (TextView)findViewById(R.id.companyTextView);
            idTitle.setTextColor(0xFFFF0000);
            return;
        }
        String code = codeInput.getText().toString();
        String expiration = expirationInput.getText().toString();
        Coupon c;
        if(expiration.equals(""))
            c = new Coupon(identifier, code);
        else
            c = new Coupon(identifier, code, expiration);

        CouponList.getInstance().addElement(c);
        finish();
    }

}