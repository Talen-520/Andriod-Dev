package edu.qc.seclass.glm;

public class item {
    private long itemId;
    private String itemName;
    private String actualitemType;
    private itemType itemType;

    public void Item(long itemId, String itemName, itemType itemType) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
    }

    public item( long itemID, String itemName, String actualitemType){
        this.itemId = itemID;
        this.itemName = itemName;
        this.actualitemType =actualitemType;
    }

    public item(long itemId, String itemName, long itemTypeId, String itemTypeName) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = new itemType(itemTypeId, itemTypeName);
    }

    public item() {

    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public itemType getItemType() {
        return itemType;
    }

    public void setItemType(itemType itemType) {
        this.itemType = itemType;
    }
}
