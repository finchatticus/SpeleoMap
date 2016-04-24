package ua.kpi.speleo.app.db;

import android.os.Parcel;
import android.os.Parcelable;

public class Caves implements Parcelable {
    private int id;
    private String name;

    public Caves() {
        super();
    }

    public Caves(String name) {
        this.name = name;
    }

    public Caves(int id) {
        super();
        this.id = id;
    }

    public Caves(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    private Caves(Parcel in) {
        super();
        this.id = in.readInt();
        this.name = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Caves[id=" + id + ", name=" + name + "];";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(getId());
        parcel.writeString(getName());
    }

    public static final Parcelable.Creator<Caves> CREATOR = new Creator<Caves>() {
        @Override
        public Caves createFromParcel(Parcel parcel) {
            return new Caves(parcel);
        }

        @Override
        public Caves[] newArray(int i) {
            return new Caves[i];
        }
    };

    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(o == null)
            return false;
        if(getClass() != o.getClass())
            return false;
        Caves other = (Caves) o;
        if(id != other.id)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
}
