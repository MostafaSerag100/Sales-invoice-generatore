package Model;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperation
{

    //
    public ArrayList<InvoiceHeader> readFile()
    {



        /*ArrayList to store Invoice Items*/
        ArrayList<InvoiceItems> invoiceItems = new ArrayList<>();

        /*Array list to store list of invoices from Csv files*/
        ArrayList<InvoiceHeader> invoicesReturn = new ArrayList<>();



        /*Line is a temp buffer*/
        String Line;
        try {

            /*Read file from specified path*/
            JFileChooser FileInvoiceHeaders = new JFileChooser(new File("src\\CSV_files\\"));

            /*User chooses file*/
            JFileChooser FileInvoiceLines = new JFileChooser(new File("src\\CSV_files\\"));//src\CSV_files\InvoiceHeader.csv


            /*Show File chooser Menu to user*/
            FileInvoiceHeaders.showSaveDialog(null);
            FileInvoiceLines.showSaveDialog(null);


            Scanner inputStream = new Scanner(FileInvoiceLines.getSelectedFile());

            Scanner inputStream2 = new Scanner(FileInvoiceHeaders.getSelectedFile());

            /*Read All lines in InvoiceLine.Csv file*/
            while (inputStream.hasNextLine())
            {
                /*Put each line from csv file to a String*/
                Line = inputStream.nextLine();

                /*Store elements in a String array */
                String[] arr = Line.split(",");


                InvoiceItems currentInvoiceItems = new InvoiceItems();
                InvoiceLine currentInvoiceLine = new InvoiceLine();


                /*Get Invoice number for Invoice line*/
                currentInvoiceItems.setInvoiceNum(Integer.parseInt(arr[0]));

                /*Get Invoice Item name*/
                currentInvoiceLine.setItemName(arr[1]);

                /*Get Invoice Item price*/
                currentInvoiceLine.setItemPrice(Integer.parseInt(arr[2]));

                currentInvoiceLine.setCount(Integer.parseInt(arr[3]));


                currentInvoiceItems.setItems(currentInvoiceLine);

                /*Add current invoice item to arrayList*/
                invoiceItems.add(currentInvoiceItems);

            }

            /*Read all lines in InvoiceHeader Csv file*/
            while(inputStream2.hasNextLine())
            {
                /*Put each line from csv file to a String*/
                Line = inputStream2.nextLine();

                /*Store elements in a String array */
                String[] arr = Line.split(",");

                /*Storing each Invoice in csv file*/
                InvoiceHeader currentInvoice = new InvoiceHeader();


                currentInvoice.setInvoiceNum(Integer.parseInt(arr[0]));
                currentInvoice.setInvoiceDate(arr[1]);
                currentInvoice.setCustomerName(arr[2]);


                /*Iterate over all Invoice items */
                for(int i = 0 ; i < invoiceItems.size() ; i++)
                {

                    /*Invoice numbers matches*/
                    if(currentInvoice.getInvoiceNum() == invoiceItems.get(i).getInvoiceNum())
                    {

                        /*All Invoice items to current invoice*/
                        currentInvoice.addInvoiceLine(invoiceItems.get(i).getItems());


                    }



                }

                invoicesReturn.add(currentInvoice);


            }




        }

        catch(Exception e)
        {

            JOptionPane.showMessageDialog(null , "Files not found");
            e.printStackTrace();
        }

        return invoicesReturn;
    }






    public void writeFile(ArrayList<InvoiceHeader> invoiceHeaders)
    {


        try
        {

            JFileChooser HeaderFileChooser = new JFileChooser("src\\CSV_files\\");
            JFileChooser LineFileChooser = new JFileChooser("src\\CSV_files\\");

            /*Show File chooser Menu to user*/
            HeaderFileChooser.showSaveDialog(null);
            LineFileChooser.showSaveDialog(null);


            /*Open file path and read file*/
            File headerFile = HeaderFileChooser.getSelectedFile();
            File itemsFile =  LineFileChooser.getSelectedFile();

            /*The print writer that will print updates to file*/
            PrintWriter outToHeader = new PrintWriter(headerFile);
            PrintWriter outToItems = new PrintWriter(itemsFile);

            /*Loop over all Invoice headers in ArrayList*/
            for (int i = 0; i < invoiceHeaders.size(); i++)
            {

                InvoiceHeader currentInvoice = invoiceHeaders.get(i);

                /*Fill InvoiceHeader file*/
                outToHeader.printf("%d,%s,%s\n" , currentInvoice.getInvoiceNum(),
                        currentInvoice.getInvoiceDate() , currentInvoice.getCustomerName());

                /*Loop over all items in each Invoice Header*/
                for(int j = 0 ; j <currentInvoice.getInvoiceLines().size(); j++)
                {

                    InvoiceLine currentInvoiceLine = currentInvoice.getInvoiceLines().get(j);

                    /*Fill data to InvoiceLine csv file*/
                    outToItems.printf("%d,%s,%d,%d\n" , currentInvoice.getInvoiceNum() , currentInvoiceLine.getItemName()
                    , currentInvoiceLine.getItemPrice() , currentInvoiceLine.getCount());

                }



            }

            /*Close files after updating*/
            outToHeader.close();
            outToItems.close();

        }

        catch (Exception e)
        {

            /*Show Dialog File not Found*/

            JOptionPane.showMessageDialog(null , "Files not found");

            e.printStackTrace();

        }











    }



}
