package Model;

import Controller.Controller;

public class Nodes
{
    Controller controller;
    private int numOfNodes = 0;
    public Nodes(Controller controller)
    {
        this.controller = controller;
    }

    public int getNumOfNodes()
    {
        return numOfNodes;
    }
    public void IncreaseNodes()
    {
        numOfNodes++;
    }
    public void ResetNodes()
    {
        numOfNodes = 0;
    }
}
