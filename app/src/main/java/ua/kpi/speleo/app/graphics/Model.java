package ua.kpi.speleo.app.graphics;

public class Model {
    private int from;
    private int to;
    private float x1;
    private float y1;
    private float x2;
    private float y2;
    private double distance;
    private double azimuth;
    private double inclination;
    private double d;
    private double deltaX;
    private double deltaY;

    public Model() {

    }

    public Model(int from, int to, double distance, double azimuth, double inclination, float x1, float y1) {
        this.from = from;
        this.to = to;
        this.x1 = 20 * x1 + 800 / 2;
        this.y1 = 1044 / 2 - 20 * y1;
        this.distance = distance;
        this.azimuth = azimuth;
        this.inclination = inclination;

        d = Math.cos((inclination * Math.PI) / 180) * distance;
        deltaX = Math.cos((azimuth * Math.PI) / 180) * d;
        deltaY = Math.sin((azimuth * Math.PI) / 180) * d;
        x2 = 20 * ((float)(x1 + deltaX)) + 800 / 2;
        y2 = 1044 / 2 - ((float)(y1 + deltaY)) * 20;
        System.out.println("x1 " + x1);
        System.out.println("y1 " + y1);
        System.out.println("x2 " + x2);
        System.out.println("y2 " + y2);
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    public float getX1() {
        return x1;
    }

    public void setX1(float x1) {
        this.x1 = x1;
    }

    public float getY1() {
        return y1;
    }

    public void setY1(float y1) {
        this.y1 = y1;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public double getInclination() {
        return inclination;
    }

    public void setInclination(double inclination) {
        this.inclination = inclination;
    }

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(double deltaX) {
        this.deltaX = deltaX;
    }

    public double getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(double deltaY) {
        this.deltaY = deltaY;
    }
}
