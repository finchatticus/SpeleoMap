package ua.kpi.speleo.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.Data;
import ua.kpi.speleo.app.db.DataDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class DataActivity extends Activity {
    private static final String LOG_TAG = DataActivity.class.getSimpleName();

    private Button buttonSkeleton;
    private Button buttonSketch;
    private Button buttonSave;
    private ListView listViewData;

    private Caves caves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        Log.d(LOG_TAG, "getParcelableExtra");
        caves = (Caves) getIntent().getParcelableExtra("Caves");
        Log.d(LOG_TAG, "caves: " + caves.getId() + ", " + caves.getName());

        buttonSkeleton = (Button) findViewById(R.id.buttonSkeleton);
        buttonSketch = (Button) findViewById(R.id.buttonSketch);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        listViewData = (ListView) findViewById(R.id.listViewData);

        ArrayList<HashMap> list = new ArrayList<HashMap>();
        DataDAO dataDAO = new DataDAO(this);
        dataDAO.deleteAll();
        dataDAO.save(new Data(1,2,1.0,2.0,3.0,4.0,caves));
        dataDAO.save(new Data(1,2,1.0,2.0,3.0,4.0,caves));
        list = dataDAO.getDataListHashmap(caves);

        Log.v("lists","1231");
        for (int i = 0; i < list.size(); i++) {
            Log.v(this.getClass().getSimpleName().toString(),"list " + list.get(i).get((String) Constants.COLUMN1));
        }

        ListviewAdapter adapter = new ListviewAdapter(this, list);
        listViewData.setAdapter(adapter);

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

}
