package com.juancoob.practicegoogleexam.ViewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

/**
 * Created by Juan Antonio Cobos Obrero on 5/10/18.
 */
public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    public ViewModelFactory() {

    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MainViewModel();
    }
}
