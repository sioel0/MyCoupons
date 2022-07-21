package it.unipr.mobdev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CouponList list = CouponList.getInstance();
        Gson gson = new Gson();
        try {
            FileInputStream file = openFileInput("couponList");
            BufferedReader data = new BufferedReader(new InputStreamReader(file));
            String line = null;

            while((line = data.readLine()) != null) {
                Coupon c = gson.fromJson(line, Coupon.class);
                list.addElement(c);
            }
        } catch (IOException e) {
            Toast.makeText(this, "Impossibile caricare i dati", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CouponAdapter());
    }

    public void addButtonPressed(View view) {
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // save data to file
        try {
            FileOutputStream file = openFileOutput("couponList", Context.MODE_PRIVATE);
            Gson gson = new Gson();
            CouponList instance = CouponList.getInstance();
            for (int i = 0; i < instance.size(); ++i) {
                String data = gson.toJson(instance.couponAtIndex(i))+"\n";
                file.write(data.getBytes());
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Toast.makeText(this, "Impossibile salvare i dati", Toast.LENGTH_SHORT).show();
        }
    }

}