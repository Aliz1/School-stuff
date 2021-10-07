package Model;

import Controller.Controller;

public class Nodes
{
    Controller controller;

    private String id = "";

    public Nodes(String ID)
    {
        this.id = ID;
    }

    public String getId() {
        return id;
    }
    public String setID(String ID)
    {
        this.id = ID;
        return id;
    }
}
