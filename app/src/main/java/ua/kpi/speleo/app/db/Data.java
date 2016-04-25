package ua.kpi.speleo.app.db;

import android.os.Parcel;
import android.os.Parcelable;

public class Data implements Parcelable {
    private int id;
    private int from;
    private int to;
    private double distance;
    private double azimuth;
    private double inclination;
    private double rollAngle;
    private Caves caves;

    public Data() {
        super();
    }

    public Data(int from, int to, double distance, double azimuth, double inclination, double rollAngle, Caves caves) {
        super();
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.rollAngle = rollAngle;
        this.inclination = inclination;
        this.azimuth = azimuth;
        this.caves = caves;
    }

    public Data(int id, int from, int to, double azimuth, double distance, double inclination, double rollAngle, Caves caves) {
        super();
        this.id = id;
        this.from = from;
        this.to = to;
        this.azimuth = azimuth;
        this.distance = distance;
        this.inclination = inclination;
        this.rollAngle = rollAngle;
        this.caves = caves;
    }

    private Data(Parcel parcel) {
        super();
        this.id = parcel.readInt();
        this.from = parcel.readInt();
        this.to = parcel.readInt();
        this.distance = parcel.readDouble();
        this.azimuth = parcel.readDouble();
        this.inclination = parcel.readDouble();
        this.rollAngle = parcel.readDouble();
        this.caves = parcel.readParcelable(Caves.class.getClassLoader());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getRollAngle() {
        return rollAngle;
    }

    public void setRollAngle(double rollAngle) {
        this.rollAngle = rollAngle;
    }

    public Caves getCaves() {
        return caves;
    }

    public void setCaves(Caves caves) {
        this.caves = caves;
    }

    @Override
    public String toString() {
        return "Data[id=" + id
                + ", from=" + from
                + ", to=" + to
                + ", azimuth=" + azimuth
                + ", distance=" + distance
                + ", inclination=" + inclination
                + ", rollAngle=" + rollAngle;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Data other = (Data) o;
        if (id != other.id)
            return false;
        return true;

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeInt(getFrom());
        parcel.writeInt(getTo());
        parcel.writeDouble(getDistance());
        parcel.writeDouble(getAzimuth());
        parcel.writeDouble(getInclination());
        parcel.writeDouble(getRollAngle());
        parcel.writeParcelable(getCaves(), i);
    }

    public static final Parcelable.Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel parcel) {
            return new Data(parcel);
        }

        @Override
        public Data[] newArray(int i) {
            return new Data[i];
        }
    };
}
