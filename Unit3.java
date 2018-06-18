public class Unit3
{
   public static void main(String[] args)
    {
    
    //Find the substring "Squirrel ran" and print to the screen
    //If you cannot find it say so
    //Search needs to be case Insensitive
    String data= "It was a nice day. The squirrel ran across the street.";
    String searchpattern= "squirrel Ran";
    int startPos = data.indexOf(searchpattern.toLowerCase());
    System.out.println(startPos);
    if(startPos>=0)
    {
      String s= data.toLowerCase().substring(startPos, startPos + searchpattern.length());
      System.out.print(s);
    }
    else
    {
      System.out.println("Not Found");
    }
    
    
    }
}