import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

class Medicins
{
   private final HashMap<MedNum, Medicin> medicins = new HashMap<>();

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public Medicins( boolean init )
   {
      if (init)
      {
         medicins.put( MedNum.PARACETAMOL, new Medicin( "Paracetamol", "Pijnstiller", "", "3xdaags" ) );
         medicins.put( MedNum.ASPERINE, new Medicin( "Asperine", "Pijnstiller", "", "max 3xdaags" ) );
         medicins.put( MedNum.METFORMINE, new Medicin( "Metformine", "bloeddrukremmer", "Verlaagt bloedglucose", "1/week" ) );
         medicins.put( MedNum.JANUMET, new Medicin( "Janumet", "bloeddrukremmer", "Verlaagt bloedglucose", "1 x daags" ) );
         medicins.put( MedNum.CYAANKALI, new Medicin( "Cyaankali", "Pijnstiller", "effectieve oplosmiddel", "1x is genoeg" ) );
         medicins.put( MedNum.ADEFOVIR, new Medicin( "Adefovir", "Koortsremmer", "", "4 per week" ) );
         medicins.put( MedNum.TENOFOVIR, new Medicin( "Tenofovir alafenamide", "virusremmer", "HIV/HepatitusB virusremmer", "0" ) );
         medicins.put( MedNum.MOGADON, new Medicin( "Mogadon", "slaapmiddel", "", "max. 1x etmaal" ) );
         medicins.put( MedNum.NOTRAZEPAM, new Medicin( "Notrazepam", "kalmeringsmiddel", "-", "1x per etmaal" ) );
         medicins.put( MedNum.ACEBUTOLOL, new Medicin( "acebutolol", "betablokker", "blokkeert alle betas", "1xperdag" ) );
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public Medicins( JSONObject object )
   {
      JSONArray keys = object.getJSONArray( "medenums" );
      JSONArray vals = object.getJSONArray( "medicins" );
      for (int i = 0; i < vals.length(); i++)
      {
         Medicin m = new Medicin( (JSONObject) vals.get( i ) );
         int     k = keys.getInt( i );
         medicins.put( MedNum.values()[k], m );
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   JSONObject toJSON()
   {
      JSONObject obj = new JSONObject();

      JSONArray keys = new JSONArray();
      JSONArray vals = new JSONArray();
      for (var m : medicins.entrySet())
      {
         keys.put( m.getKey().ordinal() );
         vals.put( m.getValue().toJSON() );
      }
      obj.put( "medenums", keys );
      obj.put( "medicins", vals );

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

         // Read the medicin list
         JSONArray keys = object.getJSONArray( "medenums" );
         JSONArray vals = object.getJSONArray( "medicins" );
         for (int i = 0; i < vals.length(); i++)
         {
            Medicin m = new Medicin( (JSONObject) vals.get( i ) );
            int     k = keys.getInt( i );
            medicins.put( MedNum.values()[k], m );
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
   /// Add a new medicin to the list (overloaded)
   /// No test for uniqueness is being done.
   ////////////////////////////////////////////////////////////////////////////////
   public void addMedicin( MedNum id, Medicin m )
   {
      medicins.put( id, m );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public Medicin getMedicin( MedNum index )
   {
      return medicins.get( index );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public void writeShort()
   {
      for (var e : medicins.entrySet())
      {
         System.out.format( "%3d. ", e.getKey().ival );
         e.getValue().writeShort();
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public int writeComplement( Medicins reflist )
   {
      int count = 0;
      for (var e : reflist.medicins.entrySet())
      {
         if (!medicins.containsKey( e.getKey() ))
         {
            System.out.format( "%3d. ", e.getKey().ival );
            e.getValue().writeShort();
            count++;
         }
      }
      return count;
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
         System.out.format( "0=return, negative index will delete, positive will edit or add\n" );

         int choice = scanner.scanInt();
         if (choice == STOP)
         {
            break;
         }

         if (choice < 0)
         {
            var id = MedNum.int2val.get( -choice );
            if (medicins.containsKey( id ))
            {
               medicins.remove( id );
            }
            else
            {
               System.out.format( "Invalid entry: %d\n", choice );
            }
         }
         else
         {
            var id = MedNum.int2val.get( choice );

            if (medicins.containsKey( id ))
            {
               var medicin = medicins.get( id );

               System.out.format( "%s: please enter new dose: (was: %s)\n", medicin.name(), medicin.dose() );
               var scanner2 = new BScanner();
               var dose     = scanner2.scanString();

               medicin.setDose( dose );
            }
            else
            {
               // When here, a new medicin is to be added. First print which medicins can be selected
               int n = writeComplement( Admin.medicins );
               if (n == 0)
               {
                  System.out.println( "Cannot add medicin: No medicins left!" );
               }
               else
               {
                  System.out.println( "To add medicin, please enter its id (from list above)" );
                  var localscanner = new BScanner();
                  var k            = localscanner.scanInt();
                  var m            = MedNum.int2val.get( k );
                  if (m != null)
                  {
                     addMedicin( m, Admin.medicins.getMedicin( m ) );
                  }
                  else
                  {
                     System.out.format( "Invalid medicin id\n" );
                  }
               }
            }
         }
      }
   }
}
