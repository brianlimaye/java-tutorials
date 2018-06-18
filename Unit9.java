import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class Unit9
{

   public static void main(String[] args)
   {
      JLabel label = new JLabel("Press Me..");
      JPanel panel= new JPanel();
      panel.setLayout(new BorderLayout());
      panel.add(label, BorderLayout.NORTH);
      
      JButton button= new JButton("Go!");
      button.addActionListener(new MyActionListener());
      panel.add(button, BorderLayout.CENTER);
      
      JFrame frame= new JFrame();
      frame.add(panel);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
      
      
   
   }
   private static class MyActionListener implements ActionListener
   {
   
      public void actionPerformed(ActionEvent e)
      {
      
      System.out.println("Pressed");
      
      }
   
   }

}