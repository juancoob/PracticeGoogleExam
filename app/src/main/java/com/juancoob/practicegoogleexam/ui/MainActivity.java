package com.juancoob.practicegoogleexam.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.juancoob.practicegoogleexam.R;
import com.juancoob.practicegoogleexam.ui.custom.EditTextWithClear;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @BindView(R.id.et_custom)
    public EditTextWithClear customEditTextWithClear;

    @BindView(R.id.fab_new_activity)
    public FloatingActionButton newActivityFloatingActionButton;

    @BindView(R.id.iv_sample)
    public ImageView sampleImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setupSharedPreferences();
    }

    public void setupSharedPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        manageEditText(sharedPreferences);
        manageFABBackgroundColor(sharedPreferences);
        manageImage(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @OnClick(R.id.fab_new_activity)
    public void onClickNewActivityFloatingActionButton() {
        Intent intentToDetailActivity = new Intent(this, DetailActivity.class);
        startActivity(intentToDetailActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.i_settings) {
            Intent intent = new Intent(this, PreferenceActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        if(key.equals(getString(R.string.pref_show_image_key))) {
            manageImage(sharedPreferences);
        } else if(key.equals(getString(R.string.pref_show_hint_key))) {
            manageEditText(sharedPreferences);
        } else if(key.equals(getString(R.string.pref_change_fab_color_key))) {
            manageFABBackgroundColor(sharedPreferences);
        }
    }

    private void manageImage(SharedPreferences sharedPreferences) {
        if(sharedPreferences.getBoolean(getString(R.string.pref_show_image_key), false)) {
            sampleImageView.setVisibility(View.VISIBLE);
        } else {
            sampleImageView.setVisibility(View.GONE);
        }
    }

    private void manageFABBackgroundColor(SharedPreferences sharedPreferences) {
        String color = sharedPreferences.getString(getString(R.string.pref_change_fab_color_key), getString(R.string.pref_change_fab_color_default_value));
        newActivityFloatingActionButton.setBackgroundTintList(color != null && color.equals(getString(R.string.pref_change_fab_color_default_value)) ? ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)) : ColorStateList.valueOf(getResources().getColor(R.color.red)));
    }

    private void manageEditText(SharedPreferences sharedPreferences) {
        customEditTextWithClear.setHint(sharedPreferences.getString(getString(R.string.pref_show_hint_key),
                getString(R.string.write_something)));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // To avoid memory leaks
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }
}
