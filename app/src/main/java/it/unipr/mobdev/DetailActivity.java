package it.unipr.mobdev;

import static it.unipr.mobdev.CouponAdapter.ViewHolder.IDENTIFIER;
import static it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_CODE;
import static it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_EXPIRATION;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        displayBarCode();
        Switch qrBarSwitch = (Switch)  findViewById(R.id.qrBarSwitch);
        qrBarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                    displayQrCode();
                else
                    displayBarCode();
            }

        });
    }

    private void displayBarCode() {
        try {
            TextView couponCode = (TextView)findViewById(R.id.couponCode);
            String content = couponCode.getText().toString();
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.CODE_128, 400, 400);
            ImageView imageViewQrCode = (ImageView) findViewById(R.id.qrBarCode);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch(Exception e) {
            return;
        }
    }

    private void displayQrCode() {
        try {
            TextView couponCode = (TextView)findViewById(R.id.couponCode);
            TextView expiration = (TextView)findViewById(R.id.expirationView);
            String content = couponCode.getText().toString();
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 400, 400);
            ImageView imageViewQrCode = (ImageView) findViewById(R.id.qrBarCode);
            imageViewQrCode.setImageBitmap(bitmap);
        } catch(Exception e) {
            return;
        }
    }

}