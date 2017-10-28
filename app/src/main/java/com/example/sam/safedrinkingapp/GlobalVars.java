package com.example.sam.safedrinkingapp;

import android.app.Application;

/**
 * Created by Sam on 10/28/2017.
 */

public class GlobalVars extends Application {
    private Integer weight;
    private Integer height;
    private String MaleFemale;
    private double MaleConst = 0.73;
    private double FemaleConst = 0.66;

    public Integer getWeight(){
        return weight;
    }
    public Integer getHeight(){
        return height;
    }
    public String getSex(){
        return MaleFemale;
    }
    public double getMaleConst(){
        return MaleConst;
    }
    public double getFemaleConst(){
        return FemaleConst;
    }
    public void setWeight(Integer newWeight){
        weight=newWeight;
    }
    public void setHeight(Integer newHeight){
        height=newHeight;
    }
    public void setSex(String newSex){
        MaleFemale=newSex;
    }

    //private double drunkCalc = ((getWeight()*getWeightGrams()*100*getMaleConst()));
    //public Double getDrunkCalc() { return drunkCalc; }
    /*public void determineSex(){
        if (){

        }
    }*/
}
