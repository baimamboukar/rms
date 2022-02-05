/*
 📌📌📌 THE ICT UNIVERSITY, FALL 20201 📌📌📌

 🚩 ♨♨♨♨♨ JAVA PROGRAMMING I ♨♨♨♨♨ 🚩

 🚩 INSTRUCTOR: ENGR. Daniel Moune

 📌﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋﹋📌

 🎯 NAMES: BAIMAM BOUKAR JEAN JACQUES
 🎯 EMAIL: baimam.jeanjacque@ictuniversity.edu.cm
 🎯 WHATSAPP: (+237) 690535759
 🎯 TWITTER: @baimamjj
 🎯 LINKEDIN: linkedin.com/in/baimamboukar
 🎯 WEBSITE: www.baimamboukar.me
 */

package com.baimamboukar.java.rms.src.ui;

import java.util.List;

import com.baimamboukar.java.rms.src.database.Database;
import com.baimamboukar.java.rms.src.models.Student;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.gui2.table.Table;

public class StudentGUI {
        public static Panel displayStudents(Panel back, Window window, WindowBasedTextGUI gui) {
                Panel contentPanel = new Panel(new GridLayout(4));
                GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                gridLayout.setHorizontalSpacing(2);
                contentPanel.addComponent(
                                new Separator(Direction.HORIZONTAL)
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                Table<String> table = new Table<>("Matricule", "Name", "Email", "Phone", "Courses");

                List<Student> students = Database.getStudents("SELECT * FROM students");
                table.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(4));
                if (students != null) {
                        for (Student student : students) {
                                table.getTableModel().addRow(student.getMatricule(), student.getName(),
                                                student.getEmail(),
                                                student.getPhone(),
                                                student.getCourses().toString());
                        }
                }
                table.setSelectAction(() -> {

                        MessageDialog.showMessageDialog(gui, "Student Information",
                                        "Names: Baimam Boukar\n" +
                                                        "Matricule: ICTU20201685\n" +
                                                        "Email: baimamboukar@gmail.com\n" +
                                                        "Phone: +237 678 987 654\n" +
                                                        "Courses: Java, Python",
                                        MessageDialogButton.Close);
                });

                table.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(
                                4));
                contentPanel.addComponent(table);
                contentPanel.addComponent(
                                new Separator(Direction.HORIZONTAL)
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                contentPanel.addComponent(
                                new Button("Back", () -> {
                                        window.setComponent(back);
                                })
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));

                return contentPanel;
        }
}
