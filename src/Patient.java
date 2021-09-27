import java.util.Scanner;

public class Patient
{
   private final int RETURN    = 0;
   private final int SURNAME   = 1;
   private final int FIRSTNAME = 2;
   private final int NICKNAME  = 3;

   private String surName;
   private String firstName;
   private String nickName;
   private String woonplaats;

   // Constructor
   Patient( String surName, String firstName )
   {
      this.surName   = surName;
      this.firstName = firstName;
      this.nickName  = firstName; // rest via separate method if needed.
   }

   public String getWoonplaats()
   {
      return woonplaats;
   }

   // Access surName
   public String getSurName()
   {
      return surName;
   }

   // Access surName
   public String getFirstName()
   {
      return firstName;
   }

   // Access callName
   public String getNickName()
   {
      return nickName;
   }

   void editMenu( boolean zv )
   {
      Scanner scanner1 = new Scanner( System.in ); // use for integers.
      Scanner scanner2 = new Scanner( System.in ); // use for strings

      boolean nextCycle = true;
      while (nextCycle)
      {
         System.out.println( "Patient data edit menu: enter digit (0=return)" );
         printEditMenuOptions( zv );

         int choice = scanner1.nextInt();
         switch (choice)
         {
            case RETURN:
               nextCycle = false;
               break;

            case SURNAME:
               if (zv)
               {
                  System.out.println( "Enter new surname:" );
                  surName = scanner2.nextLine();
               }
               break;

            case FIRSTNAME:
               if (zv)
               {
                  System.out.println( "Enter new first name:" );
                  firstName = scanner2.nextLine();
               }
               break;

            case NICKNAME:
               System.out.println( "Enter new nickname:" );
               nickName = scanner2.nextLine();
               break;

            default:
               System.out.println( "Invalid entry: " + choice );
               break;
         }
      }
   }

   // Write patient data to screen.
   void printEditMenuOptions( boolean zv )
   {
      if (zv)
      {
         System.out.println( SURNAME + " - Surname:    " + surName );
         System.out.println( FIRSTNAME + " - First name: " + firstName );
      }
      System.out.println( NICKNAME + " - Nickname:   " + nickName );
   }

   // Write patient data to screen.
   void write()
   {
      System.out.println( "===================================");
      System.out.println( "Surname:    " + surName );
      System.out.println( "First name: " + firstName );
      System.out.println( "Nickname:   " + nickName );
      System.out.println( "===================================");
   }
}
