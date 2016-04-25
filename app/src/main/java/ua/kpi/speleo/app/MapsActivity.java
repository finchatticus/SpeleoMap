package ua.kpi.speleo.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.CavesDAO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cooper on 22.04.16.
 */
public class MapsActivity extends Activity {
    private static final String LOG_TAG = MapsActivity.class.getSimpleName();

    private ListView listViewData;
    private Button buttonNew;

    List<Caves> cavesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maps);

        listViewData = (ListView) findViewById(R.id.listViewMaps);
        buttonNew = (Button) findViewById(R.id.buttonNew);
        buttonNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewMapActivity.class);
                startActivity(intent);
            }
        });

        CavesDAO cavesDAO = new CavesDAO(this);
        ArrayList<String> cavesListStr = new ArrayList<String>();
        cavesList = new ArrayList<Caves>();
        cavesList = cavesDAO.getCaves();
        for (int i = 0; i < cavesDAO.getCaves().size(); i++) {
            cavesListStr.add(cavesDAO.getCaves().get(i).getName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	android.R.layout.simple_list_item_1, cavesListStr);
        listViewData.setAdapter(adapter);
        listViewData.setOnItemClickListener(listViewDataOnItemClick);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private AdapterView.OnItemClickListener listViewDataOnItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Log.v(LOG_TAG,"pos " + i + " id " + l + " cave " + cavesList.get(i).getName() + " cave id " + cavesList.get(i).getId());
            Intent intent = new Intent(getApplicationContext(), DataActivity.class);
            intent.putExtra("Caves", cavesList.get(i));
            startActivity(intent);
        }
    };
}
