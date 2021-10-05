package com.example.heartsound.Basic_information;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.heartsound.R;


public class PersonFragment extends Fragment {

    static public EditText editText,editText2;
    static public View mainView;
    static public Button last_page;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_person, container, false);
        editText = mainView.findViewById(R.id.editText);
        editText2 = mainView.findViewById(R.id.editText2);
        last_page = (Button) mainView.findViewById(R.id.last_page);
        Activity mActivity=this.getActivity();

        last_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mActivity,"已儲存",Toast.LENGTH_SHORT).show();
            }
        });




        return mainView;
    }

}
