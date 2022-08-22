package Model;

import java.util.ArrayList;

public class InvoiceHeader
{

    /*Class members*/
    private int invoiceNum;
    private String invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLine> InvoiceLines;


    /*Constructor 1*/


    public InvoiceHeader()
    {

        InvoiceLines = new ArrayList<InvoiceLine>();

    }


    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName)
    {
        this();
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;


    }

    /*Constructor 2*/
    public InvoiceHeader(int invoiceNum, String invoiceDate, String customerName, ArrayList<InvoiceLine> invoiceLines)
    {
        this();
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        InvoiceLines = invoiceLines;
    }


    /*Getter methods*/
    public int getInvoiceNum() {
        return invoiceNum;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return InvoiceLines;
    }


    /*Setter methods*/
    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {InvoiceLines = invoiceLines;}


    /*Add InvoiceLine to ArrayList InvoiceLines*/
    public void addInvoiceLine(InvoiceLine newInvoiceLine)
    {
        this.InvoiceLines.add(newInvoiceLine);


    }




}
