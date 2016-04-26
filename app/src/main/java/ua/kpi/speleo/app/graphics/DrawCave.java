package ua.kpi.speleo.app.graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import ua.kpi.speleo.app.Constants;
import ua.kpi.speleo.app.db.Caves;
import ua.kpi.speleo.app.db.Data;
import ua.kpi.speleo.app.db.DataDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class DrawCave {
    private int width;
    private int height;
    private Paint paint;
    private Canvas canvas;
    private Caves caves;
    private Context context;

    public DrawCave(int width, int height, Canvas canvas, Caves caves,Context context) {
        this.height = height;
        this.width = width;
        this.canvas = canvas;
        this.caves = caves;
        this.context = context;

        DataDAO dataDAO = new DataDAO(context);

        ArrayList<HashMap> list = new ArrayList<HashMap>();
        list = dataDAO.getDataListHashmap(caves);

        ArrayList<Model> listModel= new ArrayList<Model>();
        for (int i = 0; i < list.size(); i++) {
            int from = (Integer) list.get(i).get(Constants.FROM);
            int to = (Integer) list.get(i).get(Constants.TO);
            double distance = (Double) list.get(i).get(Constants.DISTANCE);
            double azimuth = (Double) list.get(i).get(Constants.AZIMUTH);
            double inclination = (Double) list.get(i).get(Constants.INCLINATION);
            listModel.add(new Model(from,to,distance,azimuth,inclination,0, 0));
        }




        paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        paint.setColor(Color.RED);
        for (int i = 0; i < listModel.size(); i++) {
            canvas.drawLine(listModel.get(i).getX1(), listModel.get(i).getY1(), listModel.get(i).getX2(), listModel.get(i).getY2(), paint);
            canvas.drawText(String.valueOf(listModel.get(i).getTo()),listModel.get(i).getX2(),listModel.get(i).getY2() + 14,paint);
        }
        canvas.drawText(String.valueOf(listModel.get(1).getFrom()),listModel.get(1).getX1(),listModel.get(1).getY1() + 14, paint);

        //canvas.drawLine(0.0f,0.0f,50,50,paint);
        //canvas.drawPoint();
    }

    public Canvas getCanvas() {
        return canvas;
    }
}
