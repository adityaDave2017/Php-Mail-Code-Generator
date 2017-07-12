package com.phpmail;


import org.jetbrains.annotations.NotNull;

public class Field implements Comparable<Field> {

    private String formFieldName;
    private String displayName;
    private boolean isOptional;

    public String getFormFieldName() {
        return formFieldName;
    }

    public void setFormFieldName(String formFieldName) {
        this.formFieldName = formFieldName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public String toPhpVariable() {
        return "$" + formFieldName + " = $_REQUEST['" + formFieldName + "'];";
    }

    @Override
    public String toString() {
        return toPhpVariable();
        /*return "Field{" +
                "formFieldName='" + formFieldName + '\'' +
                ", displayName='" + displayName + '\'' +
                ", isOptional=" + isOptional +
                '}';*/
    }

    @Override
    public int compareTo(@NotNull Field o) {
        return this.formFieldName.compareTo(o.formFieldName);
    }
}
