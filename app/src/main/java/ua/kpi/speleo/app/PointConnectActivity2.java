package ua.kpi.speleo.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.DataDAO;
import ua.kpi.speleo.app.graphics.ConnectModel;
import ua.kpi.speleo.app.graphics.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class PointConnectActivity2 extends Activity {
    private ListView listViewData;
    private Button buttonConnect;
    private Button buttonDisconnect;
    private Button buttonBack;

    private ArrayList<HashMap> list;

    private Caves caves;

    private ArrayList<Model> listModel;

    public static ArrayList<ConnectModel> listConnected = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point_connect2);

        caves = (Caves) getIntent().getParcelableExtra("Caves");

        listViewData = (ListView) findViewById(R.id.listViewData);
        buttonConnect = (Button) findViewById(R.id.buttonConnect);
        buttonDisconnect = (Button) findViewById(R.id.buttonDiconnect);
        buttonBack = (Button) findViewById(R.id.buttonBack);

        list = new ArrayList<HashMap>();
        DataDAO dataDAO = new DataDAO(getApplicationContext());
        list = dataDAO.getDataListHashmap(caves);
        ListviewAdapter adapter = new ListviewAdapter(this, list);
        listViewData.setAdapter(adapter);
        listViewData.setOnItemClickListener(listViewDataOnItemClick);

        listModel= new ArrayList<Model>();
        for (int i = 0; i < list.size(); i++) {
            int from = (Integer) list.get(i).get(Constants.FROM);
            int to = (Integer) list.get(i).get(Constants.TO);
            double distance = (Double) list.get(i).get(Constants.DISTANCE);
            double azimuth = (Double) list.get(i).get(Constants.AZIMUTH);
            double inclination = (Double) list.get(i).get(Constants.INCLINATION);
            listModel.add(new Model(from,to,distance,azimuth,inclination,0, 0));
        }

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                Model model1 = listModel.get(i);
                Model model2 = listModel.get(j);
                if(model1.getFrom() == model2.getTo()) {
                    float x2 = model2.getX2();
                    float y2 = model2.getY2();
                    model1.setX1(x2);
                    model1.setY1(y2);

                    float x2Real = model2.getX2Real();
                    float y2Real = model2.getY2Real();
                    model1.setX1Real(x2Real);
                    model1.setY2Real(y2Real);

                    listModel.set(i,model1);
                    System.out.println("listmodel");

                    System.out.println("x1 " + model1.getX1());
                    System.out.println("y1 " + model1.getY1());

                    System.out.println("x1Real " + model1.getX1Real());
                    System.out.println("y1Real " + model1.getY1Real());
                }
            }
        }

        listConnected = new ArrayList<ConnectModel>();

        buttonDisconnect.setOnClickListener(buttonDisconnectOnClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private AdapterView.OnItemClickListener listViewDataOnItemClick = new AdapterView.OnItemClickListener() {
        private int count = 0;
        private Model model1;
        private Model model2;
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            count++;
            System.out.println("onItemClick");

            if(count == 1) {
                model1 = listModel.get(i);
            }
            if(count == 2) {
                model2 = listModel.get(i);
                listConnected.add(new ConnectModel(model1, model2));
                count = 0;
            }
            System.out.println(count);
            if(model1 != null) {
                System.out.println(model1.getDistance());
                //System.out.println("x1 " + model1.getX1());
                //System.out.println("y1 " + model1.getY1());
                //System.out.println("x2 " + model1.getX2());
                //System.out.println("y2 " + model1.getY2());
            }
            if(model2 != null) {
                System.out.println(model2.getDistance());
                //System.out.println("x1 " + model2.getX1());
                //System.out.println("y1 " + model2.getY1());
                //System.out.println("x2 " + model2.getX2());
                //System.out.println("y2 " + model2.getY2());
            }
        }
    };

    private View.OnClickListener buttonDisconnectOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            listConnected.clear();
        }
    };
}
