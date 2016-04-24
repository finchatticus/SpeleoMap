package ua.kpi.speleo.app.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataDBDAO {

    protected SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private Context context;

    public DataDBDAO(Context context) {
        this.context = context;
        dbHelper = DataBaseHelper.getInstance(this.context);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DataBaseHelper.getInstance(context);
        database = dbHelper.getWritableDatabase();
    }
}
