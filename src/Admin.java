import java.time.LocalDate;
import java.util.Scanner;

public class Admin
{
   Patient patient;
   int     userID = -1;
   boolean zv;

   // Constructor
   Admin( int userID )
   {
      this.userID = userID;
      zv          = (userID == 0);
      patient     = new Patient( "Van Puffelen", "Adriaan", LocalDate.of( 2000, 02, 29 ) );
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
         System.out.println( "Admin menu: enter digit:" );
         System.out.println( STOP + ":  STOP" );
         System.out.println( PRINT + ":  Print patient data" );
         System.out.println( EDIT + ":  Edit  patient data" );

         choice = scanner.nextInt();

         switch (choice)
         {
            case STOP: // do nothing
               break;

            case PRINT:
               patient.write();
               break;

            case EDIT:
               patient.editMenu( zv );
               break;

            default:
               System.out.println( "Please enter a *valid* digit" );
               break;
         }
      }
   }
}
