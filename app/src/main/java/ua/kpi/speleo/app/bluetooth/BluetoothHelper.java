package ua.kpi.speleo.app.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import java.util.List;

public class BluetoothHelper {
    private BluetoothAdapter bluetoothAdapter;
    private ArrayAdapter<String> bluetoothArrayAdapter;
    private List<BluetoothDevice> bluetoothList;

    public BluetoothHelper() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean isBluetoohSupported() {
        if(bluetoothAdapter == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public void enable() {
        if(isBluetoohSupported() && !bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.enable();
        }
    }

    public void disable() {
        if(isBluetoohSupported() && bluetoothAdapter.isEnabled()) {
            bluetoothAdapter.disable();
        }
    }

    public boolean isEnable() {
        return bluetoothAdapter.isEnabled();
    }

    public void find() {
        if (bluetoothAdapter.isDiscovering()) {
            // the button is pressed when it discovers, so cancel the discovery
            bluetoothAdapter.cancelDiscovery();
            Log.v("BT","Bluetooth cancel");
        }
        else {
            bluetoothAdapter.startDiscovery();
            Log.v("BT","Bluetooth search");
        }
    }
}
