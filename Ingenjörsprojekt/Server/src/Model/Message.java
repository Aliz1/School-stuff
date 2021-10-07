package Model;

import java.io.Serializable;


public class Message implements Serializable {

    String info;
    Nodes Node;

    public Message (String info){
        this.info = info;
    }

    public Message(String info, Nodes node){
        this.info = info;
        this.Node = node;
    }

    public String getInfo() {
        return info;
    }

    public Nodes getNode() {
        return Node;
    }

   /* @Override
    public String toString() {
        return String.format("The number of nodes is: %s");
    }*/
}

