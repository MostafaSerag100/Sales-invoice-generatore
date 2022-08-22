package Model;

public class InvoiceLine
{

    /*Class members*/
    private String itemName;
    private int itemPrice;
    private int count;


    public InvoiceLine()
    {



    }



    public InvoiceLine(String itemName, int itemPrice, int count)
    {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }


    /*Getter and setter methods to access private variables*/
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }




    @Override
    public String toString()
    {

        return "Item Name:" + this.itemName + " Item Price:" + this.itemPrice + " Count:" + this.count;

    }






}
