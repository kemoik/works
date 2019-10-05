package com.example.work;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Jobs
{
    String companyName;
    String profession;
    String location;
    String date;


    public Jobs()
    {

    }
    public Jobs(String companyname,String profession,String date,String location){
        this.companyName=companyname;
        this.profession=profession;
        this.date=date;
        this.location=location;
    }



    public String getCompanyName(){
        return companyName;
    }
    public String getProfession(){
        return profession;
    }
    public String getLocation() {
        return location;
    }
    public String getDate() {
        return date;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public void setDate(String date) {
        this.date = date;
    }

    protected Jobs(Parcel in)
    {
        companyName = in.readString();
        profession = in.readString();
        location = in.readString();
        date = in.readString();
    }

    public static final Parcelable.Creator<Jobs> CREATOR = new Parcelable.Creator<Jobs>() {
        @Override
        public Jobs createFromParcel(Parcel in) {
            return new Jobs(in);
        }

        @Override
        public Jobs[] newArray(int size) {
            return new Jobs[size];
        }
    };
    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(companyName);
        dest.writeString(profession);
        dest.writeString(location);
        dest.writeString(date);
    }



}
