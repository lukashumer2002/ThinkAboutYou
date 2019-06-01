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
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

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

        alert.setPositiveButton("TRAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             //----------------------------------------------------------------------------------

               Intent intent = new Intent(getActivity(), WOplayer.class);
               startActivity(intent);

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
        final List<Workouts> newList = new ArrayList<>();
        alert = new AlertDialog.Builder(getActivity());
        alert.setView(view).setCancelable(false);

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        dialog = alert.create();
        TextView tv = view.findViewById(R.id.textView5);
        final EditText name = view.findViewById(R.id.WOfabeditText);
        ListView lv = view.findViewById(R.id.WOfabListView);

        FloatingActionButton fabWOcreate = view.findViewById(R.id.fabcreatewo);

        fabWOcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                View view = View.inflate(getActivity(), R.layout.addnewexercise, null);
                dialognewexercise(view);


            }
        });


        lv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Workouts selected1 = WOlist.get(position);

                dialog(View.inflate(getActivity(), R.layout.test, null), selected1.getName());
                if (selectedFRAGE)
                {
                    newList.add(selected1);
                    selectedFRAGE=false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                GesammtWO gesammtWO = new GesammtWO(name.getText().toString().trim(), newList);
                KINGlist.add(gesammtWO);

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

    public void dialognewexercise(View view) {
        alert2 = new AlertDialog.Builder(getActivity());
        alert2.setView(view).setCancelable(false);

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        dialog2 = alert2.create();

        final EditText name = view.findViewById(R.id.editText);
        final EditText wdh = view.findViewById(R.id.editTextWDH);
        final EditText time = view.findViewById(R.id.editTextTime);

        alert2.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(wdh.getText().toString().trim() == null|| wdh.getText().toString().trim() == "0")
                {
                    if (time.getText().toString().trim()==null||time.getText().toString().trim()=="0")
                    {
                        Toast.makeText(getContext(),"FEHLER BEI DER ERSTELLUNG EINER NEUEN ÜBUNG",Toast.LENGTH_LONG).show();
                    }else {

                        Workouts wo = new Workouts(name.getText().toString().trim(), -1, null, Long.valueOf(time.getText().toString().trim()));
                        WOlist.add(wo);
                    }
                }
                else if(time.getText().toString().trim()==null||time.getText().toString().trim()=="0")
                {
                    if(wdh.getText().toString().trim() == null|| wdh.getText().toString().trim() == "0")
                    {

                        Toast.makeText(getContext(),"FEHLER BEI DER ERSTELLUNG EINER NEUEN ÜBUNG",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Workouts wo = new Workouts(name.getText().toString().trim(), Integer.valueOf(wdh.getText().toString().trim()), null, -1);
                        WOlist.add(wo);
                    }
                }

                else
                {
                    Workouts wo = new Workouts(name.getText().toString().trim(), Integer.valueOf(wdh.getText().toString().trim()), null, Long.valueOf(time.getText().toString().trim()));
                    WOlist.add(wo);
                }

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

//    public void writeCsv1(String x)  {
//
//        String filename = "Übungen.csv";
//        try
//        {
//            FileOutputStream fos = openFileOutput(filename, MODE_PRIVATE | MODE_APPEND);
//            PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));
//            out.println(x);
//            out.flush();
//            out.close();
//
//
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }


}
