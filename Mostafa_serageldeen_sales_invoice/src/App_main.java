import Controller.MyListener;
import Model.FileOperation;
import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.InvoiceMoney;
import View.MyFrame;
import View.MyTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class App_main
{
    
    private MyFrame GlobalMainFrame;
    private static int GlobalPrevSelectedRow = -1;

    /*Where all Invoices are stored during program*/
    private static ArrayList<InvoiceHeader> GlobalInvoiceHeadersList;

    private static ArrayList<InvoiceMoney> GlobalInvoiceMoneyList;

    /*JPanel objects declaration*/
    private static JPanel left_panel;
    private static JPanel right_panel;

    /*JScrollPane object declaration*/
    private static JScrollPane table_scroll;
    private static JScrollPane table2_scroll;


    /*MyTable Objects declaration*/
    private static MyTable jtableOfInvoices;

    private static MyTable jtableOfItems;

    /*JTextField object declaration*/
    private static JTextField date_textField;
    private static JTextField customer_textField;



    /*JLabel objects declaration*/
    private static JLabel invoice_table_label;
    private static JLabel invoice_num_label;
    private static JLabel invoice_date_label;
    private static JLabel invoice_customer_label;
    private static JLabel invoice_total_label;
    private static JLabel invoice_total_display;
    private static JLabel invoice_number_display;
    private static JLabel invoice_items_label;

    /*JMenuBar object declaration*/
    private static JMenuBar menuBar;

    /*JMenu object declaration*/
    private static JMenu file;

    /*JMenuItem object declaration*/
    private static JMenuItem load_file;
    private static JMenuItem save_file;


    /*JButton object declaration */
    private static JButton create_inv_button;
    private static JButton del_inv_button;
    private static JButton save_button;
    private static JButton cancel_button;

    private static JButton jButtonAddItem;
    private static JButton jButtonDeleteItem;


    /*Declaration of some usefull ArrayList buffers*/
    private static ArrayList<Integer>arrInvoiceTotal;
    private static ArrayList<Integer>arrItemsTotal;


    /*App Entry point*/
    public static void main(String []args)
    {
        App_main M = new App_main();

        M.startApp();


    }





    private void Print_layout()
    {

        App_main m = new App_main();

        /*Creating a JMenuBar object*/
        menuBar = new JMenuBar();
        menuBar.setBounds(0 , 0 , 100 , 30);



        file = new JMenu("File");

        /*Creating JMenuItems*/
        save_file = new JMenuItem("Save file");
        load_file = new JMenuItem("Load file");

        /*Setting Action commands for each JMenu item*/
        save_file.setActionCommand("Save File");
        load_file.setActionCommand("Load File");



        /*Add JMenuItems to JMenu*/
        file.add(load_file);
        file.add(save_file);

        /*Add JMenu to JMenuBar*/
        menuBar.add(file);


        Runnable[] arrayOfButtonMethods = new Runnable[8];

        arrayOfButtonMethods[0] = this::onCreateInvoiceButtonPressed;
        arrayOfButtonMethods[1] = this::onDeleteButtonPressed;
        arrayOfButtonMethods[2] = this::onDeleteItemButtonPressed;
        arrayOfButtonMethods[3] = this::onAddItemButtonPressed;
        arrayOfButtonMethods[4] = this::onSaveButtonPressed;
        arrayOfButtonMethods[5] = this::onCancelButtonPressed;
        arrayOfButtonMethods[6] = this::onSaveFileSelected;
        arrayOfButtonMethods[7] = this::onLoadFileSelected;

        /*Create an event Listener*/
        MyListener buttonListner = new MyListener(arrayOfButtonMethods);

        /*Button Creation*/
        del_inv_button = new JButton("Delete Invoice");
        create_inv_button = new JButton("Create new Invoice");

        /*Add action listeners to your buttons*/
        del_inv_button.addActionListener(buttonListner);
        create_inv_button.addActionListener(buttonListner);

        /*Set action commands to your buttons*/
        del_inv_button.setActionCommand("Delete Invoice");
        create_inv_button.setActionCommand("Create Invoice");


        /*Set size and location for each button*/
        del_inv_button.setBounds(300 , 700 , 200 , 30);
        create_inv_button.setBounds(50 , 700 , 200 , 30);


        String[] table_header = {"No" , "Date" , "Customer" , "Total"};
        String [][] table_data = m.initInvoiceTableData(GlobalInvoiceMoneyList, GlobalInvoiceHeadersList);




        /*Create Invoice table*/
        jtableOfInvoices = new MyTable(table_data , table_header , false);
        table_scroll = new JScrollPane(jtableOfInvoices);

        /*Pass a Runnable method to be executed on Row selection event*/
        jtableOfInvoices.onRawSelection(this::onRawSelected);

        /*Setting size and location for Scroll pane carrying the table*/
        table_scroll.setBounds(50 , 150 , 600 , 500);

        /*Create the left panel*/
        left_panel = new JPanel();
        left_panel.setBounds(0 , 0 , 700 , 800);
        //left_panel.setBackground(Color.darkGray);
        left_panel.setLayout(null);

        /*Create a JLabel*/
        invoice_table_label = new JLabel("Invoice table");
        invoice_table_label.setBounds(50 ,100 , 100 , 30);


        /*Add items to left pane*/
        left_panel.add(table_scroll);
        left_panel.add(del_inv_button);
        left_panel.add(create_inv_button);
        left_panel.add(menuBar);
        left_panel.add(invoice_table_label);


        /*Create right panel*/
        right_panel = new JPanel();
        right_panel.setBounds(700 , 0 , 900 , 800);
        right_panel.setLayout(null);


        /*Create right panel JLabels*/
        invoice_num_label = new JLabel("Invoice number");
        invoice_num_label.setBounds(50 , 100 , 100 , 30);
        invoice_date_label = new JLabel("Invoice Date");
        invoice_date_label.setBounds(50 , 150 , 100 , 30);
        invoice_customer_label = new JLabel("Customer name");
        invoice_customer_label.setBounds(50 , 200 , 100 , 30);
        invoice_total_label = new JLabel("Invoice Total");
        invoice_total_label.setBounds(50 , 250 , 100 , 30);
        invoice_total_display = new JLabel("23");
        invoice_total_display.setBounds(150 , 250 , 100 , 30);
        invoice_number_display = new JLabel("1");
        invoice_number_display.setBounds(150 , 100 , 100 , 30);
        invoice_items_label = new JLabel("Invoice Items");
        invoice_items_label.setBounds(50 , 300 , 100 , 30);

        /*Creation of right panel textFields */
        customer_textField = new JTextField();
        customer_textField.setBounds(150 , 200 , 300,30);
        date_textField = new JTextField();
        date_textField.setBounds(150 , 150 , 300 , 30);


        /*Create right panel buttons*/
        save_button = new JButton("Save");
        save_button.setBounds(50 , 700 , 100 , 30);
        cancel_button = new JButton("Cancel");
        cancel_button.setBounds(200 , 700 , 100 , 30);
        jButtonAddItem = new JButton("Add Item");
        jButtonAddItem.setBounds(350 , 700 , 100 , 30);
        jButtonDeleteItem = new JButton("Delete Item");
        jButtonDeleteItem.setBounds(500 , 700 , 100 , 30);




        /*Set ActionCommands for buttons*/
        save_button.setActionCommand("Save");
        cancel_button.setActionCommand("Cancel");
        jButtonAddItem.setActionCommand("Add Item");
        jButtonDeleteItem.setActionCommand("Delete Item");

        /*Set Action listeners for Menu items*/
        save_file.addActionListener(buttonListner);
        load_file.addActionListener(buttonListner);



        /*add listener to right panel buttons*/
        jButtonAddItem.addActionListener(buttonListner);
        jButtonDeleteItem.addActionListener(buttonListner);
        save_button.addActionListener(buttonListner);
        cancel_button.addActionListener(buttonListner);

        /*Create Invoice table*/
        String []items_header = {"Item name" , "Item price" , "Count" , "Item total"};
        String [][]items_data = {{""}};



        jtableOfItems = new MyTable(items_data , items_header , true);
        jtableOfItems.onRawSelection(this::onItemRowSelected);

        table2_scroll = new JScrollPane(jtableOfItems);


        DefaultTableModel tableModel = (DefaultTableModel) jtableOfItems.getModel();


        /*Setting size and location for Scroll pane carrying the table*/
        table2_scroll.setBounds(50 , 350 , 600 , 300);



        /*Add components to Right JPanel*/
        right_panel.add(invoice_num_label);
        right_panel.add(invoice_date_label);
        right_panel.add(invoice_customer_label);
        right_panel.add(invoice_total_label);
        right_panel.add(invoice_total_display);
        right_panel.add(invoice_number_display);
        right_panel.add(customer_textField);
        right_panel.add(date_textField);
        right_panel.add(invoice_items_label);
        right_panel.add(cancel_button);
        right_panel.add(save_button);
        right_panel.add(table2_scroll);
        right_panel.add(jButtonAddItem);
        right_panel.add(jButtonDeleteItem);


        /*Add components to frame*/
        JComponent components[] = new JComponent[2];
        components[0] = left_panel;
        components[1] = right_panel;


        GlobalMainFrame = new MyFrame("app" , 1920 , 1080 , 0 , 0 , components);

        /*Make the frame appear*/
        GlobalMainFrame.setVisible(true);


    }


    /*Function to calculate total from a given list of invoices*/
    public ArrayList<InvoiceMoney> fillTotalMoney(ArrayList<InvoiceHeader> invoices)
    {

        /*Number of given invoices in the list*/
        int numOfInvoices = invoices.size();

        /*ArrayList to be returned*/
        ArrayList<InvoiceMoney> toReturnList = new ArrayList<>();

        /*Loop over all invoices*/
        for(int i = 0; i < numOfInvoices; i++)
        {
            /*Total money for each invoice*/
            int totalPerInvoice = 0;

            /*Variable to save money for each invoice*/
            InvoiceMoney currentInvoiceMoney = new InvoiceMoney();

            /*Buffer to save a list of items total money*/
            ArrayList<Integer> buffItemsTotal = new ArrayList<>();

            /*Get current invoice*/
            InvoiceHeader currentInvoice = invoices.get(i);

            /*All invoice lines for current invoice*/
            ArrayList<InvoiceLine> currentInvoiceLines = currentInvoice.getInvoiceLines();

            /*Number of items in current invoice*/
            int numOfInvoiceLines = currentInvoiceLines.size();


            for(int j = 0; j < numOfInvoiceLines; j++)
            {

                Integer totalPerItem = 0;

                InvoiceLine currentInvoiceLine = currentInvoiceLines.get(j);

                /*Calculate total money for each item*/
                totalPerItem = currentInvoiceLine.getItemPrice() * currentInvoiceLine.getCount();

                /*Add total for each item to buffer*/
                buffItemsTotal.add(totalPerItem);

                /*Adding total of each item to total of the whole invoice*/
                totalPerInvoice += totalPerItem;

            }


            /*Adding all collected data to a variable of type InvoiceMoney*/
            currentInvoiceMoney.setInvoiceTotal(totalPerInvoice);
            currentInvoiceMoney.setItemsTotal(buffItemsTotal);

            /*Add the Invoice money to the returned ArrayList*/
            toReturnList.add(currentInvoiceMoney);

        }

        return toReturnList;

    }



    /*Returns data to be displayed on Invoice Header table*/
    public String[][] initInvoiceTableData(ArrayList<InvoiceMoney> ListInvoiceMoney ,
                                           ArrayList<InvoiceHeader> ListInvoiceHeader)
    {

        /*Number of Invoices in list*/
        int numOfInvoices = ListInvoiceHeader.size();

        /*Create 2d Array to be returned*/
        String [][]data = new String[numOfInvoices][4];

        for(int i =0; i < numOfInvoices; i++)
        {

            /*Get current invoice data(Number , Date , Customer name , total)*/
            InvoiceHeader currentInvoice = ListInvoiceHeader.get(i);
            InvoiceMoney currentInvoiceMoney = ListInvoiceMoney.get(i);

            /*Fill table data with current invoice data*/
            data[i][0] = Integer.toString(currentInvoice.getInvoiceNum());
            data[i][1] = currentInvoice.getInvoiceDate();
            data[i][2] = currentInvoice.getCustomerName();
            data[i][3] = Integer.toString(currentInvoiceMoney.getInvoiceTotal());




        }

        return data;


    }


    public void onRawSelected()
    {

        DefaultTableModel LocalTableModel = (DefaultTableModel)jtableOfItems.getModel();


        /*Get number of rows selected*/
        int LocalNumOfSelectedRows = jtableOfInvoices.getSelectedRowCount();




        /*Only 1 row selected??*/
        if(LocalNumOfSelectedRows == 1)
        {
            /*Get currently selected row*/
            int LocalCurrentSelectedRow = jtableOfInvoices.getSelectedRow();
            //JOptionPane.showMessageDialog(null , "Row added:" + LocalCurrentSelectedRow );

            if(LocalCurrentSelectedRow != GlobalPrevSelectedRow)
            {

                /*Delete all rows in invoice Items table*/
                if (jtableOfItems.getRowCount() > 0)
                {
                    int LocalNumOfRowsToDelete = jtableOfItems.getRowCount();

                    for (Integer j = 0; j < LocalNumOfRowsToDelete; j++)
                    {
                        try
                        {
                            LocalTableModel.removeRow(0);

                        }

                        catch (Exception e)
                        {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null , "there is a problem");
                        }


                    }

                }


                    /*Get the invoice for the selected row*/
                    InvoiceHeader LocalCurrentInvoice = GlobalInvoiceHeadersList.get(LocalCurrentSelectedRow);
                    InvoiceMoney LocalCurrentInvoiceMoney = GlobalInvoiceMoneyList.get(LocalCurrentSelectedRow);

                    invoice_number_display.setText(Integer.toString(LocalCurrentInvoice.getInvoiceNum()));
                    invoice_total_display.setText(Integer.toString(LocalCurrentInvoiceMoney.getInvoiceTotal()));

                    date_textField.setText(LocalCurrentInvoice.getInvoiceDate());
                    customer_textField.setText(LocalCurrentInvoice.getCustomerName());


                    /*Update items table with all items in selected invoice*/
                    ArrayList<InvoiceLine> LocalCurrentInvoiceLines = LocalCurrentInvoice.getInvoiceLines();
                    ArrayList<Integer> LocalCurrentItemsTotal = LocalCurrentInvoiceMoney.getItemsTotal();


                    /*Loop over all items in selected invoice*/
                    for (int i = 0; i < LocalCurrentInvoiceLines.size(); i++) {


                        String[] LocalDataToItemTable = new String[4];

                        LocalDataToItemTable[0] = LocalCurrentInvoiceLines.get(i).getItemName();
                        LocalDataToItemTable[1] = Integer.toString(LocalCurrentInvoiceLines.get(i).getItemPrice());
                        LocalDataToItemTable[2] = Integer.toString(LocalCurrentInvoiceLines.get(i).getCount());
                        LocalDataToItemTable[3] = Integer.toString(LocalCurrentItemsTotal.get(i));

                        /*Add a new item to item table*/
                        LocalTableModel.addRow(LocalDataToItemTable);

                    }







                /*Prev selected row = current selected row*/
                GlobalPrevSelectedRow = LocalCurrentSelectedRow;

            }




        }

    }


    /*Adds a new invoice with empty data*/
    public void onCreateInvoiceButtonPressed()
    {



        /*Add an empty invoice to invoice list*/
        int LocalCurrentNumOfInvoices = GlobalInvoiceHeadersList.size();//number of current invoices before creating new invoice

        InvoiceHeader LocalNewInvoice = new InvoiceHeader();
        LocalNewInvoice.setInvoiceDate("");
        LocalNewInvoice.setInvoiceNum(LocalCurrentNumOfInvoices + 1);
        LocalNewInvoice.setCustomerName("");
        GlobalInvoiceHeadersList.add(LocalNewInvoice);

        /*Add an empty InvoiceMoney to list*/
        InvoiceMoney LocalNewInvoiceMoney = new InvoiceMoney();
        LocalNewInvoiceMoney.setInvoiceTotal(0);
        GlobalInvoiceMoneyList.add(LocalNewInvoiceMoney);


        /*Add a row with empty data to invoice table*/
        DefaultTableModel LocalTableModel = (DefaultTableModel) jtableOfInvoices.getModel();

        String []LocalNewRowData = new String[4];

        LocalNewRowData[0] = Integer.toString(LocalNewInvoice.getInvoiceNum());
        LocalNewRowData[1] = "";
        LocalNewRowData[2] = "";
        LocalNewRowData[3] = Integer.toString(LocalNewInvoiceMoney.getInvoiceTotal());


        LocalTableModel.addRow(LocalNewRowData);

    }


    public void CreateTest()

    {

        System.out.println("From create test");

    }

    public void DeleteTest()
    {


        System.out.println("From delete test");

    }


    /*Method to be called when delete invoice button is pressed*/
    public void onDeleteButtonPressed()
    {

        int LocalNumOfRowsSelected = jtableOfInvoices.getSelectedRowCount();

        /*One row only selected for delete*/
        if(LocalNumOfRowsSelected == 1)
        {


            int LocalIndexToRemove = jtableOfInvoices.getSelectedRow();

            /*Remove Selected invoice from invoice list*/

            GlobalInvoiceHeadersList.remove(LocalIndexToRemove);


            /*Remove Selected invoice money from invoice money list*/

            GlobalInvoiceMoneyList.remove(LocalIndexToRemove);


            DefaultTableModel LocalTabelModel = (DefaultTableModel) jtableOfInvoices.getModel();

            LocalTabelModel.removeRow(LocalIndexToRemove);

        }

        else if(LocalNumOfRowsSelected == 0)
        {


            JOptionPane.showMessageDialog(null , "Please select an Invoice to delete");


        }

        else
        {

            JOptionPane.showMessageDialog(null , "Please select Only one Invoice ");

        }








    }


    /*Method to be called when Add item button is pressed by user*/
    public void onDeleteItemButtonPressed()
    {

        DefaultTableModel LocalItemsTabelModel = (DefaultTableModel) jtableOfItems.getModel();

        int LocalNumOfSelectedRows = jtableOfItems.getSelectedRowCount();

        /*Only one row selected??*/
        if(LocalNumOfSelectedRows == 1)
        {
            /*Delete selected row*/
            int LocalCurrentSelectedRow = jtableOfItems.getSelectedRow();
            LocalItemsTabelModel.removeRow(LocalCurrentSelectedRow);

        }

        /*No rows selected*/
        else if(LocalNumOfSelectedRows == 0)
        {

            JOptionPane.showMessageDialog(null , "Delete item failed please select an Item");

        }

        /*More than one row selected*/
        else
        {
            JOptionPane.showMessageDialog(null , "Delete item failed please choose one item only");

        }



    }


    public void onAddItemButtonPressed()
    {

        DefaultTableModel LocalItemsTabelModel = (DefaultTableModel) jtableOfItems.getModel();


        String[] empty = {"" , "" , "" , ""};
        LocalItemsTabelModel.addRow(empty);


    }



    public ArrayList<InvoiceLine> getChangedInvoiceItems()
    {
        /*Array list to be returned by method*/
        ArrayList<InvoiceLine> toReturnList = new ArrayList<>();


        int LocalNumOfItems = jtableOfItems.getRowCount();

        /*Loop over all items in Items table*/
        for(int i = 0; i < LocalNumOfItems; i++)
        {

            InvoiceLine LocalCurrentInvoiceLine = new InvoiceLine();

            String LocalCurrentItemName = (String) jtableOfItems.getValueAt(i , 0);
            String LocalCurrentItemPrice = (String)jtableOfItems.getValueAt(i , 1);
            String LocalCurrentItemCount = (String)jtableOfItems.getValueAt(i , 2);
            //String LocalCurrentItemTotal = (String) jtableOfItems.getValueAt(i , 3);

            try
            {

                /*If any data is missing for an Invoice Item*/
                if (LocalCurrentItemName.isEmpty() || LocalCurrentItemCount.isEmpty() || LocalCurrentItemPrice.isEmpty())
                {
                    JOptionPane.showMessageDialog(null , "Please provide all info about all items");
                    return null;
                }

                else
                {

                    /*Add all collected data to a new Invoice line*/
                    LocalCurrentInvoiceLine.setItemName(LocalCurrentItemName);
                    LocalCurrentInvoiceLine.setItemPrice(Integer.parseInt(LocalCurrentItemPrice));
                    LocalCurrentInvoiceLine.setCount(Integer.parseInt(LocalCurrentItemCount));


                }

            }
            catch (Exception e)
            {


                JOptionPane.showMessageDialog(null , "Provide correct data format");
                return null;
            }



            /*Add collected Invoice line to list*/
            toReturnList.add(LocalCurrentInvoiceLine);



        }

        return toReturnList;


    }


    public InvoiceMoney getChangedInvoiceMoney()
    {

        /*Create Invoice money variable to be returned*/
        InvoiceMoney moneyToReturn = new InvoiceMoney();

        ArrayList<Integer> LocalTotalPerItemList = new ArrayList<>();


        int LocalTotalPerInvoice = 0;

        int LocalNumOfItemRows = jtableOfItems.getRowCount();




        for(int i = 0; i < LocalNumOfItemRows; i++)
        {
            /*Get total of current item from item table*/
            String LocalCurrentItemTotal = (String) jtableOfItems.getValueAt(i , 3);

            /*If nothing is there in total column */
            if(LocalCurrentItemTotal.isEmpty())
            {
                JOptionPane.showMessageDialog(null , "Please fill all Total Column for items table");

                return null;

            }

            else
            {
                Integer IntLocalCurrentItemTotal = Integer.parseInt(LocalCurrentItemTotal);

                LocalTotalPerInvoice += IntLocalCurrentItemTotal;

                LocalTotalPerItemList.add(IntLocalCurrentItemTotal);

            }




        }

        moneyToReturn.setInvoiceTotal(LocalTotalPerInvoice);

        moneyToReturn.setItemsTotal(LocalTotalPerItemList);


        /*Return invoice money*/
        return moneyToReturn;
    }




    /*Method to run on a save button pressed*/
    public void onSaveButtonPressed()
    {

        /*Get number of selected rows from Invoice table*/
        int LocalNumOfSelectedRows = jtableOfInvoices.getSelectedRowCount();
        String LocalCustomerFromUser = new String();
        String LocalDateFromUser = new String();



        if(LocalNumOfSelectedRows == 1)
        {

            int LocalCurrentSelectedRow = jtableOfInvoices.getSelectedRow();

            /*Get All invoice items and add then to a List of Invoice items*/

            ArrayList<InvoiceLine> test = getChangedInvoiceItems();

            /*Get invoice total and all invoice items total*/
            InvoiceMoney moneyTets = getChangedInvoiceMoney();

            /*Add all invoice data(Invoice Date , Customer name , Total , List of Invoice items)*/

            /***********Get Invoice data provided by user**********************/

            if(test != null && moneyTets != null) {

                /*If any info missing*/
                if (date_textField.getText().isEmpty() || customer_textField.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Please provide Customer name and Invoice date");
                    return;
                } else {

                    LocalCustomerFromUser = customer_textField.getText();
                    LocalDateFromUser = date_textField.getText();

                }


                InvoiceHeader LocalInvoiceUpdated = new InvoiceHeader();

                /*Save user inputs to Update invoice*/
                LocalInvoiceUpdated.setCustomerName(LocalCustomerFromUser);
                LocalInvoiceUpdated.setInvoiceDate(LocalDateFromUser);
                LocalInvoiceUpdated.setInvoiceLines(test);
                LocalInvoiceUpdated.setInvoiceNum(LocalCurrentSelectedRow + 1);

                /*Update Global InvoiceHeadersList and Global InvoiceMoneyList */
                GlobalInvoiceHeadersList.set(LocalCurrentSelectedRow, LocalInvoiceUpdated);
                GlobalInvoiceMoneyList.set(LocalCurrentSelectedRow, moneyTets);


                /*Modify Invoice table with updates*/
                jtableOfInvoices.setValueAt(LocalDateFromUser, LocalCurrentSelectedRow, 1);
                jtableOfInvoices.setValueAt(LocalCustomerFromUser, LocalCurrentSelectedRow, 2);
                jtableOfInvoices.setValueAt(Integer.toString(moneyTets.getInvoiceTotal()), LocalCurrentSelectedRow, 3);

                invoice_total_display.setText(Integer.toString(moneyTets.getInvoiceTotal()));

            }
        }





        /*User did not select a row??*/
        else if(LocalNumOfSelectedRows == 0)
        {

            JOptionPane.showMessageDialog(null , "Please select a Row to update");

        }

        /*User selected more than one row*/
        else
        {

            JOptionPane.showMessageDialog(null , "Please only select on row for update");
        }


    }

    /*Method to Run when a row from Items table is selected*/
    public void onItemRowSelected()
    {

        int LocalNumOfItemRowSelceted = jtableOfItems.getSelectedRowCount();

        /*Only on row selected??*/
        if(LocalNumOfItemRowSelceted == 1)
        {

            System.out.println("Item row selected");

            /*Get current selected row*/
            int LocalCurrentSelectedRow = jtableOfItems.getSelectedRow();

            /*Get Item price and Item count from Table of Items*/
            String LocalCurrentItemPrice = (String)jtableOfItems.getValueAt(LocalCurrentSelectedRow , 1);
            String LocalCurrentItemCount = (String)jtableOfItems.getValueAt(LocalCurrentSelectedRow , 2);


            if(LocalCurrentItemCount.isEmpty() || LocalCurrentItemPrice.isEmpty())
            {

                /*Do nothing*/
            }


            else
            {
                /*Wrong data format might be given*/
                try
                {
                    int LocalCurrentTotal = Integer.parseInt(LocalCurrentItemCount) * Integer.parseInt(LocalCurrentItemPrice);
                    System.out.println(LocalCurrentTotal);

                    jtableOfItems.setValueAt(Integer.toString(LocalCurrentTotal) , LocalCurrentSelectedRow , 3);


                }

                catch (Exception e)
                {

                    /*Do nothing*/

                }


            }


        }







    }


    public void startApp()
    {
        /*Create objects to call Class functions*/
        App_main m = new App_main();
        FileOperation f = new FileOperation();

        /*Read from CSV file and store to ArrayList*/
        //invoiceHeaders = f.readFile();
        GlobalInvoiceHeadersList = f.readFile();

        GlobalInvoiceMoneyList = m.fillTotalMoney(GlobalInvoiceHeadersList);

        m.Print_layout();


    }


    public void onCancelButtonPressed()
    {


        DefaultTableModel LocalTableModel = (DefaultTableModel)jtableOfItems.getModel();


        /*Get number of rows selected*/
        int LocalNumOfSelectedRows = jtableOfInvoices.getSelectedRowCount();

        /*Only 1 row selected??*/
        if(LocalNumOfSelectedRows == 1)
        {
            /*Get currently selected row*/
            int LocalCurrentSelectedRow = jtableOfInvoices.getSelectedRow();
            //JOptionPane.showMessageDialog(null , "Row added:" + LocalCurrentSelectedRow );



                /*Delete all rows in invoice Items table*/
                if (jtableOfItems.getRowCount() > 0)
                {
                    int LocalNumOfRowsToDelete = jtableOfItems.getRowCount();

                    for (Integer j = 0; j < LocalNumOfRowsToDelete; j++)
                    {
                        try
                        {
                            LocalTableModel.removeRow(0);

                        }

                        catch (Exception e)
                        {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null , "there is a problem");
                        }


                    }

                }


                /*Get the invoice for the selected row*/
                InvoiceHeader LocalCurrentInvoice = GlobalInvoiceHeadersList.get(LocalCurrentSelectedRow);
                InvoiceMoney LocalCurrentInvoiceMoney = GlobalInvoiceMoneyList.get(LocalCurrentSelectedRow);

                invoice_number_display.setText(Integer.toString(LocalCurrentInvoice.getInvoiceNum()));
                invoice_total_display.setText(Integer.toString(LocalCurrentInvoiceMoney.getInvoiceTotal()));

                date_textField.setText(LocalCurrentInvoice.getInvoiceDate());
                customer_textField.setText(LocalCurrentInvoice.getCustomerName());


                /*Update items table with all items in selected invoice*/
                ArrayList<InvoiceLine> LocalCurrentInvoiceLines = LocalCurrentInvoice.getInvoiceLines();
                ArrayList<Integer> LocalCurrentItemsTotal = LocalCurrentInvoiceMoney.getItemsTotal();


                /*Loop over all items in selected invoice*/
                for (int i = 0; i < LocalCurrentInvoiceLines.size(); i++) {


                    String[] LocalDataToItemTable = new String[4];

                    LocalDataToItemTable[0] = LocalCurrentInvoiceLines.get(i).getItemName();
                    LocalDataToItemTable[1] = Integer.toString(LocalCurrentInvoiceLines.get(i).getItemPrice());
                    LocalDataToItemTable[2] = Integer.toString(LocalCurrentInvoiceLines.get(i).getCount());
                    LocalDataToItemTable[3] = Integer.toString(LocalCurrentItemsTotal.get(i));

                    /*Add a new item to item table*/
                    LocalTableModel.addRow(LocalDataToItemTable);

                }


        }



    }

    public void onLoadFileSelected()
    {
        App_main m = new App_main();


        GlobalMainFrame.dispose();

        /*Restart app*/
        m.main(new String[] {"arg1", "arg2", "arg3"});


    }

    public void onSaveFileSelected()
    {

        FileOperation f = new FileOperation();

        f.writeFile(GlobalInvoiceHeadersList);


    }



}
