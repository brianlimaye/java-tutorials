public class Unit7
{
  public static void main(String[] args) throws Throwable 
   {
     Object[][] data= {
      new String[] {"Bahar", "Red"},
      new String[] { new String("this is just a string") },
      new Integer[] { new Integer(1), new Integer(2), new Integer(3), new Integer(4), new Integer(5)},
      new Float[] {new Float(1.9999), new Float(7.9999), new Float(1.675289)},
      new String[] {"Ayanna", "Lavender"},
      new String[] {"Bozo", "Green", "Red" }
     };
      
     for(int x=0; x<data.length; x++)
     {
         Object row = data[x];
         if ( (row instanceof Integer[]) || (row instanceof Float[]) )
         {
            Number[] numberArray = (Number[]) row;
            System.out.println("Here are the numbers: ");
            for (int j=0; j < numberArray.length; j++)
            {
               System.out.print(numberArray[j].getClass().getName() + " - " + numberArray[j] + " ");
            }
         }
     }
   }

}