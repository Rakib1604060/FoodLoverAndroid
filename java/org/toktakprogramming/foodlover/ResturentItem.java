package org.toktakprogramming.foodlover;

public class ResturentItem {
    String resturentName,location,itemNumber,tableNumber,id,info;

    public String getId() {
        return id;
    }

    public ResturentItem(String resturentName, String location, String itemNumber, String tableNumber, String id,String info) {
        this.resturentName = resturentName;
        this.location = location;
        this.itemNumber = itemNumber;
        this.tableNumber = tableNumber;
        this.id=id;
        this.info=info;

    }

    public String getInfo() {
        return info;
    }

    public String getResturentName() {
        return resturentName;
    }

    public void setResturentName(String resturentName) {
        this.resturentName = resturentName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }
}
