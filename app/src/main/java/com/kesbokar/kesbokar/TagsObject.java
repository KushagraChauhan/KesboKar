package com.kesbokar.kesbokar;

import androidx.annotation.NonNull;

public class TagsObject {
    private String id, value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @NonNull
    @Override
    public String toString() {
        return this.value;
    }
}
