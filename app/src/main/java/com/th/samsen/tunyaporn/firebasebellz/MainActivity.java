package com.th.samsen.tunyaporn.firebasebellz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.th.samsen.tunyaporn.firebasebellz.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Add Fragment
        if (savedInstanceState == null) {
//            Add to Activity
            getSupportFragmentManager().beginTransaction().add(R.id.contentMainFragment, new MainFragment()).commit();


        }


    }
}
