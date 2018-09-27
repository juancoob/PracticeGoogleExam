package com.juancoob.practicegoogleexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab_new_activity)
    public void onClickNewActivityFloatingActionButton() {
        Intent intentToDetailActivity = new Intent(this, DetailActivity.class);
        startActivity(intentToDetailActivity);
    }

}
