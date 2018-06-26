public class Unit10
{
private String name;

      // overloaded default constructor
      public Unit10()
      {
      
         this("Unit10");
      
      }
      
      // overloaded constructor with string paramater
      public Unit10(String name)
      {
      
         this.name = name;
       
       
      }
      
      // overridden from Object
      public String toString()
      {
      
      return name;
  
   
      }
      
      // overridden from Object
      public boolean equals(Object otherObject)
      {
         if (otherObject instanceof Unit10)
         {
            Unit10 otherUnit = (Unit10) otherObject;
            if (otherUnit.name.equals(name))
            {
               return true;
            }
         }
         return false;
      }
      

   public static void main(String[] args)
   {
   
    Unit10 app1= new Unit10("brian");
    
    Unit10 app2 = new Unit10("BAHAR");
    
    System.out.println(app1);  
    
    System.out.println("Unit10 Objects Equal? " + app1.equals(app2));
    
    String s = "brian";
    String s1 = "brian";
    
    System.out.println("Strings Equals? " + s.equals(s1));
        

int i=10;
int j =10;

System.out.println(i==j);
   
   }

}