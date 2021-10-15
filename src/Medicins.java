import java.util.ArrayList;

class Medicins
{
   private ArrayList<Medicin> medicins = new ArrayList<Medicin>( 0);

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
         medicins.add( new Medicin( "Cyaankali", "Pijnstiller", "effectieve oplosmiddel", "1x is genoeg") );
         medicins.add( new Medicin( "Adefovir", "Koortsremmer", "", "4 per week" ) );
         medicins.add( new Medicin( "Tenofovir alafenamide", "virusremmer", "HIV/HepatitusB virusremmer", "0") );
         medicins.add( new Medicin( "Mogadon", "slaapmiddel", "", "max. 1x etmaal") );
         medicins.add( new Medicin( "Notrazepam", "kalmeringsmiddel", "-", "1x per etmaal") );
         medicins.add( new Medicin( "acebutolol", "betablokker", "blokkeert alle betas", "1xperdag") );
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
   public void addMedicin( String name, String kind, String desc, String dose )
   {
      medicins.add( new Medicin( name, kind, desc, dose ) );
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
   public Medicin getMedicin( int k )
   {
      if ( k<0 || k > medicins.size() - 1 )
      {
         System.out.format( "ERROR: medicin id must be one of 0..%d\n", medicins.size() - 1 );
         return null;
      }

      return medicins.get( k );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public void setMedicin( int k, Medicin m )
   {
      if (k<0 || k > medicins.size() - 1)
      {
         System.out.format( "ERROR: medicin id must be one of 0..%d\n", medicins.size() - 1 );
         return;
      }

      medicins.set( k, m );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public void writeln()
   {
      for (int i = 0; i < medicins.size(); i++)
      {
         System.out.format( "%3d. ", i+1);  medicins.get( i ).writeShort();
      }
   }

}
