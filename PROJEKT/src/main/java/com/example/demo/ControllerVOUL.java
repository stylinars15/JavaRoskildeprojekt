package com.example.demo;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.io.*;
import java.util.Objects;

public class ControllerVOUL extends HelloController {

    public DatePicker date;
    public TextField task,shiftlength;

    @Override

    public void searchnowbutton(MouseEvent mouseEvent) throws IOException {
        super.searchnowbutton(mouseEvent);
    }


    public void donegetshift(MouseEvent mouseEvent) throws IOException {

        String Dateuse = String.valueOf(date.getValue());
        String Taskuse = task.getText();
        String Shiftlengthuse = shiftlength.getText();

        if (Objects.equals(editvollunteerlabel.getText(), "Volunteer found")){


            if ((Objects.equals(Dateuse, "")) || Objects.equals(Taskuse, "") || Objects.equals(Shiftlengthuse, "")){

                Createavolunteer.setTextFill(Color.web("#FF0000"));
                Createavolunteer.setText("All information required");
            }
            else{
                File file = new File("voluntterdata.txt");
                BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
                PrintWriter pw = new PrintWriter(bw);

                for (int i = 0; i <= split.length; i++) {

                    if (i == split.length) {
                        bw.write(Dateuse + ",");
                        bw.write(Taskuse + ",");
                        bw.write(Shiftlengthuse + "\n");

                    } else {
                        bw.write(split[i] + ",");
                    }
                }
                bw.close();
                deletefunc();

                Createavolunteer.setTextFill(Color.web("#008000"));
                Createavolunteer.setText("Shift saved");

                date.getEditor().clear();
                task.clear();
                shiftlength.clear();
            }

        }

    }

}
