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

package com.baimamboukar.java.rms.src.models;

import java.io.IOException;
import java.util.Arrays;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.BasicWindow;
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
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class Teacher {
    private int id;
    private String names;
    private int courseID;
    private String email;

    public Teacher(int id, String names, int courseID, String email) {
        this.id = id;
        this.names = names;
        this.courseID = courseID;
        this.email = email;
    }

    
    /** 
     * @return int
     */
    public int getId() {
        return id;
    }

    
    /** 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * @return String
     */
    public String getNames() {
        return names;
    }

    
    /** 
     * @param names
     */
    public void setNames(String names) {
        this.names = names;
    }

    
    /** 
     * @return int
     */
    public int getCourseID() {
        return courseID;
    }

    
    /** 
     * @param courseID
     */
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Teacher [courseID =" + courseID + ", id =" + id + ", names =" + names + "]";
    }

    
    /** 
     * @return String
     */
    public String getEmail() {
        return email;
    }

    
    /** 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        Teacher teacher = new Teacher(1, "Magic teacher", 4, "magic@teacher.cm");
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();

        Screen screen = null;

        try {

            screen = terminalFactory.createScreen();
            screen.startScreen();
            screen.doResizeIfNecessary();

            final WindowBasedTextGUI textGUI = new MultiWindowTextGUI(screen,
                    new DefaultWindowManager(),
                    new EmptySpace(TextColor.ANSI.CYAN_BRIGHT));

            final Window window = new BasicWindow("TEACHER INFORMATION");
            window.setFixedSize(new TerminalSize(100, 30));
            window.setDecoratedSize(new TerminalSize(4, 12));
            window.setHints(Arrays.asList(Window.Hint.CENTERED));
            Panel contentPanel = new Panel(new GridLayout(2));
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
            contentPanel.addComponent(new Label("Name: "));
            contentPanel.addComponent(new Label(teacher.names));
            contentPanel.addComponent(new Label("email: "));
            contentPanel.addComponent(new Label(teacher.email));
            contentPanel.addComponent(new Label("Course ID: "));
            contentPanel.addComponent(new Label(teacher.getCourseID() + ""));
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
}