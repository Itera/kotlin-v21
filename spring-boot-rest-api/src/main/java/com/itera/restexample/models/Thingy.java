package com.itera.restexample.models;

import java.util.Objects;

public class Thingy {
    private int id;
    private String name;

    public Thingy(int id, String name) {
        this.id = id;
        this.name = name;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Thingy thingy = (Thingy) o;
        return id == thingy.id && Objects.equals(name, thingy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
