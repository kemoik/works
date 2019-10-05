package com.example.work;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User
{


    String names;
    String email;
    String cv;
    String skills;


    public User(String names, String email, String cv, String skills)
    {

    }
    public User(String name,String email,int number,String cv,String skills){
        this.names=names;
        this.email=email;
        this.cv=cv;
        this.skills=skills;
    }

    public String getNames() {
        return names;
    }
    public String getEmail() {
        return email;
    }

    public String getCv() {
        return cv;
    }
    public String getSkills(){
        return skills;
    }
    public void setNames(String names) {
        this.names = names;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    protected User(Parcel in)
    {
        names = in.readString();
        email = in.readString();

        cv = in.readString();
        skills = in.readString();
    }


    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(names);
        dest.writeString(email);

        dest.writeString(cv);
        dest.writeString(skills);
    }
}
