package com.example.codebasev1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.iid.FirebaseInstanceId;

import java.sql.Connection;

public class MainSideBar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LinearLayout linearLayout;
    private CoordinatorLayout coordinatorLayout;
    private String user;
    DBConnection db=new DBConnection();
    Connection con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_side_bar);

        try {
            user = getIntent().getStringExtra("kullanici");
        }catch (Exception e){
            user="Kullanıcı";
        }

        FloatingActionButton fab = findViewById(R.id.fab2);
        viewPager = (ViewPager) findViewById(R.id.view_pager2);
        tabLayout = (TabLayout) findViewById(R.id.tabs2);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentGiris(), "Tab 1");
        adapter.addFragment(new FragmentDetay(), "Tab 2");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        linearLayout=findViewById(R.id.mainLL);
        coordinatorLayout=findViewById(R.id.coordinatorLL);

        drawerLayout=findViewById(R.id.drawer2);
        final NavigationView navigationView=findViewById(R.id.navigationView2);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.BLUE));

        View header = navigationView.getHeaderView(0);
        TextView navProfile = (TextView) header.findViewById(R.id.userTxt);
        ImageView userImage = (ImageView) header.findViewById(R.id.userPic);
        TextView userMail = (TextView) header.findViewById(R.id.mailTxt);

        try{
            navProfile.setText(user.toString());
            db.setUsername(user);
            con = db.connectionclass();
            db.setPic();
            SpannableString content = new SpannableString(db.getMail());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
            userMail.setText(content);
            if(content.length()>20 && content.length()<=28)
                userMail.setTextSize(15f);
            else if(content.length()>28)
            {
                userMail.setPadding(0,50,0,0);
                userMail.setTextSize(10f);
            }

            byte[] bitmapdata =db.getBarr();
            Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
            userImage.setImageBitmap(bitmap);

        }catch (Exception e){ Log.e("Db","girmedi");}

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        Log.e("Firebase", "token "+ FirebaseInstanceId.getInstance().getToken());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainSideBar.this, QR_Scan.class);
                intent.putExtra("kullanici",user);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()){
            case R.id.AnaSayfa:
                linearLayout.setVisibility(View.GONE);
                tabLayout.setVisibility(View.VISIBLE);
                coordinatorLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.Borçlar:
                intent= new Intent(getBaseContext(), viewBillByContractNumber.class);
                startActivity(intent);
                break;
            case R.id.Grafik:
                intent = new Intent(getBaseContext(), FaturaGrafik.class);
                startActivity(intent);
                break;
            case R.id.Ayarlar:
                intent = new Intent(getBaseContext(), ChooseAllowedNotificationActivity.class);
                startActivity(intent);
                break;

            case R.id.Logout:
                intent = new Intent(getBaseContext(), Login.class);
                startActivity(intent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}