package com.baimamboukar.java.rms.src.ui;

import java.io.IOException;
import java.util.Arrays;

import com.baimamboukar.java.rms.src.database.Database;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Borders;
import com.googlecode.lanterna.gui2.Button;
import com.googlecode.lanterna.gui2.DefaultWindowManager;
import com.googlecode.lanterna.gui2.Direction;
import com.googlecode.lanterna.gui2.EmptySpace;
import com.googlecode.lanterna.gui2.GridLayout;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Separator;
import com.googlecode.lanterna.gui2.Window;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.gui2.dialogs.MessageDialogButton;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class Dashboard {
        static Panel contentPanel;
        static int studentsCount = Database.getCount("SELECT COUNT(*) as count FROM students");

        static int teachersCount = Database.getCount("SELECT COUNT(*) as count FROM teachers");
        static int coursesCount = Database.getCount("SELECT COUNT(*) as count FROM courses");
        static int resultsCount = Database.getCount("SELECT COUNT(*) as count FROM results");

        public static void main(String[] args) {

                DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

                Screen screen = null;

                try {

                        screen = terminalFactory.createScreen();
                        screen.startScreen();
                        screen.doResizeIfNecessary();

                        final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen,
                                        new DefaultWindowManager(),
                                        new EmptySpace(TextColor.ANSI.CYAN_BRIGHT));

                        final Window window = new BasicWindow("Admin Dashboard");
                        window.setFixedSize(new TerminalSize(100, 30));
                        window.setDecoratedSize(new TerminalSize(4, 12));
                        window.setHints(Arrays.asList(Window.Hint.CENTERED));
                        contentPanel = new Panel(new GridLayout(4));
                        Panel mainPanel = new Panel(new GridLayout(4));
                        mainPanel.addComponent(new Label("Hello World"));

                        final Panel activPanel = contentPanel;
                        GridLayout gridLayout = (GridLayout) contentPanel.getLayoutManager();
                        gridLayout.setHorizontalSpacing(2);
                        contentPanel.addComponent(
                                        new Separator(Direction.HORIZONTAL)
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));

                        contentPanel.addComponent(
                                        new Label("｟" + studentsCount + "K Students｠")
                                                        .setBackgroundColor(new TextColor.RGB(14, 244, 125)));
                        contentPanel.addComponent(
                                        new Label("｟" + teachersCount + "K Teachers｠")
                                                        .setBackgroundColor(TextColor.ANSI.BLUE_BRIGHT));
                        contentPanel.addComponent(new Label("｟" + coursesCount + "K Courses｠")
                                        .setBackgroundColor(TextColor.ANSI.RED));
                        contentPanel.addComponent(new Label("｟" + resultsCount + "K Results｠")
                                        .setBackgroundColor(new TextColor.RGB(225, 49, 125)));
                        contentPanel.addComponent(
                                        new Separator(Direction.HORIZONTAL)
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(new Button(" Result ",
                                        new Runnable() {
                                                public void run() {
                                                        window.setComponent(ResultGUI.publishResult(contentPanel,
                                                                        window, textGUI));
                                                };
                                        })
                                                        .withBorder(Borders.doubleLine("publish"))
                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                        GridLayout.Alignment.CENTER,
                                                                        GridLayout.Alignment.CENTER)));
                        contentPanel.addComponent(new Button(" Students ",
                                        () -> MessageDialog.showMessageDialog(textGUI, "MessageBox",
                                                        "We are still cooking this feature ! ☄☄☄☄☄",
                                                        MessageDialogButton.OK))

                                                                        .withBorder(Borders.doubleLine("add"))
                                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                                        GridLayout.Alignment.END,
                                                                                        GridLayout.Alignment.BEGINNING)));
                        contentPanel.addComponent(new Button(" Teacher ",
                                        () -> MessageDialog.showMessageDialog(textGUI, "MessageBox",
                                                        "We are still cooking this feature ! ☄☄☄☄☄",
                                                        MessageDialogButton.OK))
                                                                        .withBorder(Borders.doubleLine("add"))
                                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                                        GridLayout.Alignment.CENTER,
                                                                                        GridLayout.Alignment.CENTER)));
                        contentPanel.addComponent(new Button(" Course ",
                                        () -> MessageDialog.showMessageDialog(textGUI, "MessageBox",
                                                        "We are still cooking this feature ! ☄☄☄☄☄",
                                                        MessageDialogButton.OK))
                                                                        .withBorder(Borders.doubleLine("add"))
                                                                        .setSize(new TerminalSize(8, 4))
                                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                                        GridLayout.Alignment.CENTER,
                                                                                        GridLayout.Alignment.CENTER)));

                        /** ACTION BUTTONS FOR DISPLAY */
                        contentPanel.addComponent(
                                        new Label("☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄☄ ")
                                                        .setLayoutData(
                                                                        GridLayout.createHorizontallyFilledLayoutData(
                                                                                        4)));
                        contentPanel.addComponent(new Button(" Students ☄ ",
                                        () -> window.setComponent(
                                                        StudentGUI.displayStudents(contentPanel, window, textGUI)))
                                                                        .withBorder(Borders.singleLine("display")));
                        contentPanel.addComponent(new Button(" Teacher ☄ ",
                                        () -> window.setComponent(
                                                        TeacherGUI.displayteachers(contentPanel, window, textGUI)))
                                                                        .withBorder(Borders.singleLine("display")));
                        contentPanel.addComponent(new Button(" Results ☄ ",
                                        () -> window.setComponent(
                                                        ResultGUI.displayStudents(contentPanel, window, textGUI)))
                                                                        .withBorder(Borders.singleLine("display")));
                        contentPanel.addComponent(new Button(" Course ☄ ",
                                        () -> window.setComponent(
                                                        CourseGUI.displayCourses(contentPanel, window, textGUI)))
                                                                        .setLayoutData(GridLayout.createLayoutData(
                                                                                        GridLayout.Alignment.CENTER,
                                                                                        GridLayout.Alignment.CENTER))
                                                                        .withBorder(Borders.singleLine("display")));

                        // contentPanel.addComponent(new Label("RECENTLY ADDED STUDENTS")
                        // .setBackgroundColor(new TextColor.RGB(14, 244, 125)))
                        // .setLayoutData(GridLayout.createHorizontallyFilledLayoutData(4));
                        // List<Component> comps = StudentGUI.displayStudents(contentPanel, window,
                        // textGUI)
                        // .getChildrenList();
                        // comps.forEach((comp) -> {
                        // contentPanel.addComponent(comp);
                        // });

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

                        window.setComponent(activPanel);

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