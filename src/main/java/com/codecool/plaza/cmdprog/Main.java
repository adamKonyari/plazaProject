package com.codecool.plaza.cmdprog;

public class Main {
    public static void main(String[] args) {
        try {
            new CmdProgram().run();
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
