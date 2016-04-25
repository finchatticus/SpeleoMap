package ua.kpi.speleo.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ua.kpi.speleo.R;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.CavesDAO;

public class NewMapActivity extends Activity {
    private EditText editTextName;
    private Button buttonNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_map);

        editTextName = (EditText) findViewById(R.id.editTextName);
        buttonNew = (Button) findViewById(R.id.buttonNew);
        buttonNew.setOnClickListener(buttonNewClickListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private View.OnClickListener buttonNewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name = editTextName.getText().toString();
            if(!name.matches("")) {
                Caves caves = new Caves(name);
                CavesDAO cavesDAO = new CavesDAO(getApplicationContext());
                long id = cavesDAO.save(caves);
                caves.setId((int) id);
                
                Intent intent = new Intent(getApplicationContext(), DataActivity.class);
                intent.putExtra("Caves", caves);
                startActivity(intent);
            }
        }
    };
}
