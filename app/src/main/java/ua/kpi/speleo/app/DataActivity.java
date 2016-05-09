package ua.kpi.speleo.app;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.CavesDAO;
import ua.kpi.speleo.app.db.Data;
import ua.kpi.speleo.app.db.DataDAO;
import ua.kpi.speleo.app.services.BluetoothListenerService;

import java.util.ArrayList;
import java.util.HashMap;

public class DataActivity extends Activity {
    private static final String LOG_TAG = DataActivity.class.getSimpleName();

    private Button buttonSkeleton;
    private Button buttonSketch;
    private Button buttonSave;
    private Button buttonRefresh;
    private ListView listViewData;

    private ArrayList<HashMap> list;

    private Caves caves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        Log.d(LOG_TAG, "getParcelableExtra");
        caves = (Caves) getIntent().getParcelableExtra("Caves");
        Log.d(LOG_TAG, "caves: " + caves.getId() + ", " + caves.getName());
        BluetoothDevice bluetoothDevice = BluetoothActivity.getBluetoothDeviceConnect();
        if(bluetoothDevice == null) {
            Toast.makeText(getApplicationContext(), "Connect to DistoX please", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), BluetoothActivity.class);
            startActivity(intent);
        }
        else {
            Log.d(LOG_TAG, "bluetooth_device: " + bluetoothDevice.getName() + " " + bluetoothDevice.getAddress());
        }

        if(BluetoothListenerService.isEnabled) {
            Intent intent = new Intent(getApplicationContext(), BluetoothListenerService.class);
            stopService(intent);
            Log.v(LOG_TAG,"Stop service");
        }
        else if(!BluetoothListenerService.isEnabled && (bluetoothDevice != null)) {
            Intent intent = new Intent(getApplicationContext(), BluetoothListenerService.class);
            intent.putExtra("device",bluetoothDevice);
            intent.putExtra("caves" ,caves);
            startService(intent);
            Log.v(LOG_TAG,"Start service");
        }

        //DataDAO dataDAO = new DataDAO(getApplicationContext());
        //dataDAO.deleteAll();

        buttonSkeleton = (Button) findViewById(R.id.buttonSkeleton);
        buttonSketch = (Button) findViewById(R.id.buttonSketch);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonRefresh = (Button) findViewById(R.id.buttonRefresh);
        listViewData = (ListView) findViewById(R.id.listViewData);

        buttonRefresh.setOnClickListener(buttonRefreshOnClickListener);
        buttonSave.setOnClickListener(buttonSaveOnClickListener);
        buttonSkeleton.setOnClickListener(buttonSkeletonOnClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG,"onDestroy");
    }

    private View.OnClickListener buttonSaveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if(listViewData.isFocusable()) {
                    /*for (int i = 0; i < listViewData.getAdapter().getCount(); i++) {
                       View view1 = listViewData.getChildAt(i);
                            EditText text1 = (EditText) view1.findViewById(R.id.column1);
                            EditText text2 = (EditText) view1.findViewById(R.id.column2);
                            EditText text3 = (EditText) view1.findViewById(R.id.column3);
                            EditText text4 = (EditText) view1.findViewById(R.id.column4);
                            EditText text5 = (EditText) view1.findViewById(R.id.column5);
                            String content1 = text1.getText().toString();
                            String content2 = text2.getText().toString();
                            String content3 = text3.getText().toString();
                            String content4 = text4.getText().toString();
                            String content5 = text5.getText().toString();

                            Log.d(LOG_TAG, "SAVESAVE ID " + list.get(i).get(Constants.ID) + " " + content1 + " " + content2 + " " + content3 + " " + content4 + " " + content5);
                        }*/
            }
            else {
                Toast.makeText(getApplicationContext(),"Please click refresh button", Toast.LENGTH_LONG).show();
            }
        }
    };

    private View.OnClickListener buttonRefreshOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            list = new ArrayList<HashMap>();
            DataDAO dataDAO = new DataDAO(getApplicationContext());
            list = dataDAO.getDataListHashmap(caves);
            ListviewAdapter adapter = new ListviewAdapter(getActivity(), list);
            listViewData.setAdapter(adapter);
        }
    };

    private View.OnClickListener buttonSkeletonOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), SkeletonActivity.class);
            intent.putExtra("Caves", caves);
            startActivity(intent);
        }
    };

    private Activity getActivity() {
        return this;
    }
}
