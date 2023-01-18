package it.unitn.massimomichelutti.webarch.asn2.beans;

import java.io.Serializable;

public class ErrorMsg implements Serializable {
    String msg;

    public ErrorMsg(){
        msg="";
    }

    public ErrorMsg(String message){
        msg=message;
    }

    /*
    * This function returns the string containing the message
     */
    public String getMsg(){
        return msg;
    }

    /*
     * This function sets the message to the value specified by @param message
     */
    public void setMsg(String message){
        msg=message;
    }

    @Override
    public String toString(){
        return msg;
    }
}
