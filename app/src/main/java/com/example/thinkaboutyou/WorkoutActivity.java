package com.example.thinkaboutyou;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_APPEND;
import static android.content.Context.MODE_PRIVATE;

public class WorkoutActivity extends Fragment {
    private static List<Workouts> currentWOList;
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
    GesWoAdapter adapterGesWO;
    FragmentManager fragmentManager = getFragmentManager();
    //Fragment f1 = new WOplayer();
    boolean selectedFRAGE;
    boolean setAdapterUeberprüfung;
    FirebaseFirestore db;
    boolean setGesWoUeberprüfung;
    List<Workouts> newList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_workout,container,false);
        WOlist = new ArrayList<>();
        currentWOList=new ArrayList<>();
        KINGlist = new ArrayList<>();
        loadApplication();
        readKingList();
        KINGlist.add(new GesammtWO("Brust", WOlist));
        selectedFRAGE = false;
        FirebaseFirestore.getInstance();
        setGesWoUeberprüfung=false;
        newList = new ArrayList<>();
        setAdapterUeberprüfung=false;
        WOlistView = view.findViewById(R.id.WOListView);
        WOfab = view.findViewById(R.id.WOfloatingActionButton);
        WOlistView.setAdapter(setGesWoAdapter());

        if (setGesWoUeberprüfung)
        {
            System.out.println("War HIER, setGesWoUeberprüfung");
            WOlistView.setAdapter((ListAdapter) setGesWoAdapter());
            setGesWoUeberprüfung=false;
        }

        WOfab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //add a NEW GESWO
                dialogcreateWO(View.inflate(getActivity(), R.layout.wofabaction, null));
            }
        });

        WOlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GesammtWO gesammtWO = KINGlist.get(position);
                dialogplayWO(View.inflate(getActivity(), R.layout.test, null), gesammtWO);
            }
        });




        return view;
    }

    public ShowWorkouts_Adapter setAdapter()
    {
        return showWorkouts_adapter = new ShowWorkouts_Adapter(getContext(),WOlist);
    }

    public void dialogplayWO(View view, final GesammtWO ges) {
        alert = new AlertDialog.Builder(getActivity());
        alert.setView(view).setCancelable(false);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        dialog = alert.create();

        TextView textView = view.findViewById(R.id.textView9);
        textView.setText("Möchtest du wirklich " +ges.getName()+" durchführen?");

        alert.setPositiveButton("TRAIN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             //----------------------------------------------------------------------------------

               Intent intent = new Intent(getActivity(), WOplayer.class);
               currentWOList=ges.getWos();
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
        loadApplication();
        FloatingActionButton fabWOcreate = view.findViewById(R.id.fabcreatewo);
        lv.setAdapter(setAdapter());


        fabWOcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                View view = View.inflate(getActivity(), R.layout.addnewexercise, null);
                dialognewexercise(view);
                setAdapterUeberprüfung=true;
            }


        });

        if (setAdapterUeberprüfung)
        {
            lv.setAdapter(setAdapter());
            setAdapterUeberprüfung=false;
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Workouts selected1 = WOlist.get(position);
                dialog(View.inflate(getActivity(), R.layout.test, null), selected1.getName());
                if (selectedFRAGE)
                {
                    newList.add(selected1);
                    selectedFRAGE=false;
                }
            }
        });

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                GesammtWO gesammtWO = new GesammtWO(name.getText().toString().trim(), newList);
                KINGlist.add(gesammtWO);
                writeKingList(gesammtWO);
                setGesWoUeberprüfung=true;
                newList.clear();
                System.out.println("War HIER, "+ gesammtWO.getName());
            }
        });

        alert.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newList.clear();
                dialog.dismiss();
            }
        });
        alert.show();
    }

    public void dialog(View view, String txt) {
        alert2 = new AlertDialog.Builder(getActivity());
        alert2.setView(view).setCancelable(false);
        TextView tv9 = view.findViewById(R.id.textView9);

        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        dialog2 = alert2.create();

        tv9.setText("Möchtest du wirklich" +txt+" hinzufügen?");

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
                boolean testwdh = false;
                boolean testtime = false;
                int wdh1;
                long time1;
                System.out.println("III:"+wdh.getText().toString()+";"+time.getText().toString());


                //output:

                //;;


                try{
                    wdh1 = Integer.valueOf(wdh.getText().toString());
                    testwdh=true;

                }catch (Exception ex)
                {
                    testwdh = false;
                }

                try{
                    time1 = Long.valueOf(time.getText().toString());
                    testtime=true;

                }catch (Exception ex)
                {
                    testtime = false;
                }

                if (testtime)
                {
                    if (testwdh)
                    {
                        Workouts wo = new Workouts(name.getText().toString().trim(), Integer.valueOf(wdh.getText().toString().trim()), null, Long.valueOf(time.getText().toString().trim()));
                        writeCsv1(wo);
                        WOlist.add(wo);
                    }else
                    {
                        Workouts wo = new Workouts(name.getText().toString().trim(), -1, null, Long.valueOf(time.getText().toString().trim()));
                        writeCsv1(wo);
                        WOlist.add(wo);
                    }

                }
                else if (testwdh)
                {
                    Workouts wo = new Workouts(name.getText().toString().trim(), Integer.valueOf(wdh.getText().toString().trim()), null, -1);
                    writeCsv1(wo);
                    WOlist.add(wo);
                }

                else
                {
                    Toast.makeText(getContext(),"FEHLER BEI DER ERSTELLUNG EINER NEUEN ÜBUNG",Toast.LENGTH_LONG).show();
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
    public void writeCsv1(Workouts workouts)  {

        String filename = "WOs.csv";
        try
        {
            FileOutputStream fos = getContext().openFileOutput(filename, MODE_PRIVATE | MODE_APPEND);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));

            out.println(workouts.toString());

            out.flush();
            out.close();


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void loadApplication() {
        String filename = "WOs.csv";
        WOlist.clear();

        try {
            FileInputStream fis = getContext().openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;

            try {
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split(";");
                    String name = arr[0];
                    String wdh = arr[1];
                    String imagepath = arr[2];
                    String time = arr[3];
//                    if (Integer.valueOf(wdh)==-1)
//                    {
//
//                    }
//                    else if(Long.valueOf(time)==-1)
//                    {
//
//                    }
                    if(imagepath==null)
                    {
                        imagepath="-1";
                    }

                    WOlist.add(new Workouts(name, Integer.valueOf(wdh), imagepath, Long.valueOf(time)));

                }
            } catch (Exception e) {
                //readAssets();
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            //readAssets();
            System.out.println("Keine Datei gefunden!");
        }


    }

    public GesWoAdapter setGesWoAdapter()
    {
        return adapterGesWO = new GesWoAdapter(getContext(),KINGlist);
    }

    public void writeToDB(Workouts workouts)
    {
        Map<Integer,Workouts> wo = new HashMap<>();
        wo.put(WOlist.size(),workouts);



        db.collection("WOs")
                .add(wo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    public void readFromDB()
    {
        db.collection("WOs")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            WOlist.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Map<String, Object> wo = document.getData();
                                WOlist.add((Workouts) wo.values());

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public static List<Workouts> getCurrentWOList()
    {
        return currentWOList;
    }

    public void writeKingList(GesammtWO gesammtWO)
    {
        String filename = "KingList.csv";
        try
        {
            FileOutputStream fos = getContext().openFileOutput(filename, MODE_PRIVATE | MODE_APPEND);
            PrintWriter out = new PrintWriter(new OutputStreamWriter(fos));

            String x = gesammtWO.getName();
            for (int i = 0; i < gesammtWO.getWos().size(); i++) {

                x+=";"+gesammtWO.getWos().get(i).getName();

            }

            out.println(x);

            out.flush();
            out.close();


        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void readKingList()
    {
        String filename = "KingList.csv";
        KINGlist.clear();

        try {
            FileInputStream fis = getContext().openFileInput(filename);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));

            String line;
            List<Workouts> currentreadList = new ArrayList<>();
            try {
                while ((line = br.readLine()) != null) {
                    String[] arr = line.split(";");
                    String name = arr[0];


                    for (int i = 1; i < arr.length; i++) {
                        for (int j = 0; j < WOlist.size(); j++) {
                            if(arr[i].equals(WOlist.get(j)))
                            {
                                currentreadList.add(WOlist.get(j));
                            }
                        }
                    }

                    GesammtWO ges =  new GesammtWO(name, currentreadList);
                    KINGlist.add(ges);

                }
            } catch (Exception e) {
                //readAssets();
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            //readAssets();
            System.out.println("Keine Datei gefunden!");
        }

    }


}
