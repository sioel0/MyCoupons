package it.unipr.mobdev;

import static it.unipr.mobdev.CouponAdapter.ViewHolder.IDENTIFIER;
import static it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_CODE;
import static it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_EXPIRATION;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// TODO: add qr/bar code generation and display managed with switch
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        // retrieve data from the intent that started the activity
        String code = intent.getStringExtra(COUPON_CODE);
        String company = intent.getStringExtra(IDENTIFIER);
        String expiration = intent.getStringExtra(COUPON_EXPIRATION);
        if(company != null) {
            setTitle(company);
        }
        if(code != null) {
            TextView codeView = (TextView) findViewById(R.id.couponCode);
            codeView.setText(code);
        }
        if(expiration != null && expiration != "") {
            if(DateManager.compareDates(DateManager.currentDate(), expiration)) {
                TextView expirationView = (TextView) findViewById(R.id.expirationView);
                expirationView.setText("Scaduto il " + expiration);
                expirationView.setVisibility(View.VISIBLE);
            }
        }
        // generate and display barcode
    }

    private Bitmap displayBarCode() {
        return null;
    }

    private Bitmap displayQrCode() {
        return null;
    }

}