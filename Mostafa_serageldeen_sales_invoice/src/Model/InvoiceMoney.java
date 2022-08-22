package Model;

import java.util.ArrayList;

public class InvoiceMoney
{

    private int invoiceTotal;
    private ArrayList<Integer> ItemsTotal;


    /*The class has 2 constructors*/
    public InvoiceMoney()
    {

        /*Create the ArrayList to be store in Heap*/
        ItemsTotal = new ArrayList<Integer>();

    }


    public InvoiceMoney(int total , ArrayList<Integer> itemsTotal)
    {

        this();

        invoiceTotal = total;
        ItemsTotal = itemsTotal;

    }


    /*Setter and getter methods for class*/
    public void setInvoiceTotal(int total)
    {


        invoiceTotal = total;

    }

    public int getInvoiceTotal()
    {

        return invoiceTotal;

    }



    public ArrayList<Integer> getItemsTotal()
    {

        return ItemsTotal;

    }

    public void setItemsTotal(ArrayList<Integer> itemsTotal)
    {


        ItemsTotal = itemsTotal;

    }


    /*Add a new Item total to list*/
    public void addItemTotal(Integer itemTotal)
    {


        ItemsTotal.add(itemTotal);


    }

}
