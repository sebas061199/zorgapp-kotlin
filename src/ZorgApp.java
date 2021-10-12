import java.util.Scanner;

import static java.lang.System.exit;

public class ZorgApp
{
   public static void main( String[] args )
   {
      System.out.println( "ZorgApp2021-" + "sprint1" );

      if (args.length != 1)
      {
         System.out.println( "usage: ZorgApp <patientnr>" );
         exit( 0 );
      }
      assert (args.length == 1);// When ere we know that args[] has only one element.

      Scanner input  = new Scanner( args[0] );
      int     userID = input.nextInt(); // patientnr. 0=zorgverlener.
      System.out.println( "userID: " + userID );

      if (userID < 0)
      {
         System.err.println( "usage: zorgapp <patientnr>" );
         exit( -1 );
      }

      Admin admin = new Admin( userID );
      admin.menu();
   }
}
