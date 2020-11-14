package com.example.TTT;

public class Button {

    private char type;

    public Button(){
        this.type = '-';

    }

    /**
     *
     * @param type
     */
    public Button(char type){
        this.type = type;
    }

    /**
     * sets the type of this button - possible delete if there isn't a good use
     * @param type - a char that represents what type of button this is
     */
    public void setType(char type){
        this.type = type;
    }

    /**
     * getter method for the type
     * @return the type of this button
     */
    public char getType(){
        return this.type;
    }

    /**
     * .equals function compares the type (char) of the two buttons
     * @param other - other Button to compare to this one
     * @return true if their types are the same, false otherwise
     */
    public boolean equals(Button other){
        if(other.getType() == this.type){
            return true;
        } else{
            return false;
        }
    }

}
