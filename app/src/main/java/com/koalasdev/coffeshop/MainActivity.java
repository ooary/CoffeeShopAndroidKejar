package com.koalasdev.coffeshop;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //modifier widget variabel
    private EditText mEditTextNama;
    private Spinner mSpinnerMenu;
    private RadioGroup mRgFlavour;
    private Button mOrder,mReset,mMin,mPlus;
    private TextView mQuantity,mPrice;
    private RadioButton mChocolateFlavour,mMocchaFlavour;

    //inisialisasi variabel
    private int qty = 0;
    private int price = 0;
    //list isi spinner
    private List<String> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //inisialisasi View
        inisialisasiView();
        initEvent();
    }

    private void inisialisasiView() {
        //menampilkan actionBar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mEditTextNama = (EditText) findViewById(R.id.mEditTextNama);
        mSpinnerMenu = (Spinner) findViewById(R.id.mSpinnerMenu);
        mRgFlavour = (RadioGroup) findViewById(R.id.mRgFlavour);
        mOrder = (Button) findViewById(R.id.mButtonOrder);
        mReset = (Button) findViewById(R.id.mButtonReset);
        mPlus = (Button) findViewById(R.id.mButtonPlus);
        mMin = (Button) findViewById(R.id.mButtonMin);
        mPrice = (TextView) findViewById(R.id.mPrice);
        mQuantity = (TextView) findViewById(R.id.mQuantity);
        mChocolateFlavour = (RadioButton) findViewById(R.id.mChocolateFlavour);
        mMocchaFlavour = (RadioButton) findViewById(R.id.mMocchaFlavour);

        //inisialisasi nilai text view
        mPrice.setText(String.valueOf("Price: $0"));
        mQuantity.setText(String.valueOf(0));
    }
    //event on back press
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void initEvent(){

        mPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //qty = qty+1;
                qty++;
                mQuantity.setText(String.valueOf(qty));
                price = 5 * qty;
                mPrice.setText("Price: $" + price);
            }
        });
        mMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(price <=0){
                    Toast.makeText(MainActivity.this,"Tidak Bisa Kurang dari 0",Toast.LENGTH_SHORT).show();
                    mPrice.setText("Price: $0 " );
                }else{
                    qty--;
                    mQuantity.setText(String.valueOf(qty));
                    price = 5 * qty;

                    mPrice.setText("Price: $" + price);
                }

            }
        });
        mReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPrice.setText("Price: $0");
                mQuantity.setText("0");
            }
        });
        /*
                CONTEXT BISA ACTIVITY ATAU FRAGMENT
                ADAPTER FUNGSINYA PENAMPUNG DATA DARI SUMBER
         */
        //spinner data menu
        categories.add("Tea");
        categories.add("Coffe");
        categories.add("Milk");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        mSpinnerMenu.setAdapter(dataAdapter);
        //SET EVENT GET DATA FROM SPINNER
        mSpinnerMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,parent.getSelectedItem() +" Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //source link : https://www.tutorialspoint.com/android/android_spinner_control.htm

        // radio Button method get data from radio button
       mRgFlavour.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
               switch (checkedId){
                   case R.id.mChocolateFlavour :
                       Toast.makeText(MainActivity.this, "Chocolate Flavour", Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.mMocchaFlavour:
                       Toast.makeText(MainActivity.this, "Moccha Flavour", Toast.LENGTH_SHORT).show();
                       break;

               }
           }
       });
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }
    public void sendEmail(){
        String[] to = {"weduswak@gmail.com"};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL,to);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Order dengan Nama "+ mEditTextNama.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Nama Saya "+ mEditTextNama.getText() + ", Ane Mesen " +mSpinnerMenu.getSelectedItem() + " Sebanyak " + mQuantity.getText()
        +" seharga " + price);
        try{
            startActivity(Intent.createChooser(emailIntent,"Send mail..."));
            finish();
        }catch(android.content.ActivityNotFoundException e){
            Toast.makeText(this, "There is no gmail installed", Toast.LENGTH_SHORT).show();
        }

    }

}
