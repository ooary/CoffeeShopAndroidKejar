package com.koalasdev.coffeshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    //method onOrderClickView
    public void onOrderCardViewClick(View v){
        Intent intent = new Intent(this,MainActivity.class);
//        intent.putExtra("message","Order Now")
        startActivity(intent);
    }
    public void onTopMenuCardViewClick(View v){
        Intent intent = new Intent(this,TopMenuActivity.class);
        startActivity(intent);
    }
    /*
            SETTINGAN MENU PADA POJOK KANAN
     */
    /*
        UNTUK MENAMPILKAN MENU
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }
    /*
        TRIGGER TOMBOL BACK PADA HP
     */
    @Override
    public void onBackPressed() {
        quitApplication();
    }

    /*
           UNTUK HANDLE CLICK MENU
        */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menuOrder:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.menuTop:
                Intent TopMenu = new Intent(this,TopMenuActivity.class);
                startActivity(TopMenu);
                return true;
            case R.id.menuAbout:
                Intent about = new Intent(this,AboutActivity.class);
                startActivity(about);
                return true;
            case R.id.menuQuit:
                quitApplication();

                return true;
            default:
                return super.onOptionsItemSelected(item);


        }


    }

    private void quitApplication() {
    /*
        MEMBUAT ALERT DIALOG BUILDERNYA DULU
     */
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Apakah Anda Yakin ingin Keluar");
                /*
                    MENSETTING APA BILA MENGCLICK ya
                 */
        alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
                /*
                    MENSETTING APABILA MENGCLICK NO
                 */
        alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
                /*
                    SETTING TAMPILAN ALERT DIALOG DAN BUAT DIALOG DARI DIALOG BUILDER DI ATAS
                 */
        AlertDialog alertDialog = alertDialogBuilder.create();
                /*
                    TAMPILKAN ALERTDIALOG
                 */
        alertDialog.show();
    }
}
