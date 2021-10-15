package Model;

import java.io.Serializable;

import Controller.Controller;

public class Nodes implements Serializable
{
    Controller controller;

    private String id = "";
    private String difficulty = "";

    public Nodes(String ID, String Difficulty)
    {
        this.id = ID;
        this.difficulty = Difficulty;
    }

    public String getId() {
        return id;
    }
    public String setID(String ID)
    {
        this.id = ID;
        return id;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
    @Override
    public String toString() {
        return String.format("ID: "+ getId() + " Difficulty: " + getDifficulty());
    }
}
