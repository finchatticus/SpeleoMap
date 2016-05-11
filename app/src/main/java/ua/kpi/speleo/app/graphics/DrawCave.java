package ua.kpi.speleo.app.graphics;

import android.content.Context;
import android.content.SharedPreferences;
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
    private SharedPreferences sharedPreferences;

    public DrawCave(int width, int height, Canvas canvas, Caves caves,Context context) {
        this.height = height;
        this.width = width;
        this.canvas = canvas;
        this.caves = caves;
        this.context = context;

        sharedPreferences = context.getSharedPreferences(caves.getId() + "_points_coordinates", Context.MODE_PRIVATE);

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

        for (int i = 0; i < list.size(); i++) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putFloat("x_coord", listModel.get(i).getX1());
            editor.putFloat("y_coord", listModel.get(i).getY1());
            editor.commit();
        }

        paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
        for (int i = 0; i < listModel.size(); i++) {
            paint.setColor(Color.RED);
            canvas.drawLine(listModel.get(i).getX1(), listModel.get(i).getY1(), listModel.get(i).getX2(), listModel.get(i).getY2(), paint);
            paint.setColor(Color.GREEN);
            canvas.drawCircle(listModel.get(i).getX1(), listModel.get(i).getY1(), 1, paint);
            canvas.drawCircle(listModel.get(i).getX2(), listModel.get(i).getY2(), 1, paint);
            paint.setColor(Color.BLUE);
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
