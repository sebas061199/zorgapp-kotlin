import java.io.File;

public class Admin
{
   static final String fname_medicins = "medicins.json";
   static final String fname_patients = "patients.json";
   static Medicins medicins; // The list of all medicins

   Patients patients; // The list of all patients
   Patient  patient;  // The currently selected patient
   boolean  zv;       // true when 'zorgverlener'; false otherwise

   // Constructor
   Admin( int userID )
   {
      // Initialise both list of medicins and list of patients.
      {
         File fmeds = new File( fname_medicins );
         File fprof = new File( fname_patients );

         // If both files exist, use them to load data. Otherwise, go for the hardcoded option.
         if (fmeds.isFile() && fprof.isFile())
         {
            System.out.format( "Both %s and %s exist: loading data from these files!\n", fname_medicins, fname_patients );
            medicins = new Medicins( false );
            medicins.load( fmeds ); // todo: robustness for file access etc.
            patients = new Patients();
            patients.load( fprof ); // todo: robustness for file access etc.
         }
         else
         {
            System.out.format( "Hard-coded profiles and medicins!\n" );
            medicins = new Medicins( true );  // The app needs the total list of avaialble medicins...
            patients = new Patients( medicins ); // .. which is used to initialise the profiles.
         }
      }

      zv      = (userID == 0);
      patient = patients.getPatient( zv ? 1 : userID ); // todo: make robust
   }

   // Write a text menu to select a new patient.
   // Returns the new patient or, on error, the current one.
   Patient selectPatientMenu()
   {
      patients.writeOneliners();
      System.out.println( "enter Patient ID:" );
      var scanner = new BScanner();

      int id = scanner.scanInt();
      return patients.isValidId( id ) ? patients.getPatient( id ) : patient;
   }

   // Main menu
   void menu()
   {
      final int STOP   = 0;
      final int SELECT = 1;
      final int PRINT  = 2;
      final int EDIT   = 3;
      final int PLOTW  = 4;

      var scanner = new BScanner();

      int choice = 1;
      while (choice != 0)
      {
         if (zv)
         {
            System.out.format( "%s\n", "=".repeat( 80 ) );
            System.out.format( "Current patient: " );
            patient.writeOneliner();
         }

         ////////////////////////
         // Print menu on screen
         ////////////////////////
         System.out.format( "%d:  STOP\n", STOP );
         if (zv)
         {
            System.out.format( "%d:  Select Patient\n", SELECT );
         }
         System.out.format( "%d:  Print patient data\n", PRINT );
         System.out.format( "%d:  Edit  patient data\n", EDIT );
         System.out.format( "%d:  Plot  patient weights\n", PLOTW );
         ////////////////////////

         System.out.println( "enter digit:" );
         choice = scanner.scanInt();
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

            case PLOTW:
               patient.plotWeights();
               break;

            default:
               System.out.println( "Please enter a *valid* digit" );
               break;
         }
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ///
   ////////////////////////////////////////////////////////////////////////////////
   public void save()
   {
      patients.save( fname_patients );
      medicins.save( fname_medicins );
   }

}
