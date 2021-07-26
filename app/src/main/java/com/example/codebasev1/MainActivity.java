package com.example.codebasev1;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.codebasev1.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private DBConnection db=new DBConnection();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);*/

       /* FloatingActionButton fab = findViewById(R.id.fab);
        final String value= getIntent().getStringExtra("kullanici");

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentGiris(), "Tab 1");
        adapter.addFragment(new FragmentDetay(), "Tab 2");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);



        drawerLayout=findViewById(R.id.drawer);
        NavigationView navigationView=findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);

        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, QR_Scan.class);
               intent.putExtra("kullanici",value);
               startActivity(intent);
            }
        });*/
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
        switch (menuItem.getItemId()){
            case R.id.AnaSayfa:
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
        return false;
    }
}