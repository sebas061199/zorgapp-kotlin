import java.util.Scanner;

public class Admin
{
   Patient patient;

   // Constructor
   Admin()
   {
      patient = new Patient( "Van Puffelen", "Adriaan" );
   }

   // Main menu
   void menu()
   {
      Scanner scanner = new Scanner( System.in );

      int choice = 1;
      while ( choice !=0 )
      {
         System.out.println( "Enter digit: ");
         System.out.println( "0:  STOP");
         System.out.println( "1:  PRINT PATIENT DATA" );

         choice = scanner.nextInt();

         switch (choice)
         {
            case 0: // do nothing
               break;

            case 1: // write data
               patient.write();
               break;

            default:
               System.out.println( "Please enter a valid digit" );
               break;
         }
      }
   }
}
