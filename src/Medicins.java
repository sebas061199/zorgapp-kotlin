import java.util.ArrayList;
import java.util.Scanner;

class Medicins
{
   private ArrayList<Medicin> medicins = new ArrayList<Medicin>( 0 );

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
      Scanner   scanner = new Scanner( System.in );

      while (true)
      {
         writeShort();
         System.out.format( "0=return, negative index will delete, positive will edit\n" );

         int choice = scanner.nextInt();
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
               Scanner scanner2 = new Scanner( System.in );
               var     dose     = scanner2.nextLine();

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
