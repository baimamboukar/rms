package com.baimamboukar.java.rms.services.interfaces;

import java.util.List;

public interface SQLiteConnectInterface {
    public void connect();

    public void close();

    public List<String> execute();

    public static void main(String[] args) {
    }
}