import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

class Medicins
{
   private final ArrayList<Medicin> medicins = new ArrayList<Medicin>( 0 );

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public Medicins( boolean init )
   {
      if (init)
      {
         medicins.add( new Medicin( "Paracetamol", "Pijnstiller", "", "3xdaags" ) );
         medicins.add( new Medicin( "Asperine", "Pijnstiller", "", "max 3xdaags" ) );
         medicins.add( new Medicin( "Metformine", "bloeddrukremmer", "Verlaagt bloedglucose", "1/week" ) );
         medicins.add( new Medicin( "Janumet", "bloeddrukremmer", "Verlaagt bloedglucose", "1 x daags" ) );
         medicins.add( new Medicin( "Cyaankali", "Pijnstiller", "effectieve oplosmiddel", "1x is genoeg" ) );
         medicins.add( new Medicin( "Adefovir", "Koortsremmer", "", "4 per week" ) );
         medicins.add( new Medicin( "Tenofovir alafenamide", "virusremmer", "HIV/HepatitusB virusremmer", "0" ) );
         medicins.add( new Medicin( "Mogadon", "slaapmiddel", "", "max. 1x etmaal" ) );
         medicins.add( new Medicin( "Notrazepam", "kalmeringsmiddel", "-", "1x per etmaal" ) );
         medicins.add( new Medicin( "acebutolol", "betablokker", "blokkeert alle betas", "1xperdag" ) );
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public Medicins( JSONObject object )
   {
      JSONArray a = object.getJSONArray( "medicins" );
      for (int i = 0; i < a.length(); i++)
      {
         Medicin w = new Medicin( (JSONObject) a.get( i ) );
         medicins.add( w );
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   JSONObject toJSON()
   {
      JSONObject obj = new JSONObject();

      JSONArray ja = new JSONArray();
      for (Medicin m : medicins)
      {
         ja.put( m.toJSON() );
      }
      obj.put( "medicins", ja );

      return obj;
   }


   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   void save( String filename )
   {
      try
      {
         PrintWriter writer = new PrintWriter( filename, StandardCharsets.UTF_8 );

         String s = toJSON().toString();
         writer.println( s );

         writer.flush();
         writer.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Load from disk. Data must have been saved by the save routine in this class.
   ////////////////////////////////////////////////////////////////////////////////
   public void load( File file )
   {
      try
      {
         InputStream is      = new FileInputStream( file );
         JSONTokener tokener = new JSONTokener( is );
         JSONObject  object  = new JSONObject( tokener );

         medicins.clear();
         JSONArray meds = object.getJSONArray( "medicins" );
         for (int i = 0; i < meds.length(); i++)
         {
            Medicin m = new Medicin( (JSONObject) meds.get( i ) );
            medicins.add( m );
         }
      }
      catch (org.json.JSONException e)
      {
         System.out.println( "json error: que?" );
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public int size()
   {
      return medicins.size();
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   private boolean isValidIndex( int index )
   {
      return index >= 0 && index < size();
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Add a new medicin to the list (overloaded)
   /// No test for uniqueness is being done.
   ////////////////////////////////////////////////////////////////////////////////
   public void addMedicin( Medicin m )
   {
      medicins.add( m );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public Medicin getMedicin( int index )
   {
      if (!isValidIndex( index ))
      {
         System.out.format( "ERROR: medicin id must be one of 0..%d\n", medicins.size() - 1 );
         return null;
      }

      return medicins.get( index );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public void writeShort()
   {
      for (int i = 0; i < medicins.size(); i++)
      {
         System.out.format( "%3d. ", i + 1 );
         medicins.get( i ).writeShort();
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public void editMenu()
   {
      final int STOP    = 0;
      var       scanner = new BScanner();

      while (true)
      {
         writeShort();
         System.out.format( "0=return, negative index will delete, positive will edit\n" );

         int choice = scanner.scanInt();
         if (choice == STOP)
         {
            break;
         }

         if (choice < 0)
         {
            int index = -choice - 1;
            if (isValidIndex( index ))
            {
               medicins.remove( index );
            }
            else
            {
               System.out.format( "Invalid entry: %d\n", choice );
            }
         }
         else
         {
            int index = choice - 1;
            if (isValidIndex( index ))
            {
               var medicin = medicins.get( index );

               System.out.format( "%s: please enter new dose: (was: %s)\n", medicin.name(), medicin.dose() );
               var scanner2 = new BScanner();
               var dose     = scanner2.scanString();

               medicin.setDose( dose );
            }
            else
            {
               System.out.format( "Invalid entry: %d\n", choice );
            }
         }
      }
   }
}
