package Model;

import java.util.ArrayList;

public class modelTest
{

    private static ArrayList<InvoiceHeader> test ;


    public static void main(String []args)
    {

        readTest();

       //WriteTest();




    }


    public static void readTest()
    {
        FileOperation f = new FileOperation();

        f.readFile();


        test = f.readFile();


        for(int i = 0 ; i < test.size(); i++)
        {

            InvoiceHeader currentInvoice = test.get(i);


            System.out.println("InVoice num:" + currentInvoice.getInvoiceNum() +
                    " Invoice Date:" + currentInvoice.getInvoiceDate() + " Customer name:  " + currentInvoice.getCustomerName()

                    + currentInvoice.getInvoiceLines().toString());
//+



        }





    }


    public static void WriteTest()
    {

        FileOperation f = new FileOperation();



        InvoiceHeader newInvoice = new InvoiceHeader(3 , "8/17/2022" , "Mostafa");


        //String itemName, int itemPrice, int count
        InvoiceLine newInvoiceLine = new InvoiceLine("Donut" , 15 , 8);


        newInvoice.addInvoiceLine(newInvoiceLine);

        test.add(newInvoice);

        f.writeFile(test);




    }


}
