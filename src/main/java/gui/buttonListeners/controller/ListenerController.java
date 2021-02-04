package gui.buttonListeners.controller;

import blogic.filetype.executor.Executable;
import gui.view.Table;

import javax.swing.*;

public class ListenerController {
    private String filename = "";
    private Executable executable;
    private final JFrame frame;
    private Table table;

    public ListenerController(JFrame frame) {
        this.frame = frame;
        table = new Table(frame);

    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setExecutable(Executable executable) {
        this.executable = executable;
    }

    public String getFilename() {
        return filename;
    }

    public Table getTable() {
        return table;
    }

    public Executable getExecutable() {
        return executable;
    }
}
