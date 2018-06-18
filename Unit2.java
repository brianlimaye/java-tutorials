public class Unit2
{
   public static void main(String[] args)
   {
   
      //Print every Even character
      String brian = "Brian is cool";
      for(int x=0; x<brian.length(); x++)
        {
            if((x%2)==0)
          {
            System.out.print("" + brian.charAt(x));
          }
      
        }
   }
}