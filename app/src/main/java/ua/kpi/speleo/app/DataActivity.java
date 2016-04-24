package ua.kpi.speleo.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import ua.kpi.speleo.R;

import java.util.ArrayList;
import java.util.HashMap;

public class DataActivity extends Activity {
    private Button buttonSkeleton;
    private Button buttonSketch;
    private ListView listViewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        buttonSkeleton = (Button) findViewById(R.id.buttonSkeleton);
        buttonSketch = (Button) findViewById(R.id.buttonSketch);
        listViewData = (ListView) findViewById(R.id.listViewData);

        ArrayList<HashMap> list = new ArrayList<HashMap>();

        HashMap temp = new HashMap();
        temp.put(Constants.COLUMN1, "1");
        temp.put(Constants.COLUMN2, "1");
        temp.put(Constants.COLUMN3, "1");
        temp.put(Constants.COLUMN4, "1");
        temp.put(Constants.COLUMN5, "1");
        list.add(temp);

        HashMap temp1 = new HashMap();
        temp1.put(Constants.COLUMN1, "1");
        temp1.put(Constants.COLUMN2, "1");
        temp1.put(Constants.COLUMN3, "1");
        temp1.put(Constants.COLUMN4, "1");
        temp1.put(Constants.COLUMN5, "1");
        list.add(temp1);

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
    }
}
