package ua.kpi.speleo.app;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ua.kpi.speleo.R;

public class MainActivity extends Activity {
    private Button buttonBluetooth;
    private Button buttonMaps;
    private Button buttonSettings;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        buttonBluetooth = (Button) findViewById(R.id.buttonBluetooth);
        buttonMaps = (Button) findViewById(R.id.buttonMaps);
        buttonSettings = (Button) findViewById(R.id.buttonSettings);

        buttonBluetooth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BluetoothActivity.class);
                startActivity(intent);
            }
        });
        buttonMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
