package edu.qc.seclass.glm;

public class listModel {
    private int listId;
    private int isChecked;
    private String itemQty;
    private String itemName;

    public listModel(int listId, int isChecked, String itemQty, String itemName){
        this.listId = listId;
        this.isChecked = isChecked;
        this.itemQty = itemQty;
        this.itemName = itemName;
    }

    public int getListId(){
        return listId;
    }

    public int getIsChecked(){
        return isChecked;
    }

    public String getItemQty(){
        return itemQty;
    }

    public String getItemName(){
        return itemName;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public void setIsChecked(int isChecked) {
        this.isChecked = isChecked;
    }

    public void setItemQty(String itemQty) {
        this.itemQty = itemQty;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
