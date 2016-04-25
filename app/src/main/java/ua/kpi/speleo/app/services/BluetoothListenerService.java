package ua.kpi.speleo.app.services;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.Data;
import ua.kpi.speleo.app.db.DataDAO;
import ua.kpi.speleo.app.distox.DistoXData;
import ua.kpi.speleo.app.distox.DistoXEncoding;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BluetoothListenerService extends Service {

    private List<DistoXData> distoXData = new LinkedList();
    private BluetoothDevice device;
    private BluetoothSocket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private boolean stopWorker;
    private Thread workerThread;
    private byte[] input;

    private Caves caves;

    public static boolean isEnabled = false;

    private SharedPreferences preferencesTest;

    public BluetoothListenerService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("Service","Service create");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        isEnabled = true;
        super.onStart(intent, startId);
        Log.v("Service","Service start");
        device = intent.getParcelableExtra("device");
        caves = intent.getParcelableExtra("caves");
        Log.v("Service",device.toString());
        Log.v("Service",caves.toString());
        //connect to device
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb"); //Standard SerialPortService ID
        try {
            socket = device.createRfcommSocketToServiceRecord(uuid);
            socket.connect();
            outputStream = socket.getOutputStream();
            inputStream = socket.getInputStream();
        } catch (IOException e) {
            Log.v("Bluetooth_service",e.toString());
        }

        preferencesTest = getSharedPreferences("test", Context.MODE_PRIVATE);

        beginListenForData();
    }

    private void beginListenForData()
    {
        stopWorker = false;
        input = new byte[8];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
                        while (true) {
                            if (inputStream.available() >= 8) {
                                int oldType = 0, oldDist = 0, oldAzi = 0, oldIncl = 0;  // previous
                                inputStream.read(input, 0, 8); // receive 8 bytes
                                byte type = input[0];
                                if ((type & 0x3F) == 1) { // measurement data
                                    int distance = input[1] + (input[2] << 8) + ((type & 0x40) << 10);
                                    short azimuth = (short)(input[3] + (input[4] << 8));
                                    short inclination = (short)(input[5] + (input[6] << 8));
                                    outputStream.write(type & 0x80 | 0x55);  // send acknowledge byte
                                    if (type != oldType || distance != oldDist || azimuth != oldAzi || inclination != oldIncl) { // valid data
                                        DistoXEncoding distoXEncoding = new DistoXEncoding(input);
                                        distoXData.add(distoXEncoding.getEncodingData());

                                        SharedPreferences.Editor editor = preferencesTest.edit();
                                        editor.putString("info", distoXEncoding.getEncodingData().toString());
                                        editor.apply();

                                        Data data = new Data(distoXEncoding);
                                        DataDAO dataDAO = new DataDAO(getApplicationContext());
                                        data.setCaves(caves);

                                        dataDAO.save(data);

                                        /*
                                        Intent intent = new Intent();
                                        intent.setAction("BLUETOOTH_LISTENER_DATA");
                                        intent.putExtra("dist", distoXEncoding.getDistance());
                                        sendBroadcast(intent);
                                        */


                                        oldType = type;
                                        oldDist = distance;
                                        oldAzi = azimuth;
                                        oldIncl = inclination;
                                    }
                                }
                            }
                        }
                    }
                    catch (IOException ex) {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }

    @Override
    public void onDestroy() {
        Log.v("Service","Service stop");

        stopWorker = true;
        //workerThread.interrupt();
        try {
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            Log.v("Service","onDestroy " + e.toString());
        }
        try {
            socket.close();
        } catch (IOException e) {
            Log.v("Service","onDestroy " + e.toString());
        }

        Log.v("Service","onDestroy()" + " list size " + distoXData.size());

        for (int i = 0; i < distoXData.size(); i++) {
            Log.v("Service",distoXData.get(i).toString());
        }

        Log.v("Service","Service stop");

        isEnabled = false;

        super.onDestroy();
    }

}
