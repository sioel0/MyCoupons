package it.unipr.mobdev;

import static it.unipr.mobdev.CouponAdapter.ViewHolder.CODE_FORMAT;
import static it.unipr.mobdev.CouponAdapter.ViewHolder.COMPANY_NAME;
import static it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_CODE;
import static it.unipr.mobdev.CouponAdapter.ViewHolder.COUPON_EXPIRATION;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

// TODO: add qr/bar code generation and display
public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String code = intent.getStringExtra(COUPON_CODE);
        String company = intent.getStringExtra(COMPANY_NAME);
        int format = intent.getIntExtra(CODE_FORMAT, 1);
        String expiration = intent.getStringExtra(COUPON_EXPIRATION);
        setTitle(company);
        TextView codeView = (TextView)findViewById(R.id.couponCode);
        codeView.setText(code);
        if(expiration != null) {
            TextView expirationView = (TextView) findViewById(R.id.expirationView);
            expirationView.setText("Scaduto il " + expiration);
            expirationView.setVisibility(View.VISIBLE);
        }
    }
}