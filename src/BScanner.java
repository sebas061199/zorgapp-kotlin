import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import static java.time.format.ResolverStyle.STRICT;

///////////////////////////////////////////////////////////////////////////////////
// BScanner is a scanner call, wrapping the java.util.Scanner, providing a bit more
// robustness in cases where the given input does not match the required one.
///////////////////////////////////////////////////////////////////////////////////
class BScanner
{
   private final Scanner scanner;

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public BScanner()
   {
      scanner = new Scanner( System.in );
      scanner.useLocale( Locale.US );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public BScanner( String arg )
   {
      scanner = new Scanner( arg );
      scanner.useLocale( Locale.US );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public int scanInt()
   {
      int r = 0;

      while (true)
      {
         try
         {
            r = scanner.nextInt();
            break;
         }
         catch (Exception e)
         {
            System.out.println( "please enter integer" );
         }
         scanner.nextLine();
      }

      return r;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public double scanDouble()
   {
      double r = 0;

      while (true)
      {
         try
         {
            r = scanner.nextDouble();
            break;
         }
         catch (Exception e)
         {
            System.out.println( "please enter a floating point number" );
         }
         scanner.nextLine();
      }

      return r;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public String scanString()
   {
      String r = "";

      while (true)
      {
         try
         {
            r = scanner.nextLine();
            break;
         }
         catch (Exception e)
         {
            System.out.println( "please enter a string (ws allowed)" );
         }
         scanner.nextLine();
      }

      return r;
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Note: STRICT formatting does not work....?????
   ///       java.time.format.ResolverStyle.STRICT (see formatter, below)
   ////////////////////////////////////////////////////////////////////////////////
   public LocalDate scanDate( String fmt )
   {
      LocalDate r = null;
      String sdate = "";

//      DateTimeFormatter formatter = DateTimeFormatter.ofPattern( fmt );
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern( fmt ).withResolverStyle(STRICT);
      while (true)
      {
         try
         {
            sdate = scanner.nextLine();
            r = LocalDate.parse( sdate, formatter );
            break;
         }
         catch (Exception e)
         {
            System.out.format( "%s is not a valid date; ", sdate );
            System.out.format( "please enter a valid date %s:\n", fmt );
//            while (scanner.hasNext())
//            {
//               scanner.next();
//            }
         }
         //scanner.nextLine();
      }

      return r;
   }

}
