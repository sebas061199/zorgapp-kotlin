import java.time.LocalDate;
import java.util.Scanner;

public class Admin
{
   Patients patients = new Patients(); // The list of all patients
   Patient  patient;                   // The currently selected patient
   Medicins medicins = new Medicins( true );
   boolean  zv;                        // true when 'zorgverlener'; false otherwise

   // Constructor
   Admin( int userID )
   {
      zv = (userID == 0);

      // Create patient profiles
      {
         var p = new Patient( patients.freeId(), "Van Puffelen", "Adriaan", LocalDate.of( 2000, 02, 29 ) );
         p.addMedicin( medicins.getMedicin( 1 ) );
         p.addMedicin( medicins.getMedicin( 3 ) );
         p.addMedicin( medicins.getMedicin( 5 ) );
         p.addMedicin( medicins.getMedicin( 7 ) );
         p.addMedicin( medicins.getMedicin( 9 ) );
         patients.addPatient( p );
      }
      patients.addPatient( new Patient( patients.freeId(), "Bruggen", "Karin", LocalDate.of( 1970, 1, 1 ), 64.2, 1.68 ) );
      patients.addPatient( new Patient( patients.freeId(), "Klinkhamer", "Hielke", LocalDate.of( 1980, 12, 31 ), 74.2, 1.77 ) );
      patients.addPatient( new Patient( patients.freeId(), "Klinkhamer", "Sietse", LocalDate.of( 1980, 12, 31 ), 74.5, 1.78 ) );
      patients.addPatient( new Patient( patients.freeId(), "Kaak", "Maria", LocalDate.of( 2000, 6, 25 ), 68.2, 1.65 ) );

      {
         var p = new Patient( patients.freeId(), "de Lange", "Kortjakje", LocalDate.of( 2012, 7, 1 ), 68.2, 1.65 );
         p.addMedicin( medicins.getMedicin( 2 ), "onbekend" );
         p.addMedicin( medicins.getMedicin( 3 ), "1/dag" );
         patients.addPatient( p );
      }

      patients.addPatient( new Patient( patients.freeId(), "Stroorum", "Karin", LocalDate.of( 2012, 12, 12 ), 44.2, 1.50 ) );
      patients.addPatient( new Patient( patients.freeId(), "Pie", "Willem", LocalDate.of( 1956, 11, 21 ), 80.0, 1.86 ) );
      patients.addPatient( new Patient( patients.freeId(), "Bakkebaard", "Opa", LocalDate.of( 1900, 01, 01 ), 44.2, 1.50 ) );

      patient = patients.getPatient( zv ? 1 : userID ); // todo: make robust
   }

   // Write a text menu to select a new patient.
   // Returns the new patient or, on error, the current one.
   Patient selectPatientMenu()
   {
      patients.writeOneliners();
      System.out.println( "enter Patient ID:" );
      Scanner scanner = new Scanner( System.in );

      int id = scanner.nextInt();
      return patients.isValidId( id ) ? patients.getPatient( id ) : patient;
   }

   // Main menu
   void menu()
   {
      final int STOP   = 0;
      final int SELECT = 1;
      final int PRINT  = 2;
      final int EDIT   = 3;

      Scanner scanner = new Scanner( System.in );

      int choice = 1;
      while (choice != 0)
      {
         if (zv)
         {
            System.out.format( "Current patient: " );
            patient.writeOneliner();
         }

         System.out.format( "%d:  STOP\n", STOP );
         if (zv)
         {
            System.out.format( "%d:  Select Patient\n", SELECT );
         }
         System.out.format( "%d:  Print patient data\n", PRINT );
         System.out.format( "%d:  Edit  patient data\n", EDIT );

         System.out.println( "enter digit:" );
         choice = scanner.nextInt();
         switch (choice)
         {
            case STOP: // do nothing
               break;

            case SELECT: // select patient (zv only)
               if (zv)
               {
                  patient = selectPatientMenu();
               }
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
