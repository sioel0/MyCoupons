package it.unipr.mobdev;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class AddActivity extends AppCompatActivity {

    public static final String LOG="AddCoupon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    // Register the launcher and result handler
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Toast.makeText(AddActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                } else {
                    // TODO: extrapolate date and write it inside addActivity editText views
                    Toast.makeText(AddActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            });

    // Launch
    public void scanCode(View view) {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scansiona codice a barre o QR");
        options.setBeepEnabled(false);
        options.setBarcodeImageEnabled(false);
        barcodeLauncher.launch(options);
    }

    // TODO: error if name or code is empty
    public void saveInput(View view) {
        EditText identifierInput = (EditText)findViewById(R.id.identifierInput);
        EditText codeInput = (EditText)findViewById(R.id.codeInput);
        EditText expirationInput = (EditText)findViewById(R.id.expirationInput);
        String identifier = identifierInput.getText().toString();
        // display error message if coupon identifier has been already used
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