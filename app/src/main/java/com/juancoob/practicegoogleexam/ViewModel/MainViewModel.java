package com.juancoob.practicegoogleexam.ViewModel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Juan Antonio Cobos Obrero on 5/10/18.
 */
public class MainViewModel extends ViewModel {

    private static final String TAG = MainViewModel.class.getName();

    private MutableLiveData<List<String>> countryList;

    public MainViewModel() {
        countryList = new MutableLiveData<>();
    }

    public MutableLiveData<List<String>> getCountryList() {
        return countryList;
    }

    public void countCountryList(Context ctx) {
        List<String> countryList = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(ctx.getAssets().open("countries.txt")));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                countryList.add(line);
            }
        } catch (IOException exception) {
            Log.e(TAG, "Error: " + exception.getMessage());
        }
        getCountryList().setValue(countryList);
    }

}
