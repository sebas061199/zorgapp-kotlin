import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;


public class Patients
{
   private final ArrayList<Patient> patients = new ArrayList<>();

   //////////////////////////////////////////////////////////////////////////////////////////
   //////////////////////////////////////////////////////////////////////////////////////////
   public Patients()
   {
   }

   //////////////////////////////////////////////////////////////////////////////////////////
   // Ad hoc contructor: when given a medicin list, create hard-coded list of patients.
   //////////////////////////////////////////////////////////////////////////////////////////
   public Patients( Medicins medicins )
   {
      var p = new Patient( freeId(), "Van Puffelen", "Adriaan", LocalDate.of( 2000, 02, 29 ) );
      p.addMedicin( medicins.getMedicin( 1 ) );  // TODO: no hardcoded encoding of medicins:
      p.addMedicin( medicins.getMedicin( 3 ) );
      p.addMedicin( medicins.getMedicin( 5 ) );
      p.addMedicin( medicins.getMedicin( 7 ) );
      p.addMedicin( medicins.getMedicin( 9 ) );
      p.addWeightMeasurement( 75.0, LocalDate.of( 2000, Month.APRIL, 1 ) );
      p.addWeightMeasurement( 75.3, LocalDate.of( 2000, Month.APRIL, 27 ) );
      p.addWeightMeasurement( 75.9, LocalDate.of( 2000, Month.MAY, 7 ) );
      p.addWeightMeasurement( 76.3, LocalDate.of( 2000, Month.MAY, 27 ) );
      p.addWeightMeasurement( 77.3, LocalDate.of( 2000, Month.JUNE, 14 ) );
      p.addWeightMeasurement( 76.3, LocalDate.of( 2000, Month.JUNE, 27 ) );
      p.addWeightMeasurement( 75.3, LocalDate.of( 2000, Month.AUGUST, 27 ) );
      p.addWeightMeasurement( 73.3, LocalDate.of( 2000, Month.NOVEMBER, 27 ) );
      patients.add( p );

      patients.add( new Patient( freeId(), "Bruggen", "Karin", LocalDate.of( 1970, 1, 1 ), 64.2, 1.68 ) );

      p = new Patient( freeId(), "Klinkhamer", "Hielke", LocalDate.of( 1980, 12, 31 ), 74.2, 1.77 );
      p.addWeightMeasurement( 60.0, LocalDate.of( 2004, Month.FEBRUARY, 29 ) );
      p.addWeightMeasurement( 61.0, LocalDate.of( 2005, Month.FEBRUARY, 28 ) );
      p.addWeightMeasurement( 62.0, LocalDate.of( 2006, Month.FEBRUARY, 28 ) );
      p.addWeightMeasurement( 63.0, LocalDate.of( 2007, Month.FEBRUARY, 28 ) );
      p.addWeightMeasurement( 64.0, LocalDate.of( 2008, Month.FEBRUARY, 29 ) );
      p.addWeightMeasurement( 63.0, LocalDate.of( 2009, Month.FEBRUARY, 28 ) );
      p.addWeightMeasurement( 62.0, LocalDate.of( 2010, Month.FEBRUARY, 28 ) );
      p.addWeightMeasurement( 61.0, LocalDate.of( 2011, Month.FEBRUARY, 28 ) );
      p.addWeightMeasurement( 62.0, LocalDate.of( 2012, Month.FEBRUARY, 29 ) );
      p.addWeightMeasurement( 63.0, LocalDate.of( 2013, Month.FEBRUARY, 28 ) );
      p.addWeightMeasurement( 64.0, LocalDate.of( 2013, Month.FEBRUARY, 28 ) );
      patients.add( p );

      p = new Patient( freeId(), "Klinkhamer", "Sietse", LocalDate.of( 1980, 12, 31 ), 74.5, 1.78 );
      p.addMedicin( medicins.getMedicin( 2 ) );  // TODO: no hardcoded encoding of medicins:
      p.addMedicin( medicins.getMedicin( 4 ) );
      p.addMedicin( medicins.getMedicin( 6 ) );
      p.addMedicin( medicins.getMedicin( 8 ) );
      patients.add( p );

      patients.add( new Patient( freeId(), "Kaak", "Maria", LocalDate.of( 2000, 6, 25 ), 68.2, 1.65 ) );

      p = new Patient( freeId(), "de Lange", "Kortjakje", LocalDate.of( 2012, 7, 1 ), 68.2, 1.65 );
      p.addMedicin( medicins.getMedicin( 2 ), "onbekend" );
      p.addMedicin( medicins.getMedicin( 3 ), "1/dag" );
      p.addWeightMeasurement( 45.0, LocalDate.of( 2000, Month.APRIL, 1 ) );
      p.addWeightMeasurement( 45.3, LocalDate.of( 2000, Month.APRIL, 27 ) );
      p.addWeightMeasurement( 45.9, LocalDate.of( 2000, Month.MAY, 7 ) );
      p.addWeightMeasurement( 46.3, LocalDate.of( 2000, Month.MAY, 27 ) );
      p.addWeightMeasurement( 47.3, LocalDate.of( 2000, Month.JUNE, 14 ) );
      p.addWeightMeasurement( 46.3, LocalDate.of( 2000, Month.JUNE, 27 ) );
      p.addWeightMeasurement( 45.3, LocalDate.of( 2000, Month.AUGUST, 27 ) );
      p.addWeightMeasurement( 53.3, LocalDate.of( 2001, Month.JANUARY, 27 ) );
      patients.add( p );

      patients.add( new Patient( freeId(), "Stroorum", "Karin", LocalDate.of( 2012, 12, 12 ), 44.2, 1.50 ) );
      patients.add( new Patient( freeId(), "Pie", "Willem", LocalDate.of( 1956, 11, 21 ), 80.0, 1.86 ) );
      patients.add( new Patient( freeId(), "Bakkebaard", "Opa", LocalDate.of( 1900, 01, 01 ), 44.2, 1.50 ) );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   int numPatients()
   {
      return patients.size();
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   int freeId()
   {
      return patients.size() + 1;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   boolean isValidId( int id )
   {
      return (id > 0) && (id <= patients.size());
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   int addPatient( Patient p )
   {
      patients.add( p );
      return patients.size();
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   Patient getPatient( int id )
   {
      return isValidId( id ) ? patients.get( id - 1 ) : null;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   void writeOneliners()
   {
      int id = 0;
      for (var p : patients)
      {
         System.out.format( "[%d] ", ++id );
         p.writeOneliner();
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Alternative for serialising.
   /// Do not write a single Patients object. Instead, write a list of
   /// individual profiles, one per line. This makes it not only easier to read,
   /// but it facilitates manual editing of the file.
   ////////////////////////////////////////////////////////////////////////////////
   public void save( String filename )
   {
      try
      {
         PrintWriter writer = new PrintWriter( filename, StandardCharsets.UTF_8 );

         String s;
         for (Patient p : patients)
         {
            s = p.toJSON().toString();
            writer.println( s );
         }

         writer.flush();
         writer.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Load from disk. Data must have have saved by the save routine in this class.
   ////////////////////////////////////////////////////////////////////////////////
   public void load( File file )
   {
      try
      {
         InputStream is      = new FileInputStream( file );
         JSONTokener tokener = new JSONTokener( is );

         patients.clear();

         // Read all profiles, only stopped by a JSONException, typically
         // here (and assumed to be...) end-of-information.
         while (true)
         {
            try
            {
               JSONObject object = new JSONObject( tokener );
               Patient    p      = new Patient( object );
               patients.add( p );
            }
            catch (org.json.JSONException e)
            {
               //System.out.println( "end-of-input" );
               break;
            }
            catch (Exception e)
            {
               e.printStackTrace();
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

}
