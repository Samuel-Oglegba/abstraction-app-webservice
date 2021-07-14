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

    public boolean validateDotInput(String inputString){
        try {
          //  inputString = inputString.contains("graph") ? ;
            boolean res = false;
            if (inputString.contains("digraph")){
                if( inputString.contains("->") )
                    res = true;
            }
            else if(inputString.contains("graph")){
                if( inputString.contains("--") )
                    res = true;
            }

            return res;
        }
        catch (Exception e){
            return false;
        }
    }

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
