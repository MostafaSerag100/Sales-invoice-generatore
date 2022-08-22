package Model;

public class InvoiceItems
{

    public int invoiceNum;
    public InvoiceLine Items;



    public InvoiceItems()
    {





    }

    public int getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public InvoiceLine getItems() {
        return Items;
    }

    public void setItems(InvoiceLine items) {
        Items = items;
    }





}
