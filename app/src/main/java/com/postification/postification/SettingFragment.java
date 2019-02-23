package com.postification.postification;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.content.SharedPreferences.Editor;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingFragment extends Fragment implements TextView.OnEditorActionListener {

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

        final EditText bigEditText=activity.findViewById(R.id.bigEditText);
        final EditText smallEditText=activity.findViewById(R.id.smallEditText);

        String bigText,smallText;
        SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
        switchButton.setChecked(data.getBoolean("notification",true));

        bigText=String.valueOf(data.getInt("list_size_big",300));
        smallText=String.valueOf(data.getInt("list_size_small",50));
        bigEditText.setText(bigText, TextView.BufferType.NORMAL);
        smallEditText.setText(smallText, TextView.BufferType.NORMAL);

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

        smallEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Log.e("onEditorAction","call");
                    SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
                    Editor editor = data.edit();
                    String text=String.valueOf(v.getText());
                    int n=Integer.parseInt(text);
                    editor.putInt("list_size_big", n);
                    editor.apply();
                }
                return false;
            }
        });

    }










    public boolean onEditorAction(TextView v, int actionId, KeyEvent event){
        Activity activity=getActivity();
        SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
        Editor editor = data.edit();
        String text;
        int n;
        Log.e("onEditorAction","call");

        if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
            if(v.getId()==R.id.bigEditText){
                Log.e("bigEditText","call");
                text=String.valueOf(v.getText());
                n=Integer.parseInt(text);
                editor.putInt("list_size_big", n);
                editor.apply();
            }
            else if(v.getId()==R.id.smallEditText){
                Log.e("smallEditText","call");
                text=String.valueOf(v.getText());
                n=Integer.parseInt(text);
                editor.putInt("list_size_small", n);
                editor.apply();
            }
        }

        return false;
    }
}
