package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.objects.UserPojo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;


public class FragmentReg extends Fragment {

    // TODO: nRename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentReg() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentReg newInstance(String param1, String param2) {
        FragmentReg fragment = new FragmentReg();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reg, container, false);
        Button regBtnR = (Button) view.findViewById(R.id.regBtnR);

        regBtnR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
//                mainActivity.userEmail= ((EditText)view.findViewById(R.id.emailReg)).getText().toString();
//                mainActivity.userName= ((EditText)view.findViewById(R.id.usernameReg)).getText().toString();
//                UserPojo userPojo = new UserPojo(
//                        ((EditText)view.findViewById(R.id.emailReg)).getText().toString(),
//                        ((EditText)view.findViewById(R.id.usernameReg)).getText().toString(),
//                        ((EditText)view.findViewById(R.id.companyReg)).getText().toString()
//                );
                Map<String, Object> user = new HashMap<>();
                user.put("email",((EditText)view.findViewById(R.id.emailReg)).toString());
                user.put("userName",((EditText)view.findViewById(R.id.usernameReg)).toString());
                user.put("company",((EditText)view.findViewById(R.id.companyReg)).toString());

//                userPojo.setEmail(((EditText)view.findViewById(R.id.emailReg)).getText().toString());
//                userPojo.setUserName(((EditText)view.findViewById(R.id.usernameReg)).getText().toString());
//                userPojo.setCompany(((EditText)view.findViewById(R.id.companyReg)).getText().toString());

                mainActivity.db.collection("users")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Log.d("registration", "DocumentSnapshot added with ID: " + documentReference.getId());

                            mainActivity.regToLog();
                        }
                    })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("registration", "Error adding document", e);
                            }
                        });



            }
        });

        return view;
    }
}