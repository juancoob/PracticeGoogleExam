package com.juancoob.practicegoogleexam.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Comparator;

/**
 * Created by Juan Antonio Cobos Obrero on 6/10/18.
 */
public class Country implements Parcelable {

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public static Comparator<Country> CountryNameComparator = new Comparator<Country>() {
        @Override
        public int compare(Country country1, Country country2) {
            String countryName1 = country1.getName().toUpperCase();
            String countryName2 = country2.getName().toUpperCase();
            return countryName1.compareTo(countryName2);
        }
    };

    public static Comparator<Country> CountryNumberComparator = new Comparator<Country>() {
        @Override
        public int compare(Country country1, Country country2) {
            int countryNumber1 = country1.getNumber();
            int countryNumber2 = country2.getNumber();
            return countryNumber1 - countryNumber2;
        }
    };

    private String mName;
    private int mNumber;

    public Country(String name, int number) {
        mName = name;
        mNumber = number;
    }

    protected Country(Parcel in) {
        mName = in.readString();
        mNumber = in.readInt();
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public int getNumber() {
        return mNumber;
    }

    public void setNumber(int mNumber) {
        this.mNumber = mNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeInt(mNumber);
    }
}
