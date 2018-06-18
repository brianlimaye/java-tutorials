public class Unit6
{
  public static void main(String[] args) throws Throwable 
   {
     String[][] colors= {
      {"Bahar", "Red"},
      {"Brian", "Blue"},
      {"Ayanna", "Lavender"},
      {"Bozo", "Green", "Red" },
     };
      
     for(int x=0; x<colors.length; x++)
     {
         String[] row = colors[x];
         int numberOfColumns = row.length;
         if (numberOfColumns == 2)
         
          {
            System.out.print(colors[x][0] + " favorite color is: " + colors[x][1]);
          }
    
         if(numberOfColumns == 3)
         {
            System.out.print(" Optional Color is: " + row[2]);
         }
         System.out.println("");
     }
    
     
     
   }

}