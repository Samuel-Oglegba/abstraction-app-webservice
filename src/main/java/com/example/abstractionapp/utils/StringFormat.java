package com.example.abstractionapp.utils;

public class StringFormat {

    /**
     * this method removes all special characters in a String
     * @param inputString
     * @return
     */
    public String removeWhiteSpace(String inputString){
        return inputString.replaceAll("[^a-zA-Z0-9]", " ");
    }//removeWhiteSpace
    /**
     * this method splits the input String by colon
     * @param inputString
     * @return
     */
    public String[] splitByColon(String inputString){
        try {
            return inputString.split(":");
        }
        catch (Exception e){
            return null;
        }
    }//splitByColon

    /**
     * split the string by comma
     * @param inputString
     * @return
     */
    public String[] splitByComma(String inputString){
        try {
            return inputString.split(",");
        }
        catch (Exception e){
            return null;
        }
    }//splitByComma
}
