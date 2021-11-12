import java.time.LocalDateTime;

import static java.lang.System.exit;

public class ZorgApp
{
   public static void main( String[] args )
   {
      System.out.println( "ZorgApp2021-" + "sprint3x" );

      if (args.length != 1)
      {
         System.out.println( "usage: ZorgApp <patientnr>" );
         exit( 0 );
      }
      assert (args.length == 1);// When ere we know that args[] has only one element.

      var input  = new BScanner( args[0] );
      int userID = input.scanInt(); // patientnr. 0=zorgverlener.
      System.out.println( "userID: " + userID );

      if (userID < 0)
      {
         System.err.println( "usage: zorgapp <patientnr>" );
         exit( -1 );
      }

      Admin admin = new Admin( userID );
      admin.menu();

      System.out.format( "saving data...\n" );
      admin.save();

      System.out.println( "ZorgApp ends at " + LocalDateTime.now() );
   }
}
