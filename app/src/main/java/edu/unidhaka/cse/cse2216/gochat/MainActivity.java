package edu.unidhaka.cse.cse2216.gochat;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {



    private Toolbar mToolbar;
    private ViewPager mViewpage;
    private TabLayout mTablayout;
    private TabAccessorAdaptor mtabsAccessorAdaptor;

    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mToolbar = (Toolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("GoChat");

        mtabsAccessorAdaptor = new TabAccessorAdaptor(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewpage = (ViewPager) findViewById(R.id.main_tabs_pager);
        setupViewPager(mViewpage);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewpage);


    }

    private void setupViewPager(ViewPager mViewpage) {
        TabAccessorAdaptor adapter = new TabAccessorAdaptor(getSupportFragmentManager());
        adapter.addFragment(new ChatsFragment(), "Chats");
        adapter.addFragment(new GroupsFragment(), "Groups");
        adapter.addFragment(new ContactsFragment(), "Contacts");
        mViewpage.setAdapter(adapter);
    }


    @Override
    protected void onStart() {
        super.onStart();

        if(currentUser == null){
            SendUserToLoginActivity();
        }
    }

    private void SendUserToLoginActivity() {
        Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }
}

