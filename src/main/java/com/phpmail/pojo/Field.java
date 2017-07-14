package com.phpmail.pojo;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import org.jetbrains.annotations.NotNull;


public class Field implements Comparable<Field> {

    private SimpleStringProperty displayName;
    private SimpleStringProperty formFieldName;
    private SimpleBooleanProperty isOptional;

    public Field() {
        displayName = new SimpleStringProperty();
        formFieldName = new SimpleStringProperty();
        isOptional = new SimpleBooleanProperty();
    }

    public String getDisplayName() {
        return displayName.get();
    }

    public void setDisplayName(String displayName) {
        this.displayName.set(displayName);
    }

    public SimpleStringProperty displayNameProperty() {
        return displayName;
    }

    public String getFormFieldName() {
        return formFieldName.get();
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName.set(formFieldName);
    }

    public SimpleStringProperty formFieldNameProperty() {
        return formFieldName;
    }

    public boolean isIsOptional() {
        return isOptional.get();
    }

    public void setIsOptional(boolean isOptional) {
        this.isOptional.set(isOptional);
    }

    public SimpleBooleanProperty isOptionalProperty() {
        return isOptional;
    }

    public String toPhpVariable() {
        return "$" + getFormFieldName() + " = $_REQUEST['" + getFormFieldName() + "'];";
    }

    @Override
    public String toString() {
        return "Field{" +
                "displayName=" + displayName +
                ", formFieldName=" + formFieldName +
                ", isOptional=" + isOptional +
                '}';
    }

    @Override
    public int compareTo(@NotNull Field o) {
        return this.formFieldName.getValue().compareTo(o.formFieldName.getValue());
    }

}
