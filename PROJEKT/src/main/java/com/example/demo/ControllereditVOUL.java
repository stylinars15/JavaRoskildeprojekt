package com.example.demo;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.fxml.Initializable;
import javafx.scene.paint.Color;
import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class ControllereditVOUL extends HelloController implements Initializable{

    public TableView <Vagter> Tableviewuse;
    public TableColumn <Vagter,String> Datebox;
    public TableColumn <Vagter,String> StandBox;
    public TableColumn <Vagter,String>ShiftBox;
    public Label shiftsof;
    public TextField emailsevagter;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        Datebox.setCellValueFactory(new PropertyValueFactory<Vagter,String>("datebox"));
        Datebox.setCellFactory(TextFieldTableCell.forTableColumn());
        Datebox.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vagter, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent <Vagter, String> event) {
                Vagter vagter = event.getRowValue();
                vagter.setDatebox(event.getNewValue());
            }
        });


        StandBox.setCellValueFactory(new PropertyValueFactory<Vagter, String>("standbox"));
        StandBox.setCellFactory(TextFieldTableCell.forTableColumn());
        StandBox.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vagter, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent <Vagter, String> event) {
                Vagter vagter = event.getRowValue();
                vagter.setStandbox(event.getNewValue());
            }
        });

        ShiftBox.setCellValueFactory(new PropertyValueFactory<Vagter,String>("shiftbox"));
        ShiftBox.setCellFactory(TextFieldTableCell.forTableColumn());
        ShiftBox.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Vagter, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent <Vagter, String> event) {
                Vagter vagter = event.getRowValue();
                vagter.setShiftbox(event.getNewValue());
            }
        });
    }

    public void showshifts(MouseEvent mouseEvent) throws IOException {

        searchnamefunc();

        String temp = editvollunteerlabel.getText();

        if (Objects.equals(temp, "Volunteer found"))
        {
            showvagtertabel();
            Tableviewuse.setEditable(true);
        }

    }

    public void savevagt(MouseEvent mouseEvent) throws IOException {

        File file = new File("voluntterdata.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        PrintWriter pw = new PrintWriter(bw);

        int rowcount  =   Tableviewuse.getItems().size();

        for (int i = 0; i <= 3; i++) {
            bw.write(split[i] + ",");
        }

       for (int i = 0; i < rowcount; i++ ){
           Vagter vagter = Tableviewuse.getSelectionModel().getTableView().getItems().get(i);
           if (rowcount-1!=0){
               bw.write(vagter.getDatebox()+","+ vagter.getStandbox()+","+ vagter.getShiftbox()+",");
           }
           else {
               bw.write(vagter.getDatebox()+","+ vagter.getStandbox()+","+ vagter.getShiftbox());
           }
       }
        bw.close();
        deletefunc();
        Tableviewuse.getSortOrder().add(Datebox);
    }

    public void showvagtertabel() {

        Tableviewuse.getItems().clear();

            for (int i =4;i<split.length;i = i+3 ) {
                Vagter item = new Vagter(split[i], split[i+1], split[i+2]);
                Tableviewuse.getItems().add(item);
            }
        Tableviewuse.getSortOrder().add(Datebox);
    }

    public void ShowShifts(MouseEvent mouseEvent) throws IOException {

        String emailText = emailsevagter.getText();

        File file = new File("voluntterdata.txt");
        BufferedReader rd = new BufferedReader(new FileReader(file));

        String s;
        while ((s = rd.readLine()) != null) {
            split = s.split(",");
            if (Objects.equals(split[3], emailText)) {

                shiftsof.setText("Showing shifts for : " + split[0]);
                shiftsof.setTextFill(Color.web("#008000"));
                Tableviewuse.setDisable(false);
                showvagtertabel();
                break;

            }
            else {
                shiftsof.setTextFill(Color.web("#ff0000"));
                shiftsof.setText("Enter valid email");
                Tableviewuse.getItems().clear();
                Tableviewuse.setDisable(true);
            }
        }
        rd.close();
    }

    public void deletevagt(MouseEvent mouseEvent) {
        Tableviewuse.getItems().removeAll(Tableviewuse.getSelectionModel().getSelectedItem());
    }
}
