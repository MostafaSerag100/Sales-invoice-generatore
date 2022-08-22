package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyListener implements ActionListener
{

    private Runnable[] methods;

    public MyListener(Runnable []toRun)
    {

        methods = new Runnable[toRun.length];


        for(int i = 0; i < toRun.length; i++)
        {



         methods[i] = toRun[i];


        }

    }



    @Override
    public void actionPerformed(ActionEvent e)
    {


        /*Code to handle event*/


        //e.getSource().equals(btn);//True if event from button b1 else False



        /*Giving a different action to each button*/

        /*Action command name b1?*/
        if(e.getActionCommand().equals("Create Invoice"))
        {




            methods[0].run();



        }

        /*Action command named b2?*/
        else if(e.getActionCommand().equals("Delete Invoice"))
        {



            methods[1].run();




        }

        else if(e.getActionCommand().equals("Delete Item"))
        {


            methods[2].run();
            System.out.println("item deleted");

        }

        else if(e.getActionCommand().equals("Add Item"))
        {

            methods[3].run();


        }

        else if(e.getActionCommand().equals("Save"))
        {


            methods[4].run();

        }

        else if(e.getActionCommand().equals("Cancel"))
        {

            methods[5].run();
        }

        else if(e.getActionCommand().equals("Save File"))
        {

            methods[6].run();

        }

        else if(e.getActionCommand().equals("Load File"))
        {


            methods[7].run();

        }


    }


}
