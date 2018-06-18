public class Unit8
{

   private static int RED = 0x1;
   private static int BLUE = 0x2;
   private static int ORANGE = 0x4;
   private static int PURPLE = 0x8;

   public static void main (String[] args)
   {
   
       int currentColor = 0;
       
       // let's make the color RED and BLUE
       
       currentColor |= RED;
       currentColor |= BLUE;
       
       System.out.println("Current Color int value is: " + currentColor);
       
       // let's check if the color has red..
       
       if ( (currentColor & RED) == RED )
       {
          System.out.println("RED!");
       }
       
       if ( (currentColor & ORANGE) == ORANGE )
       {
          System.out.println("ORANGE!");
       }
       
       if ( (currentColor & BLUE) == BLUE )
       {
          System.out.println("BLUE!");
       }
       
       currentColor &= ~BLUE;
       
       if ( (currentColor & BLUE) == BLUE )
       {
          System.out.println("BLUE AGAIN!");
       }
       
       

       

       
       
       
   
   
   }


}