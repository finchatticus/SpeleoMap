package ua.kpi.speleo.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.CavesDAO;
import ua.kpi.speleo.app.db.Data;
import ua.kpi.speleo.app.db.DataDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cooper on 22.04.16.
 */
public class MapsActivity extends Activity {
    ListView listViewData;
    Button buttonNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        listViewData = (ListView) findViewById(R.id.listViewData);
        buttonNew = (Button) findViewById(R.id.buttonNew);
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewMapActivity.class);
                startActivity(intent);
            }
        });

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, cavesListStr);
        //listViewData.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
