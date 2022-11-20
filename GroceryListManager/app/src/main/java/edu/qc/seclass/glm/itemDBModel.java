package edu.qc.seclass.glm;

public class itemDBModel {

    private String itemName;
    private String itemType;
    private String itemUnit;
    private int itemQTY;
    private int itemSel;
    private int itemChk;

    public itemDBModel(int anInt, String string, String string1) {
    }


    public String getItemName(){
        return itemName;
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public String getItemType(){
        return itemType;
    }

    public void setItemType(String itemType){
        this.itemType = itemType;
    }

    public String getItemUnit(){
        return itemUnit;
    }

    public void setItemUnit(String itemUnit){
        this.itemUnit = itemUnit;
    }

    public int getItemQTY(){
        return itemQTY;
    }

    public void setItemQTY(int itemQTY){
        this.itemQTY = itemQTY;
    }

    public int getItemSel(){
        return itemSel;
    }

    public void setItemSel(int itemSel){
        this.itemSel = itemSel;
    }

    public int getItemChk(){
        return itemChk;
    }

    public void setItemChk(int itemChk){
        this.itemChk = itemChk;
    }


    public itemDBModel(String itemName, String itemType, String itemUnit, int itemQTY, int itemSel, int itemChk){
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemUnit = itemUnit;
        this.itemQTY = itemQTY;
        this.itemSel = itemSel;
        this.itemChk = itemChk;
    }


}