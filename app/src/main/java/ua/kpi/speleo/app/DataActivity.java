package ua.kpi.speleo.app;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
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


        buttonSkeleton = (Button) findViewById(R.id.buttonSkeleton);
        buttonSketch = (Button) findViewById(R.id.buttonSketch);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonRefresh = (Button) findViewById(R.id.buttonRefresh);
        listViewData = (ListView) findViewById(R.id.listViewData);

        buttonRefresh.setOnClickListener(buttonRefreshOnClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(LOG_TAG,"onDestroy");
    }

    private View.OnClickListener buttonSaveOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };

    private View.OnClickListener buttonRefreshOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            list = new ArrayList<HashMap>();
            DataDAO dataDAO = new DataDAO(getApplicationContext());
            list = dataDAO.getDataListHashmap(caves);

            for (int i = 0; i < list.size(); i++) {
                Log.v(this.getClass().getSimpleName().toString(),"list " + list.get(i).get((String) Constants.COLUMN1));
            }

            ListviewAdapter adapter = new ListviewAdapter(getActivity(), list);
            listViewData.setAdapter(adapter);
        }
    };

    private Activity getActivity() {
        return this;
    }
}
