package ua.kpi.speleo.app.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import ua.kpi.speleo.app.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataDAO extends DataDBDAO {
    public static final String DATA_ID_WITH_PREFIX = "data.id";
    public static final String CAVES_NAME_WITH_PREFIX = "caves.name";

    private static final String WHERE_ID_EQUALS = DataBaseHelper.ID_COLUMN + " =?";

    public DataDAO(Context context) {
        super(context);
    }

    public long save(Data data) {
        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.DATA_FROM, data.getFrom());
        values.put(DataBaseHelper.DATA_TO, data.getTo());
        values.put(DataBaseHelper.DATA_AZIMUTH, data.getAzimuth());
        values.put(DataBaseHelper.DATA_DISTANCE, data.getDistance());
        values.put(DataBaseHelper.DATA_INCLINATION, data.getInclination());
        values.put(DataBaseHelper.DATA_ROLL_ANGLE, data.getRollAngle());
        values.put(DataBaseHelper.DATA_ID_CAVE, data.getCaves().getId());
        long result = database.insert(DataBaseHelper.DATA_TABLE, null, values);
        Log.d("save","save " + result);
        return result;
    }

    public List<Data> getDataList() {
        List<Data> dataList = new ArrayList<Data>();
        String query = "SELECT "
                + DATA_ID_WITH_PREFIX + ","
                + DataBaseHelper.DATA_FROM + ","
                + DataBaseHelper.DATA_TO + ","
                + DataBaseHelper.DATA_DISTANCE + ","
                + DataBaseHelper.DATA_AZIMUTH + ","
                + DataBaseHelper.DATA_INCLINATION + ","
                + DataBaseHelper.DATA_ROLL_ANGLE + ","
                + DataBaseHelper.DATA_ID_CAVE + ","
                + CAVES_NAME_WITH_PREFIX
                + " FROM "
                + DataBaseHelper.DATA_TABLE + " data INNER JOIN "
                + DataBaseHelper.CAVES_TABLE + " caves ON data."
                + DataBaseHelper.DATA_ID_CAVE + " = caves."
                + DataBaseHelper.ID_COLUMN;

        Log.d("query", query);
        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Data data = new Data();
            data.setId(cursor.getInt(0));
            data.setFrom(cursor.getInt(1));
            data.setTo(cursor.getInt(2));
            data.setDistance(cursor.getDouble(3));
            data.setAzimuth(cursor.getDouble(4));
            data.setInclination(cursor.getDouble(5));
            data.setRollAngle(cursor.getDouble(6));

            Caves caves = new Caves(cursor.getInt(7),cursor.getString(8));
            data.setCaves(caves);
            Log.d("data_data",data.toString());
            Log.d("data_caves",caves.toString());

            dataList.add(data);
        }
        return dataList;
    }

    public ArrayList<HashMap> getDataListHashmap(Caves caves) {
        ArrayList<HashMap> dataList = new ArrayList<HashMap>();
        /*String query = "SELECT "
                + DATA_ID_WITH_PREFIX + ","
                + DataBaseHelper.DATA_FROM + ","
                + DataBaseHelper.DATA_TO + ","
                + DataBaseHelper.DATA_DISTANCE + ","
                + DataBaseHelper.DATA_AZIMUTH + ","
                + DataBaseHelper.DATA_INCLINATION + ","
                + DataBaseHelper.DATA_ROLL_ANGLE + ","
                + DataBaseHelper.DATA_ID_CAVE + ","
                + CAVES_NAME_WITH_PREFIX
                + " FROM "
                + DataBaseHelper.DATA_TABLE + " data INNER JOIN "
                + DataBaseHelper.CAVES_TABLE + " caves ON data."
                + DataBaseHelper.DATA_ID_CAVE + " = caves."
                + DataBaseHelper.ID_COLUMN;*/

        String query = "SELECT "
                + DataBaseHelper.ID_COLUMN + ","
                + DataBaseHelper.DATA_FROM + ","
                + DataBaseHelper.DATA_TO + ","
                + DataBaseHelper.DATA_DISTANCE + ","
                + DataBaseHelper.DATA_AZIMUTH + ","
                + DataBaseHelper.DATA_INCLINATION + ","
                + DataBaseHelper.DATA_ROLL_ANGLE + ","
                + DataBaseHelper.DATA_ID_CAVE
                + " FROM "
                + DataBaseHelper.DATA_TABLE
                + " WHERE " + DataBaseHelper.DATA_ID_CAVE + " = " + String.valueOf(caves.getId());

        Log.d("query", query);

        Cursor cursor = database.rawQuery(query, null);
        while (cursor.moveToNext()) {
            Data data = new Data();
            data.setId(cursor.getInt(0));
            data.setFrom(cursor.getInt(1));
            data.setTo(cursor.getInt(2));
            data.setDistance(cursor.getDouble(3));
            data.setAzimuth(cursor.getDouble(4));
            data.setInclination(cursor.getDouble(5));
            data.setRollAngle(cursor.getDouble(6));

            caves = new Caves(cursor.getInt(7));
            data.setCaves(caves);

            HashMap t = new HashMap();
            t.put(Constants.ID,data.getId());
            t.put(Constants.FROM, data.getFrom());
            t.put(Constants.TO, data.getTo());
            t.put(Constants.DISTANCE, data.getDistance());
            t.put(Constants.AZIMUTH, data.getAzimuth());
            t.put(Constants.INCLINATION, data.getInclination());
            t.put(Constants.ID_CAVE, data.getCaves().getId());
            dataList.add(t);
        }
        return dataList;
    }

    public Data get(int id) {
        String query = "SELECT "
                + DATA_ID_WITH_PREFIX + ","
                + DataBaseHelper.DATA_FROM + ","
                + DataBaseHelper.DATA_TO + ","
                + DataBaseHelper.DATA_DISTANCE + ","
                + DataBaseHelper.DATA_AZIMUTH + ","
                + DataBaseHelper.DATA_INCLINATION + ","
                + DataBaseHelper.DATA_ROLL_ANGLE + ","
                + DataBaseHelper.DATA_ID_CAVE + ","
                + CAVES_NAME_WITH_PREFIX
                + " FROM "
                + DataBaseHelper.DATA_TABLE + " data INNER JOIN "
                + DataBaseHelper.CAVES_TABLE + " caves ON data."
                + DataBaseHelper.DATA_ID_CAVE + " = caves."
                + DataBaseHelper.ID_COLUMN + " WHERE "
                + DATA_ID_WITH_PREFIX + " = " + String.valueOf(id);

        Log.d("query", query);
        Cursor cursor = database.rawQuery(query, null);
        Data data = new Data();

        while (cursor.moveToNext()) {
            data.setId(cursor.getInt(0));
            data.setFrom(cursor.getInt(1));
            data.setTo(cursor.getInt(2));
            data.setDistance(cursor.getDouble(3));
            data.setAzimuth(cursor.getDouble(4));
            data.setInclination(cursor.getDouble(5));
            data.setRollAngle(cursor.getDouble(6));

            Caves caves = new Caves(cursor.getInt(7), cursor.getString(8));
            data.setCaves(caves);
            Log.d("data_data", data.toString());
            Log.d("data_caves", caves.toString());
        }

            return data;
    }

    public long update(Data data) {

        ContentValues values = new ContentValues();
        values.put(DataBaseHelper.DATA_FROM, data.getFrom());
        values.put(DataBaseHelper.DATA_TO, data.getTo());
        values.put(DataBaseHelper.DATA_AZIMUTH, data.getAzimuth());
        values.put(DataBaseHelper.DATA_DISTANCE, data.getDistance());
        values.put(DataBaseHelper.DATA_INCLINATION, data.getInclination());
        values.put(DataBaseHelper.DATA_ROLL_ANGLE, data.getRollAngle());
        values.put(DataBaseHelper.DATA_ID_CAVE, data.getCaves().getId());

        long result = database.update(DataBaseHelper.DATA_TABLE, values, WHERE_ID_EQUALS,
                new String[] { String.valueOf(data.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }



    public int delete(Data data) {
        return database.delete(DataBaseHelper.DATA_TABLE, WHERE_ID_EQUALS,
                new String[] { data.getId() + "" });
    }

    public void deleteAll() {
        database.execSQL("DELETE FROM " + DataBaseHelper.DATA_TABLE + " ;");
    }
}
