package com.baimamboukar.java.rms;

import java.io.IOException;
import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import com.baimamboukar.java.rms.src.database.Database;
import com.baimamboukar.java.rms.src.models.Course;
import com.baimamboukar.java.rms.src.models.Student;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.CheckBoxList;
import com.googlecode.lanterna.gui2.ComboBox;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.TextBox;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class RMS {
        static Panel contentPanel;

        public static void main(String[] args) {
                String result = "Failed";
                Connection db = Database.connect();
                if (db != null)
                        result = "[DB CONNEXION]:Done ៚";
                TextBox namTextBox = new TextBox("");
                TextBox matriculTextBox = new TextBox("");
                TextBox phoneTextBox = new TextBox("");
                TextBox emailTextBox = new TextBox("");
                TextBox passwordBox = new TextBox("").setMask('*');
                TextBox birthTextBox = new TextBox("");
                ComboBox<String> genderTextBox = new ComboBox<String>("Male", "Female");
                TextBox parentNameTextBox = new TextBox("");
                TextBox parentPhoneTextBox = new TextBox("");
                TextBox parentEmailTextBox = new TextBox("");

                List<Course> courses = Arrays.asList(new Course("1", "1", "Java one"),
                                new Course("2", "2", "Technical Writing"),
                                new Course("3", "3", "Python prog"), new Course("4", "4", "Computer Org"),
                                new Course("1", "2", "Porbability and stats"), new Course("1", "2", "Intro to S.E"),
                                new Course("1", "2", "Intro to mobile app"));
                CheckBoxList<String> coursesList = new CheckBoxList<String>(new TerminalSize(20, 10));
                courses.forEach((course) -> {
                        coursesList.addItem(course.getCourseName());
                });

                DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
                Screen screen = null;

                try {
                        screen = terminalFactory.createScreen();
                        screen.startScreen();

                        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen);

                        final Window window = new BasicWindow("@ RESULT MONITORING SYSTEM @");

                        window.setHints(Arrays.asList(Window.Hint.CENTERED));

                        contentPanel = new Panel(new GridLayout(4));

                        GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                        gridLayout.setHorizontalSpacing(3);
                        gridLayout.setVerticalSpacing(1);

                        contentPanel.addComponent(
                                        new Separator(Direction.HORIZONTAL)
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        3)));
                        contentPanel.addComponent(
                                        new EmptySpace()
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        2)));

                        contentPanel.addComponent(new Label("Student's name: "));
                        contentPanel.addComponent(namTextBox);

                        contentPanel.addComponent(new Label("Student's matricule: "));
                        contentPanel.addComponent(matriculTextBox);

                        contentPanel.addComponent(new Label("Phone number:"));
                        contentPanel.addComponent(phoneTextBox);

                        contentPanel.addComponent(new Label("Student's email:"));
                        contentPanel.addComponent(emailTextBox);

                        contentPanel.addComponent(new Label("Password:"));
                        contentPanel.addComponent(passwordBox);

                        contentPanel.addComponent(new Label("Date of Birth:"));
                        contentPanel.addComponent(birthTextBox);

                        contentPanel.addComponent(new Label("Student's Gender:"));
                        contentPanel.addComponent(genderTextBox);

                        contentPanel.addComponent(new Label("Parent's name:"));
                        contentPanel.addComponent(parentNameTextBox);

                        contentPanel.addComponent(new Label("Parent's number:"));
                        contentPanel.addComponent(parentPhoneTextBox);

                        contentPanel.addComponent(new Label("Parent's Email:"));
                        contentPanel.addComponent(parentEmailTextBox);

                        contentPanel.addComponent(new Label("Courses:"));
                        contentPanel.addComponent(coursesList.setLayoutData(
                                        GridLayout.createHorizontallyFilledLayoutData()));

                        // contentPanel.addComponent(new Label("Display Student Infos"));
                        contentPanel.addComponent(new Button("Register", new Runnable() {
                                @Override
                                public void run() {
                                        Student student = new Student(
                                                        matriculTextBox.getText(),
                                                        namTextBox.getText(),
                                                        emailTextBox.getText(),
                                                        passwordBox.getText(),
                                                        phoneTextBox.getText(),
                                                        birthTextBox.getText(),
                                                        coursesList.getCheckedItems());

                                        MessageDialog.showMessageDialog(
                                                        textGUI,
                                                        "REGISTRATION DONE",
                                                        "Matricule: " + student.getMatricule() + "\n" +
                                                                        "Name: " + student.getName() + "\n" +
                                                                        "Email: " + student.getEmail() + "\n" +
                                                                        "Phone: " + student.getPhone() + "\n" +
                                                                        "Birthdate: " + student.getBirthDate() + "\n" +
                                                                        "Parent's Name: " + parentNameTextBox.getText()
                                                                        + "\n" +
                                                                        "Parent's Email: "
                                                                        + parentEmailTextBox.getText()
                                                                        + "\n" +
                                                                        "Password: " + student.getPassword()
                                                                        + "\n" + "Courses: " + student.getCourses());
                                        // String query = "INSERT INTO students VALUES ("
                                        // + Integer.parseInt(student.getMatricule()) + ","
                                        // + student.getName() + "," + student.getEmail() + ","
                                        // + student.getPhone() + "," +
                                        // Integer.parseInt("1") + "," + student.getCourses().toString()
                                        // + ","
                                        // + student.getPassword() + "," + genderTextBox.getText()
                                        // + ");";
                                        // String query = MessageFormat.format(
                                        // "INSERT INTO students VALUES({0}, {1}, {2},{3}, {4}, {5}, {6}, {7});",
                                        // student.getMatricule(), student.getName(), student.getEmail(),
                                        // student.getPhone(), 2, student.getCourses().toString(),
                                        // student.getPassword(), genderTextBox.getText());
                                        // System.out.println(query);
                                        // Database.insert(query);
                                }
                        }).setLayoutData(
                                        GridLayout.createHorizontallyEndAlignedLayoutData(4)));
                        contentPanel.addComponent(
                                        new EmptySpace()
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(new Label(result));
                        contentPanel.addComponent(
                                        new EmptySpace()
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));

                        /*
                         * Close off with an empty row and a separator, then a button to close the
                         * window
                         */
                        contentPanel.addComponent(
                                        new EmptySpace()
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));

                        contentPanel.addComponent(
                                        new Separator(Direction.HORIZONTAL)
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(
                                        new Button("Close", window::close).setLayoutData(
                                                        GridLayout.createHorizontallyEndAlignedLayoutData(4)));

                        /*
                         * We now have the content panel fully populated with components. A common
                         * mistake is to forget to attach it to
                         * the window, so let's make sure to do that.
                         */
                        window.setComponent(contentPanel);

                        textGUI.addWindowAndWait(window);

                } catch (IOException e) {
                        e.printStackTrace();
                } finally {
                        if (screen != null) {
                                try {
                                        screen.stopScreen();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                        }
                }
        }

        public static Panel getPanel() {
                return contentPanel;
        }
}
