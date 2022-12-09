package edu.qc.seclass.glm;

public class GroceryList {
    private long gListId;
    private String gListName;

    public GroceryList() {

    }

    public GroceryList(long gListId, String gListName) {
        this.gListId = gListId;
        this.gListName = gListName;
    }



    public long getGListId() {
        return this.gListId;
    }

    public String getGListName() {
        return gListName;
    }
    public void setGListId(long gListId) {
        this.gListId = gListId;
    }
    public void setGroceryListName(String gListName) {
        this.gListName = gListName;
    }
   // public String toString() {
   //     return this.gListId+", "+this.gListName;
    }



