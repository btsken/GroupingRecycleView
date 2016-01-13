package com.btsken.groupingrecycleview;

import java.util.Comparator;

/**
 * Created by ken on 2016/1/13.
 */
public class Data {
    int id;
    String name;

    public Data(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class NameComparator implements Comparator<Data> {

    @Override
    public int compare(Data o, Data o1) {
        return o.getName().compareTo(o1.getName());
    }
}

class IdComparator implements Comparator<Data> {

    @Override
    public int compare(Data o, Data o1) {
        return o.getId() > o1.getId() ? 1 : -1;
    }
}
