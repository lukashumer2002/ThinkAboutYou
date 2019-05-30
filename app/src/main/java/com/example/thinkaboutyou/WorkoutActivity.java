package com.example.thinkaboutyou;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WorkoutActivity extends Fragment {
    private TextView mTextMessage;
    ShowWorkouts_Adapter showWorkouts_adapter;
    List<Workouts> WOlist;
    ListView WOlistView;
    List<GesammtWO> KINGlist;
    FloatingActionButton WOfab;
    AlertDialog dialog;
    AlertDialog.Builder alert;

    AlertDialog dialog2;
    AlertDialog.Builder alert2;
    boolean wodurchführen = false;
    FragmentManager fragmentManager = getFragmentManager();
    Fragment f1 = new WOplayer();
    boolean selectedFRAGE;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_workout,container,false);
        WOlist = new ArrayList<>();
        KINGlist = new ArrayList<>();
        selectedFRAGE = false;
        WOlistView = view.findViewById(R.id.WOListView);
        WOfab = view.findViewById(R.id.WOfloatingActionButton);
        WOfab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //add a NEW WO
                dialogcreateWO(View.inflate(getActivity(), R.layout.wofabaction, null));
            }
        });

        WOlistView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Workouts selected = WOlist.get(position);
                dialogplayWO(View.inflate(getActivity(), R.layout.test, null), selected.getName());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

    public void setAdapter()
    {
        showWorkouts_adapter = new ShowWorkouts_Adapter(getContext(),WOlist);

    }

    public void dialogplayWO( View view, String txt) {
        alert = new AlertDialog.Builder(getActivity());
        alert.setView(view).setCancelable(false);

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        dialog = alert.create();

        alert.setMessage("Möchtest du wirklich" +txt+" durchführen?");

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             //----------------------------------------------------------------------------------

                fragmentManager.beginTransaction()
                        .replace(R.id.action_workout
                                , f1)
                        .commit();

                //----------------------------------------------------------------------------------
            }
        });

        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public void dialogcreateWO(View view)
    {
        alert = new AlertDialog.Builder(getActivity());
        alert.setView(view).setCancelable(false);

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        dialog = alert.create();

        final EditText name = view.findViewById(R.id.textView5);
        ListView lv = view.findViewById(R.id.WOfabListView);
        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Workouts selected1 = WOlist.get(position);

                dialog(View.inflate(getActivity(), R.layout.test, null), selected1.getName());
                if (selectedFRAGE)
                {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public void dialog(View view, String txt) {
        alert2 = new AlertDialog.Builder(getActivity());
        alert2.setView(view).setCancelable(false);

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        dialog2 = alert2.create();

        alert.setMessage("Möchtest du wirklich" +txt+" hinzufügen?");

        alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               selectedFRAGE=true;
            }
        });

        alert2.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alert2.show();
    }



}
