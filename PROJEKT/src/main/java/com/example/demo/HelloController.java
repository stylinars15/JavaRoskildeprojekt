package com.example.demo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.io.*;
import java.util.Arrays;
import java.util.Objects;



public class HelloController
{
    public PasswordField password;
    public Label myLabel,Createavolunteer,oldname,oldadress,oldemail,oldnationality,editvollunteerlabel;
    public TextField email,Address,Nationality,Email,Name,Searchname;
    public String Name1;
    public String[] split;

    @FXML
    private HelloApplication app;

    @FXML

    public void setapp(HelloApplication helloApplication) {
        this.app=helloApplication;
    }
    
    public void Respbuttonclick(MouseEvent mouseEvent) throws IOException {
        app.responsiblebuttonclick();
    }

    public void Create() throws IOException {
        app.create();
    }

    public void createvolunteer(ActionEvent actionEvent) throws IOException {
        app.create();
    }

    public void editvolunteer(ActionEvent actionEvent) throws IOException {
        app.edit();
    }

    public void createshift(ActionEvent actionEvent) throws IOException {
        app.createshift();
    }

    public void editshift(ActionEvent actionEvent) throws IOException {
        app.editshift();
    }

    public void Logout(MouseEvent mouseEvent) throws IOException {
        app.logout();
    }

    public void Voluntterbuttonclick(MouseEvent mouseEvent) throws IOException {
        app.volunteerseshifts();
    }

    public void Loginclick(MouseEvent mouseEvent) throws IOException
    {
        String emailText = email.getText();
        String passwordText = password.getText();


            File file = new File("test.txt");
            BufferedReader rd = new BufferedReader(new FileReader(file));

        String s;
            while ((s = rd.readLine())!=null){
                if (s.equals(emailText))
                {
                    s = rd.readLine();
                    if (s.equals(passwordText))
                    {
                        Create();
                    }
                    else {
                        myLabel.setTextFill(Color.web("#ff0000"));
                        myLabel.setText("Wrong email / password");
                    }
                }
                else {
                    myLabel.setTextFill(Color.web("#ff0000"));
                    myLabel.setText("Wrong email / password");
                }
            }
            rd.close();
    }

    public void Done(MouseEvent mouseEvent) throws IOException {

        String Name11 = Name.getText();
        String Address1 = Address.getText();
        String Nationality1 = Nationality.getText();
        String Email1 = Email.getText();

        String[] names = {Name11, Address1, Nationality1, Email1};
        File file = new File("voluntterdata.txt");

        BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
        PrintWriter pw = new PrintWriter(bw);

        if (Name1!=null) {

            if (!Objects.equals(Name11, "")) {
                bw.write(Name11 + ",");
            } else {
                bw.write(split[0] + ",");
            }

            if (!Objects.equals(Address1, "")) {
                bw.write(Address1 + ",");
            } else {
                bw.write(split[1] + ",");
            }

            if (!Objects.equals(Nationality1, "")) {
                bw.write(Nationality1 + ",");
            } else {
                bw.write(split[2] + ",");
            }

            if (split.length>4)
            {
                int i = 4;
                if (!Objects.equals(Email1, "")) {
                    bw.write(Email1 + ",");
                } else {
                    bw.write(split[3] + ",");
                }

                while (i!=(split.length-1))
                {
                    bw.write(split[i] + ",");
                    i++;
                }
                bw.write(split[i] + "\n");
            }
            else{
                if (!Objects.equals(Email1, "")) {
                    bw.write(Email1 + "\n");
                } else {
                    bw.write(split[3] + "\n");
                }
            }

            bw.close();
            deletefunc();

            Createavolunteer.setTextFill(Color.web("#008000"));
            Createavolunteer.setText("Volunteer saved");

            Name.clear();
            Address.clear();
            Nationality.clear();
            Email.clear();
        }

        else {
            if ( Objects.equals(names[0], "") || Objects.equals(names[1], "")|| Objects.equals(names[2], "") || Objects.equals(names[3], "")){

                Createavolunteer.setTextFill(Color.web("#FF0000"));
                Createavolunteer.setText("All information required");

            }
            else {
                createfunc(3,names, bw);
                Createavolunteer.setTextFill(Color.web("#008000"));
                Createavolunteer.setText("Volunteer saved");
                Name.clear();
                Address.clear();
                Nationality.clear();
                Email.clear();

            }
        } bw.close();

    }

    public void searchnowbutton(MouseEvent mouseEvent) throws IOException {
        searchnamefunc();
    }

    public void DELETE(MouseEvent mouseEvent) throws IOException {
        deletefunc();

        oldname.setText("");
        oldadress.setText("");
        oldnationality.setText("");
        oldemail.setText("");
        Createavolunteer.setTextFill(Color.web("#008000"));
        Createavolunteer.setText("Volunteer deleted");
    }

    public void deletefunc() throws IOException {
        String tempFile = "tempfile.txt";
        File newFile = new File(tempFile);
        FileWriter fw = new FileWriter(tempFile,true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        File file = new File("voluntterdata.txt");
        BufferedReader rd = new BufferedReader(new FileReader(file));

        String s;
        int counter = 0;
        while ((s = rd.readLine()) != null) {
            split = s.split(",");

            if (split.length>1) {
                if (!Objects.equals(split[0], Name1)) {
                    createfunc(split.length-1, split,bw);
                }
                else {
                    if (counter>=1){
                        createfunc(split.length-1,split,bw);
                    }
                    counter++;
                    }
            }
        }
        rd.close();
        pw.flush();
        pw.close();
        file.delete();
        File dump = new File("voluntterdata.txt");
        newFile.renameTo(dump);
    }

    public void createfunc(int j,String[] names, BufferedWriter bw) throws IOException {
        for (int i = 0; i <= j; i++) {
            if (i == j) {
                bw.write(names[i] + "\n");
            } else {
                bw.write(names[i] + ",");
            }
        }
    }

    public void searchnamefunc() throws IOException {

        Name1 = Searchname.getText();

        int counter = 0;

        File file = new File("voluntterdata.txt");
        BufferedReader rd = new BufferedReader(new FileReader(file));

        String s;
        while ((s = rd.readLine()) != null) {
            split = s.split(",");

            if (Objects.equals(split[0], Name1)) {
                editvollunteerlabel.setText("Volunteer found");
                editvollunteerlabel.setTextFill(Color.web("#008000"));

                counter++;
                if (oldname!=null) {
                    //label changes
                    oldname.setText(split[0]);
                    oldadress.setText(split[1]);
                    oldnationality.setText(split[2]);
                    oldemail.setText(split[3]);
                }
                break;
            }

        }
        rd.close();

        if (counter == 0){
            editvollunteerlabel.setText("Enter valid name");
            editvollunteerlabel.setTextFill(Color.web("#ff0000"));
        }
    }

}

