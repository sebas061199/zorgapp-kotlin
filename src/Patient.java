import java.util.Scanner;

public class Patient
{
   private final int RETURN    = 0;
   private final int SURNAME   = 1;
   private final int FIRSTNAME = 2;
   private final int CALLNAME  = 3;

   private String woonplaats;

   private String surName;
   private String firstName;
   private String callName;

   // Constructor
   Patient( String surName, String firstName )
   {
      this.surName   = surName;
      this.firstName = firstName;

      this.callName = firstName; // rest via separate method if needed.
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
   public String getCallName()
   {
      return callName;
   }

   void editMenu( boolean zv )
   {
      Scanner scanner1 = new Scanner( System.in ); // use for integers.
      Scanner scanner2 = new Scanner( System.in ); // use for strings

      int choice = -1;
      while (choice != 0)
      {
         write();
         System.out.println( "Enter digit (0=return)" );
         choice = scanner1.nextInt();
         switch (choice)
         {
            case RETURN:
               break;

            case SURNAME:
               System.out.println( "Enter new surname:" );
               surName = scanner2.nextLine();
               break;

            case FIRSTNAME:
               System.out.println( "Enter new first name:" );
               firstName = scanner2.nextLine();
               break;

            case CALLNAME:
               System.out.println( "Enter new call name:" );
               callName = scanner2.nextLine();
               break;
         }
      }
   }

   // Write data to screen.
   void write()
   {
      System.out.println( SURNAME + " - Surname:    " + surName );
      System.out.println( FIRSTNAME + " - First name: " + firstName );
      System.out.println( CALLNAME + " - Call name:  " + callName );
   }
}
