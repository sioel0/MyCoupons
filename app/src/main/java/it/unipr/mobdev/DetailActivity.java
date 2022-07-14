package it.unipr.mobdev;

import static it.unipr.mobdev.CouponAdapter.ViewHolder.COMPANY_NAME;
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

// TODO: add qr/bar code generation and display
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String code = intent.getStringExtra(COUPON_CODE);
        String company = intent.getStringExtra(COMPANY_NAME);
        String expiration = intent.getStringExtra(COUPON_EXPIRATION);
        if(company != null) {
            setTitle(company);
        }
        if(code != null) {
            TextView codeView = (TextView) findViewById(R.id.couponCode);
            codeView.setText(code);
        }
        if(expiration != null && expiration != "") {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            if(compareDates(formatter.format(now), expiration)) {
                TextView expirationView = (TextView) findViewById(R.id.expirationView);
                expirationView.setText("Scaduto il " + expiration);
                expirationView.setVisibility(View.VISIBLE);
            }
        }
        // generate and display barcode
        ImageView displayCode = (ImageView)findViewById(R.id.qrBarCode);
    }

    // checks if date1 comes before date2
    private boolean compareDates(String date1, String date2) {
        if(date1.compareTo(date2) == 0)
            return false;

        String[] d1 = date1.split("/");
        String[] d2 = date2.split("/");

        if(d1[2].compareTo(d2[2]) > 0)
            return true;
        if(d1[2].compareTo(d2[2]) == 0 && d1[1].compareTo(d2[1]) > 0)
            return true;
        if(d1[2].compareTo(d2[2]) == 0 && d1[1].compareTo(d2[1]) == 0 && d1[0].compareTo(d2[0]) > 0)
            return true;
        return false;
    }

    private Bitmap displayBarCode() {
        return null;
    }

    private Bitmap displayQrCode() {
        return null;
    }

}