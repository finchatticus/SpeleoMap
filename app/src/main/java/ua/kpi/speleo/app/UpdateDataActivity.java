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
import ua.kpi.speleo.app.db.Data;
import ua.kpi.speleo.app.db.DataDAO;

public class UpdateDataActivity extends Activity {
    private EditText editTextFrom;
    private EditText editTextTo;
    private EditText editTextDistance;
    private EditText editTextAzimuth;
    private EditText editTextInclination;
    private Button buttonUpdate;
    private Button buttonDelete;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_data);

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

        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(buttonUpdateOnClickListener);

        buttonDelete = (Button) findViewById(R.id.buttonDelete);
        buttonDelete.setOnClickListener(buttonDeleteOnClickListener);

        id = getIntent().getIntExtra("id",-1);
        Data data = (Data) getIntent().getParcelableExtra("Data");
        if(id == -1 || data == null) {
            buttonUpdate.setEnabled(false);
            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
        }

        editTextFrom.setText(String.valueOf(data.getFrom()));
        editTextTo.setText(String.valueOf(data.getTo()));
        editTextDistance.setText(String.valueOf(data.getDistance()));
        editTextAzimuth.setText(String.valueOf(data.getAzimuth()));
        editTextInclination.setText(String.valueOf(data.getInclination()));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private View.OnClickListener buttonUpdateOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DataDAO dataDAO = new DataDAO(getApplicationContext());

            String fromStr = editTextFrom.getText().toString();
            String toStr = editTextTo.getText().toString();
            String distanceStr = editTextDistance.getText().toString();
            String azimuthStr = editTextAzimuth.getText().toString();
            String inclinationStr = editTextInclination.getText().toString();

            Data data = dataDAO.get(id);
            System.out.println("updateonclick " + data.toString());

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

            dataDAO.update(data);

            Intent intent = new Intent(getApplicationContext(),DataActivity.class);
            intent.putExtra("Caves", data.getCaves());
            startActivity(intent);
        }
    };

    private View.OnClickListener buttonDeleteOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            DataDAO dataDAO = new DataDAO(getApplicationContext());
            Data data = dataDAO.get(id);
            dataDAO.delete(data);

            Intent intent = new Intent(getApplicationContext(),DataActivity.class);
            intent.putExtra("Caves", data.getCaves());
            startActivity(intent);
        }
    };
}
