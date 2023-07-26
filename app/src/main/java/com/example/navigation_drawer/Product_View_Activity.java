package com.example.navigation_drawer;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Product_View_Activity extends AppCompatActivity {

    int i;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    TextView hname,hemail;
    EditText productname,productprice,productdec;
    Button addbutton,updatebutton;
    ImageView himageView,productimageview;

    String name,email;
    FirebaseAuth mAuth;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);

//        mAuth = FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawer);
        navigationView=findViewById(R.id.navigation);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        View view=navigationView.getHeaderView(0);
        hname=view.findViewById(R.id.hader_name);
        hemail=view.findViewById(R.id.hader_email);
        himageView=view.findViewById(R.id.hader_image);
        hname.setText(""+name);
        hemail.setText(""+email);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId()==R.id.home)
                {
//                    Intent intent=new Intent(product_view_activity.this,home_activity.class);
//                    startActivity(intent);
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (item.getItemId()==R.id.userproduct)
                {

                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (item.getItemId()==R.id.add)
                {
                    drawerLayout.closeDrawer(Gravity.LEFT);
                }
                if (item.getItemId()==R.id.logout)
                {
                    AlertDialog.Builder builder=new AlertDialog.Builder(Product_View_Activity.this);
                    builder.setTitle("Alert...!");
                    builder.setMessage("Are you sure?"+"\n" +
                            "you want to logout");
                    builder.setPositiveButton("LOGOUT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            editor.putInt("login",0);
                            editor.commit();
                            FirebaseAuth.getInstance().signOut();
                            Intent intent = new Intent(Product_View_Activity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.show();
                }
                return true;
            }
        });
    }
}