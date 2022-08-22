package View;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame
{

    private JComponent[] MyFrame_components;
    public MyFrame()
    {
        super("My Frame");



    }

    public MyFrame(String FrameName)
    {

        super(FrameName);


    }

    public MyFrame(String FrameName , int width , int height , int loc_x ,
                   int loc_y , JComponent[] components)
    {
        /*Set frame name*/
        this(FrameName);

        /*Layout manager to manage added components*/
        setLayout(null);

        /*Add all passed components to MyFrame*/
        for(int i = 0 ; i < components.length; i++)
        {

            add(components[i]);


        }


        /*Set size*/
        setSize(width , height);

        /*Set location*/
        setLocation(loc_x , loc_y);

        /*Terminate application on frame close*/
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }






}
