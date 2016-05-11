package ua.kpi.speleo.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.CavesDAO;
import ua.kpi.speleo.app.db.Data;
import ua.kpi.speleo.app.db.DataDAO;

public class AddDataActivity extends Activity {
    private EditText editTextFrom;
    private EditText editTextTo;
    private EditText editTextDistance;
    private EditText editTextAzimuth;
    private EditText editTextInclination;
    private Button buttonAdd;

    private int idCave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data);

        editTextFrom = (EditText) findViewById(R.id.editTextFrom);
        editTextFrom.setRawInputType(Configuration.KEYBOARD_12KEY);

        editTextTo = (EditText) findViewById(R.id.editTextTo);
        editTextTo.setRawInputType(Configuration.KEYBOARD_12KEY);

        editTextDistance = (EditText) findViewById(R.id.editTextDistance);
        editTextDistance.setRawInputType(Configuration.KEYBOARD_12KEY);

        editTextAzimuth = (EditText) findViewById(R.id.editTextAzimuth);
        editTextAzimuth.setRawInputType(Configuration.KEYBOARD_12KEY);

        editTextInclination = (EditText) findViewById(R.id.editTextInclination);
        editTextInclination.setRawInputType(Configuration.KEYBOARD_12KEY);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(buttonAddOnClickListener);

        idCave = getIntent().getIntExtra("idCave",-1);
        if(idCave == -1) {
            buttonAdd.setEnabled(false);
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private View.OnClickListener buttonAddOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DataDAO dataDAO = new DataDAO(getApplicationContext());

            String fromStr = editTextFrom.getText().toString();
            String toStr = editTextTo.getText().toString();
            String distanceStr = editTextDistance.getText().toString();
            String azimuthStr = editTextAzimuth.getText().toString();
            String inclinationStr = editTextInclination.getText().toString();

            Data data = new Data();
            CavesDAO cavesDAO = new CavesDAO(getApplicationContext());
            Caves caves = cavesDAO.get(idCave);

            //TODO: to one if
            if(fromStr != null) {
                data.setFrom(Integer.valueOf(fromStr));
            }
            if(toStr != null) {
                data.setTo(Integer.valueOf(toStr));
            }
            if(distanceStr != null) {
                data.setDistance(Double.valueOf(distanceStr));
            }
            if(azimuthStr != null) {
                data.setAzimuth(Double.valueOf(azimuthStr));
            }
            if(inclinationStr != null) {
                data.setInclination(Double.valueOf(inclinationStr));
            }

            data.setCaves(caves);

            dataDAO.save(data);

            Intent intent = new Intent(getApplicationContext(),DataActivity.class);
            intent.putExtra("Caves", data.getCaves());
            startActivity(intent);
        }
    };
}
