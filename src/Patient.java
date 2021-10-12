import java.time.LocalDate;
import java.time.Period;
import java.util.Locale;
import java.util.Scanner;

public class Patient
{
   private final int RETURN      = 0;
   private final int SURNAME     = 1;
   private final int FIRSTNAME   = 2;
   private final int NICKNAME    = 3;
   private final int DATEOFBIRTH = 4;
   private final int WEIGHT      = 5;
   private final int LENGTH      = 6;

   private String surName;
   private String firstName;
   private String nickName;

   private LocalDate dateOfBirth;

   private double length = -1.0;
   private double weight = 0.0;
   private int    id     = -1;

   // Constructor
   Patient( int id, String surName, String firstName, LocalDate dateOfBirth )
   {
      this.id          = id;
      this.surName     = surName;
      this.firstName   = firstName;
      this.nickName    = firstName; // rest via separate method if needed.
      this.dateOfBirth = dateOfBirth;
   }

   Patient( int id, String surName, String firstName, LocalDate dateOfBirth, double weight, double length )
   {
      this( id, surName, firstName, dateOfBirth );
      this.weight = weight;
      this.length = length;
   }

   public double getLength()
   {
      return length;
   }

   public void setLength( double length )
   {
      this.length = length;
   }

   public double getWeight()
   {
      return weight;
   }

   public void setWeight( double weight )
   {
      this.weight = weight;
   }

   public double calcBMI()
   {
      return weight/(length*length);
   }

   // Access surName
   public String getSurName()
   {
      return surName;
   }

   // Access surName
   public String getFirstName()
   {
      return firstName;
   }

   // Access callName
   public String getNickName()
   {
      return nickName;
   }

   void editMenu( boolean zv )
   {
      Scanner scanner1 = new Scanner( System.in ); // use for integers.
      scanner1.useLocale( Locale.US );
      Scanner scanner2 = new Scanner( System.in ); // use for strings
      scanner2.useLocale( Locale.US );

      boolean nextCycle = true;
      while (nextCycle)
      {
         System.out.println( "Patient data edit menu: enter digit (0=return)" );
         printEditMenuOptions( zv );

         int choice = scanner1.nextInt();
         switch (choice)
         {
            case RETURN:
               nextCycle = false;
               break;

            case SURNAME:
               if (zv)
               {
                  System.out.println( "Enter new surname:" );
                  surName = scanner2.nextLine();
                  break;
               }

            case FIRSTNAME:
               if (zv)
               {
                  System.out.println( "Enter new first name:" );
                  firstName = scanner2.nextLine();
                  break;
               }

            case NICKNAME:
               System.out.println( "Enter new nickname:" );
               nickName = scanner2.nextLine();
               break;

            case DATEOFBIRTH:
               if (zv)
               {
                  System.out.println( "Enter new date of birth (yyyy-MM-dd):" );
                  String sdate = scanner2.nextLine();
                  dateOfBirth = LocalDate.parse( sdate );
                  break;
               }

            case LENGTH:
               if (zv)
               {
                  System.out.println( "Enter new length (in m)" );
                  double l = scanner1.nextDouble();
                  setLength( l );
                  break;
               }

            case WEIGHT:
               if (zv)
               {
                  System.out.println( "Enter new weight (in kg)" );
                  double m = scanner1.nextDouble();
                  setWeight( m );
                  break;
               }

            default:
               System.out.println( "Invalid entry: " + choice );
               break;
         }
      }
   }

   // Write patient data to screen.
   void printEditMenuOptions( boolean zv )
   {
      if (zv)
      {
         System.out.println( SURNAME + " - Surname:       " + surName );
         System.out.println( FIRSTNAME + " - First name:    " + firstName );
      }
      System.out.println( NICKNAME + " - Nickname:      " + nickName );

      if (zv)
      {
         System.out.println( DATEOFBIRTH + " - Date of Birth: " + dateOfBirth );
         System.out.println( LENGTH + " - Length: " + length + " (m)" );
         System.out.println( WEIGHT + " - Weight: " + weight + " (kg)" );
      }
   }

   // Write patient data to screen.
   void write()
   {
      System.out.println( "===================================" );
      System.out.println( "Surname:        " + surName );
      System.out.println( "First name:     " + firstName );
      System.out.println( "Nickname:       " + nickName );
      Period age = dateOfBirth.until( LocalDate.now() );
      System.out.println( "Date of birth:  " + dateOfBirth + " (age " + age.getYears() + ")" );
      System.out.format( "%-17s %.2f\n", "Length:", length );
      System.out.format( "%-17s %.2f\n", "Weight:", weight );
      System.out.format( "%-17s %.1f\n", "Body Mass Index:", calcBMI() );
      System.out.println( "===================================" );
   }

   // Write oneline info of patient to screen
   void writeOneliner()
   {
      System.out.format( "%10s %-20s [%s]\n", firstName, surName, dateOfBirth.toString() );
   }
}
