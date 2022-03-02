package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShiftFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShiftFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    MainActivity mainActivity;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShiftFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShiftFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShiftFragment newInstance(String param1, String param2) {
        ShiftFragment fragment = new ShiftFragment();
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
        View view =  inflater.inflate(R.layout.fragment_shift, container, false);

//        MainActivity mainActivity =


        CalendarView calendarView = (CalendarView) view.findViewById(R.id.calendarView);
        CheckBox morningCheckBox = (CheckBox) view.findViewById(R.id.MorningBox);
        CheckBox noonCheckBox = (CheckBox) view.findViewById(R.id.NoonBox);
        CheckBox eveningCheckBox = (CheckBox) view.findViewById(R.id.eveningBox);
        Button submitBtn = (Button) view.findViewById(R.id.submitBtn);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) { // year month day
                mainActivity= (MainActivity) getActivity();
//                the month is counted from 0 so we needed to add 1 to present the correct month
//                String date = i2 + "/" + i1+1 + "/" + i ;
                mainActivity.pickADay(calendarView, i2 , i1 ,1);
            }
        });

        morningCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity= (MainActivity) getActivity();
                mainActivity.setShiftPick(1);
            }
        });
        noonCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity= (MainActivity) getActivity();
                mainActivity.setShiftPick(2);
            }
        });
        eveningCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity= (MainActivity) getActivity();
                mainActivity.setShiftPick(3);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity= (MainActivity) getActivity();
                mainActivity.updateNewShift();
            }
        });

//                Toast.makeText(MainActivity,"dsfghf ",Toast.LENGTH_SHORT).show();


        return view;
    }
}