import java.util.ArrayList;

public class Patients
{
   private ArrayList<Patient> patient = new ArrayList<>();

   int numPatients()
   {
      return patient.size();
   }

   int freeId()
   {
      return patient.size() + 1;
   }

   boolean isValidId( int id )
   {
      return (id > 0) && (id <= patient.size());
   }

   int addPatient( Patient p )
   {
      patient.add( p );
      return patient.size();
   }

   Patient getPatient( int id )
   {
      return isValidId( id ) ? patient.get( id-1 ) : null;
   }

   void writeOneliners()
   {
      int id = 0;
      for (var p : patient)
      {
         System.out.format( "[%d] ", ++id );
         p.writeOneliner();
      }
   }
}
