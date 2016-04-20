package ua.kpi.speleo.app;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.bluetooth.BluetoothHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad on 4/18/2016.
 */
public class BluetoothActivity extends Activity {
    private Switch switchBluetooth;
    private CheckBox checkBoxDistoX;
    private ListView listViewDevices;

    private final BluetoothAdapter bluetooth = BluetoothAdapter.getDefaultAdapter();
    private ArrayAdapter<String> bluetoothArrayAdapter;
    private List<BluetoothDevice> bluetoothList  = new ArrayList<BluetoothDevice>();

    final BroadcastReceiver bluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            Log.v("BT","in receive");
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                bluetoothArrayAdapter.add(device.getName() + "\n" + device.getBluetoothClass().getDeviceClass() + "\n" + device.getAddress() + "\n" + device.getBondState());
                bluetoothArrayAdapter.notifyDataSetChanged();

                bluetoothList.add(device);

                Log.v("Found",device.getName());
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);

        switchBluetooth = (Switch) findViewById(R.id.switchBluetooth);
        checkBoxDistoX = (CheckBox) findViewById(R.id.checkBoxDistoX);
        listViewDevices = (ListView) findViewById(R.id.listViewDevices);


        bluetoothArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listViewDevices.setAdapter(bluetoothArrayAdapter);



        if(bluetooth.isEnabled()) {
            switchBluetooth.setChecked(true);
            searchBluetoothDevices();
            Toast.makeText(getApplicationContext(),"Bluetooth already enable",Toast.LENGTH_LONG).show();
        }

        switchBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               /* if(isChecked) {
                    bluetooth.enable();
                    bluetooth.find();
                    Log.v("BT","start receiver");
                    registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
                    Toast.makeText(getApplicationContext(),"Bluetooth enable, searching",Toast.LENGTH_LONG).show();
                }
                else {
                    bluetooth.disable();
                    Log.v("BT","disable " + bluetoothList.size());
                    Toast.makeText(getApplicationContext(),"Bluetooth disable, cancel search",Toast.LENGTH_LONG).show();
                }*/

                if(isChecked) {
                    bluetooth.enable();
                    searchBluetoothDevices();
                } else {
                    bluetooth.disable();
                    unregisterReceiver(bluetoothReceiver);
                }
            }
        });

        listViewDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.v("on item",bluetoothList.get(i).toString());
            }
        });

    }

    private void searchBluetoothDevices() {
        if (bluetooth.isDiscovering()) {
            // the button is pressed when it discovers, so cancel the discovery
            bluetooth.cancelDiscovery();
            unregisterReceiver(bluetoothReceiver);
            Toast.makeText(getApplicationContext(),"Bluetooth cancel", Toast.LENGTH_LONG).show();
        }
        else {
            //bluetoothArrayAdapter.clear();
            //bluetoothList.clear();
            bluetooth.startDiscovery();
            registerReceiver(bluetoothReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
            Toast.makeText(getApplicationContext(),"Bluetooth search", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bluetoothReceiver);
    }
}

