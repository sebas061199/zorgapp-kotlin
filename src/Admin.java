import java.util.Scanner;

public class Admin
{
   Patient patient;
   int     userID;

   // Constructor
   Admin( int userID )
   {
      this.userID = userID;
      patient     = new Patient( "Van Puffelen", "Adriaan" );
   }

   // Main menu
   void menu()
   {
      final int STOP  = 0;
      final int PRINT = 1;
      final int EDIT  = 2;

      Scanner scanner = new Scanner( System.in );

      int choice = 1;
      while (choice != 0)
      {
         System.out.println( "Enter digit: " );
         System.out.println( STOP + ":  STOP" );
         System.out.println( PRINT + ":  Print patient data" );
         System.out.println( EDIT + ":  Edit  patient data" );

         choice = scanner.nextInt();

         switch (choice)
         {
            case STOP: // do nothing
               break;

            case PRINT:
               System.out.println( "Print data..." );
               patient.write();
               break;

            case EDIT:
               System.out.println( "Edit data..." );
               patient.editMenu( true );
               break;

            default:
               System.out.println( "Please enter a *valid* digit" );
               break;
         }
      }
   }
}
