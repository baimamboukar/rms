package com.baimamboukar.java.rms.src.ui;

import java.util.List;

import com.baimamboukar.java.rms.src.database.Database;
import com.baimamboukar.java.rms.src.models.Teacher;
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

public class TeacherGUI {
        public static Panel displayteachers(Panel back, Window window, WindowBasedTextGUI gui) {
                Panel contentPanel = new Panel(new GridLayout(4));
                GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                gridLayout.setHorizontalSpacing(2);
                contentPanel.addComponent(
                                new Separator(Direction.HORIZONTAL)
                                                .setLayoutData(
                                                                GridLayout.createHorizontallyFilledLayoutData(
                                                                                4)));
                Table<String> table = new Table<>("ID", "Name", "Email", "CoursesID");

                List<Teacher> teachers = Database.getTeachers("SELECT * FROM teachers");
                table.setLayoutData(GridLayout.createHorizontallyFilledLayoutData(4));
                if (teachers != null) {
                        for (Teacher teacher : teachers) {
                                table.getTableModel().addRow("" + teacher.getId(), teacher.getNames(),
                                                teacher.getEmail(),
                                                teacher.getCourseID() + "");
                        }
                }
                table.setSelectAction(() -> {

                        MessageDialog.showMessageDialog(gui, "Teacher Information",
                                        "Names: Alpha Beta\n" +
                                                        "Matricule: ICTU20201685\n" +
                                                        "Email: baimamboukar@gmail.com\n",
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
