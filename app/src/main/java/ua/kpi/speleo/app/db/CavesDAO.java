package ua.kpi.speleo.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CavesDAO extends DataDBDAO {
    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_COLUMN
            + " =?";

    public CavesDAO(Context context) {
        super(context);
    }

    public long save(Caves caves) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.CAVES_NAME, caves.getName());
        long result = database.insert(DataBaseHelper.CAVES_TABLE, null, values);
        Log.d("cavesDAO","save " + result);
        return result;
    }

    public long update(Caves caves) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.CAVES_NAME, caves.getName());

        long result = database.update(DataBaseHelper.CAVES_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(caves.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int delete(Caves caves) {
        int result = database.delete(DataBaseHelper.CAVES_TABLE,
                WHERE_ID_EQUALS, new String[] { caves.getId() + "" });
        Log.d("Delete Result:", "=" + result);

        return result;
    }

    public List<Caves> getCaves() {
        List<Caves> caves = new ArrayList<Caves>();
        Cursor cursor = database.query(DataBaseHelper.CAVES_TABLE,
                new String[] { DataBaseHelper.ID_COLUMN,
                        DataBaseHelper.CAVES_NAME }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            Caves department = new Caves();
            department.setId(cursor.getInt(0));
            department.setName(cursor.getString(1));
            caves.add(department);
        }
        return caves;
    }

    public Caves get(int id) {
        String query = "SELECT "
                + DataBaseHelper.ID_COLUMN + ","
                + DataBaseHelper.CAVES_NAME
                + " FROM "
                + DataBaseHelper.CAVES_TABLE + " WHERE "
                + DataBaseHelper.ID_COLUMN + " = " + String.valueOf(id);

        Log.d("query", query);
        Cursor cursor = database.rawQuery(query, null);
        Caves caves = new Caves();

        while (cursor.moveToNext()) {
            caves = new Caves(cursor.getInt(0), cursor.getString(1));
        }

        return caves;
    }

}
