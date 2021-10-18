package Model;

import java.io.Serializable;

import Controller.Controller;

public class Nodes implements Serializable
{
    Controller controller;

    private String mac = "";
    private String difficulty = "";

    public Nodes(String Mac, String Difficulty)
    {
        this.mac = Mac;
        this.difficulty = Difficulty;
    }

    public String getMac() {
        return mac;
    }
    public String setMac(String Mac)
    {
        this.mac = Mac;
        return mac;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    @Override
    public String toString() {
        return String.format("ID: "+ getMac() + " Difficulty: " + getDifficulty());
    }
}
