package com.postification.postification;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.content.SharedPreferences.Editor;

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

        // idがswitchButtonのSwitchを取得
        Switch switchButton = activity.findViewById(R.id.notif_switch);

        SharedPreferences data = activity.getSharedPreferences(databaseName,Context.MODE_PRIVATE);
        switchButton.setChecked(data.getBoolean("notification",true));

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




    }
}
