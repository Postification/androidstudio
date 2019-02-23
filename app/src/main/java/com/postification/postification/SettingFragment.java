package com.postification.postification;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.content.SharedPreferences.Editor;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingFragment extends Fragment {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private static final String  databaseName = "setting";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Activity activity=getActivity();

        Switch switchButton = activity.findViewById(R.id.notifSwitch);

        final EditText moreBig=activity.findViewById(R.id.moreBigEditText);
        final EditText moreMedium=activity.findViewById(R.id.moreMediumEditText);
        final EditText lessMedium=activity.findViewById(R.id.lessMediumEditText);
        final EditText lessSmall=activity.findViewById(R.id.lessSmallEditText);

        String bigText,smallText;
        SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
        switchButton.setChecked(data.getBoolean("notification",true));

        bigText=String.valueOf(data.getInt("list_size_big",300));
        smallText=String.valueOf(data.getInt("list_size_small",50));
        moreBig.setText(bigText, TextView.BufferType.NORMAL);
        moreMedium.setText(smallText, TextView.BufferType.NORMAL);
        lessMedium.setText(bigText, TextView.BufferType.NORMAL);
        lessSmall.setText(smallText, TextView.BufferType.NORMAL);

        // switchのオンオフが切り替わった時の処理を設定
        switchButton.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener(){
                    public void onCheckedChanged(CompoundButton comButton, boolean isChecked){
                        SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
                        Editor editor = data.edit();
                        if(isChecked){
                            //Firebase Databaseの /user/user1/send を1にする
                            DatabaseReference myRef = database.getReference("/user/user1/send");
                            myRef.setValue(1);

                            editor.putBoolean("notification", true);
                        }
                        // オフなら
                        else{
                            //Firebase Databaseの /user/user1/send を0にする
                            DatabaseReference myRef = database.getReference("/user/user1/send");
                            myRef.setValue(0);

                            editor.putBoolean("notification", false);
                        }
                        editor.apply();
                    }
                }
        );

        //editTextの変更に対しての処理

        //moreBigの変更
        moreBig.addTextChangedListener(new TextWatcher() {
            SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
            Editor editor = data.edit();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text=String.valueOf(s);
                int n= Integer.parseInt(text);
                editor.putInt("list_size_big", n);
                editor.apply();

            }
        });

        //moreMediumの変更
        moreMedium.addTextChangedListener(new TextWatcher() {
            SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
            Editor editor = data.edit();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text=String.valueOf(s);
                int n= Integer.parseInt(text);
                editor.putInt("list_size_small", n);
                editor.apply();
            }
        });

        //lessMediumの変更
        lessMedium.addTextChangedListener(new TextWatcher() {
            SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
            Editor editor = data.edit();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text=String.valueOf(s);
                int n= Integer.parseInt(text);
                editor.putInt("list_size_big", n);
                editor.apply();
            }
        });

        //lessSmallの変更
        lessSmall.addTextChangedListener(new TextWatcher() {
            SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
            Editor editor = data.edit();

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text=String.valueOf(s);
                int n= Integer.parseInt(text);
                editor.putInt("list_size_small", n);
                editor.apply();
            }
        });



    }
}
