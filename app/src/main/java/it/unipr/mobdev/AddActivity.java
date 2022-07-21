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

import org.json.JSONException;
import org.json.JSONObject;

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
                    Toast.makeText(AddActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                } else {
                    String scannedValue = result.getContents();
                    // check if the scanned value is an alphanumeric value
                    if(scannedValue.chars().allMatch(Character::isLetterOrDigit)) {
                        EditText codeText = (EditText)findViewById(R.id.codeInput);
                        codeText.setText(scannedValue);
                    }
                    else {
                        Toast.makeText(AddActivity.this, "Codice non valido", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    // method used to launch code scanner
    public void scanCode(View view) {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Scansiona codice a barre o QR");
        options.setBeepEnabled(false);
        options.setBarcodeImageEnabled(false);
        barcodeLauncher.launch(options);
    }

    // used to save the new coupon and verify the data integrity of the newly created coupon
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
        if(code.equals("") || identifier.equals("")) {
            Toast errorMessage = Toast.makeText(getApplicationContext(), "Informazioni mancanti", Toast.LENGTH_SHORT);
            errorMessage.show();
            TextView codeTitle = (TextView)findViewById(R.id.codeTextView);
            TextView identifierTextView = (TextView)findViewById(R.id.companyTextView);
            if(code.equals(""))
                codeTitle.setTextColor(0xFFFF0000);
            if(identifier.equals(""))
                identifierTextView.setTextColor(0xFFFF0000);
            return;
        }
        Coupon c;
        if(expiration.equals(""))
            c = new Coupon(identifier, code);
        else
            c = new Coupon(identifier, code, expiration);

        CouponList.getInstance().addElement(c);
        finish();
    }

}