import java.util.Scanner;
import java.io.File;
public class Unit4
{
   public static void main(String[] args) throws Throwable
   {
     Scanner s= new Scanner(new File("/Users/brianlimaye/Desktop/ok.txt"));
     System.out.println("Input: ");
     while(s.hasNextLine())
     {
         String line= s.nextLine();
         System.out.println(line);
         switch(line)
         {
            case "quit":
              System.out.println("Bye.");
              System.exit(1);
              break;
         }
            
         //System.out.println("Input: ");
     
     } 
   
   }

}