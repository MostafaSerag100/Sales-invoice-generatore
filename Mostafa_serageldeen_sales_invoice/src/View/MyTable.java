package View;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MyTable extends JTable
{


    ListSelectionModel sel_model;
    private Runnable toRun;


    public MyTable(String[][]data , String []header , boolean isEdible)

    {

        super( new DefaultTableModel(data , header)
        {
            @Override

            public boolean isCellEditable(int row, int column)
            {
                if(isEdible == false)
                {
                    return false;
                }

                else
                {
                    if(column == 3)
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                    }

            }

        }
);




        sel_model = getSelectionModel();


        sel_model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e)
            {
                int selected_row = sel_model.getMinSelectionIndex();

                /*To avoid null pointer Exception in case no Runnable was specified*/
                if(toRun != null)
                {
                    toRun.run();
                }

            }
        });



    }

    /*User can specify a function to run on a raw selection event*/
    public void onRawSelection(Runnable method)
    {

        toRun = method;


    }















}
