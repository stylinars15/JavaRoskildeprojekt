package com.example.demo;
import javafx.beans.property.SimpleStringProperty;

public class Vager {
    private SimpleStringProperty datebox;
    private SimpleStringProperty standbox;
    private SimpleStringProperty shiftbox;


    public Vager(String datebox, String standBox, String shiftBox) {
        this.datebox = new SimpleStringProperty(datebox);
        this.standbox = new SimpleStringProperty(standBox);
        this.shiftbox = new SimpleStringProperty(shiftBox);
    }

    public Vager(){

    }

    public String getDatebox(){
        return datebox.get();
    }

    public String getStandbox(){
        return standbox.get();
    }

    public String getShiftbox(){
        return shiftbox.get();
    }

    public void setDatebox(String newValue) {
        datebox.set(newValue);
    }

    public void setStandbox(String newValue) {
        standbox.set(newValue);
    }

    public void setShiftbox(String newValue) {
         shiftbox.set(newValue);
    }
}
