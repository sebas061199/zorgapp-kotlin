import org.json.JSONObject;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Patient
{
   private final int RETURN      = 0;
   private final int SURNAME     = 1;
   private final int FIRSTNAME   = 2;
   private final int NICKNAME    = 3;
   private final int DATEOFBIRTH = 4;
   private final int LENGTH      = 5;
   private final int WEIGHT      = 6;
   private final int MEDICATION  = 7;

   private String surName;
   private String firstName;
   private String nickName;

   private LocalDate dateOfBirth;

   private double length = -1.0;
   private double weight = 0.0;
   private int    id     = -1;

   private Medicins medicins = new Medicins( false );  // Start with an empty medicin list.

   // Constructor
   Patient( int id, String surName, String firstName, LocalDate dateOfBirth )
   {
      this.id          = id;
      this.surName     = surName;
      this.firstName   = firstName;
      this.nickName    = firstName; // rest via separate method if needed.
      this.dateOfBirth = dateOfBirth;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   Patient( int id, String surName, String firstName, LocalDate dateOfBirth, double weight, double length )
   {
      this( id, surName, firstName, dateOfBirth );
      this.weight = weight;
      this.length = length;
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Construct an object from its JSONObject form.
   /// @note tagnames in this constructor must match those used in routine toJson()!
   ////////////////////////////////////////////////////////////////////////////////
   public Patient( JSONObject obj )
   {
      id        = obj.getInt( "id" );
      firstName = obj.getString( "firstName" );
      surName   = obj.getString( "surName" );

      // Read date first as string, then convert to LocalDate.
      String            date      = obj.getString( "dateOfBirth" );
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );
      dateOfBirth = LocalDate.parse( date, formatter );

      weight = obj.getDouble( "weight" );
      length = obj.getDouble( "length" );

      // The field _mymedicins is written as a JSONObject. (@see toJSON)
      JSONObject obj2 = obj.getJSONObject( "medicins" );
      medicins = new Medicins( obj2 );
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Serialize an object to a JSONObject.
   ////////////////////////////////////////////////////////////////////////////////
   public JSONObject toJSON()
   {
      JSONObject obj = new JSONObject();

      obj.put( "id", id );
      obj.put( "firstName", firstName );
      obj.put( "surName", surName );
      obj.put( "dateOfBirth", dateOfBirth );
      obj.put( "weight", weight );
      obj.put( "length", length );

      obj.put( "medicins", medicins.toJSON() );

      return obj;
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
      return length > 0.0 ? weight/(length*length) : -1.0;
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

   // Add a new medicin to the patients medication
   public void addMedicin( Medicin medicin )
   {
      medicins.addMedicin( new Medicin( medicin ) );
   }

   // Add a new medicin to the patients medication (new dose)
   public void addMedicin( Medicin medicin, String mydose )
   {
      medicins.addMedicin( new Medicin( medicin, mydose ) );
   }

   // Handle editing of patient data
   void editMenu( boolean zv )
   {
      var scanner1 = new BScanner(); // use for integers.
      var scanner2 = new BScanner(); // use for strings

      boolean nextCycle = true;
      while (nextCycle)
      {
         System.out.println( "Patient data edit menu:" );
         printEditMenuOptions( zv );
         System.out.println( "Enter digit (0=return)" );

         int choice = scanner1.scanInt();
         switch (choice)
         {
            case RETURN:
               nextCycle = false;
               break;

            case SURNAME:
               if (zv)
               {
                  System.out.format( "Enter new surname: (was: %s)\n", surName );
                  surName = scanner2.scanString();
                  break;
               }

            case FIRSTNAME:
               if (zv)
               {
                  System.out.format( "Enter new first name: (was: %s)\n", firstName );
                  firstName = scanner2.scanString();
                  break;
               }

            case NICKNAME:
               System.out.format( "Enter new nickname: (was: %s)\n", nickName );
               nickName = scanner2.scanString();
               break;

            case DATEOFBIRTH:
               if (zv)
               {
                  System.out.format( "Enter new date of birth (yyyy-MM-dd; was: %s)\n", dateOfBirth );
                  String sdate = scanner2.scanString();
                  dateOfBirth = LocalDate.parse( sdate );
                  break;
               }

            case LENGTH:
               if (zv)
               {
                  System.out.format( "Enter new length (in m; was: %.2f)", length );
                  double l = scanner1.scanDouble();
                  setLength( l );
                  break;
               }

            case WEIGHT:
               if (zv)
               {
                  System.out.format( "Enter new weight (in kg; was: %.1f)\n", weight );
                  double m = scanner1.scanDouble();
                  setWeight( m );
                  break;
               }

            case MEDICATION:
               if (zv)
               {
                  medicins.editMenu();
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
         System.out.format( "%d: %-17s %s\n", SURNAME, "Surname:", surName );
         System.out.format( "%d: %-17s %s\n", FIRSTNAME, "firstName:", firstName );
      }
      System.out.format( "%d: %-17s %s\n", NICKNAME, "Nickname:", nickName );

      if (zv)
      {
         Period age = dateOfBirth.until( LocalDate.now() );
         System.out.format( "%d: %-17s %s (age %d)\n", DATEOFBIRTH, "Date of birth:", dateOfBirth, age.getYears() );
         System.out.format( "%d: %-17s %.2f\n", LENGTH, "Length:", length );
         System.out.format( "%d: %-17s %.2f (bmi=%.1f)\n", WEIGHT, "Weight:", weight, calcBMI() );
         System.out.format( "%d: %-17s %d medicins\n", MEDICATION, "Medication", medicins.size() );
      }
   }

   // Write patient data to screen.  TODO: Combine with printEditMenuOptions
   void write()
   {
      System.out.println( "===================================" );
      System.out.format( "%-17s %s\n", "Surname:", surName );
      System.out.format( "%-17s %s\n", "firstName:", firstName );
      System.out.format( "%-17s %s\n", "Nickname:", nickName );
      Period age = dateOfBirth.until( LocalDate.now() );
      System.out.format( "%-17s %s (age %d)\n", "Date of birth:", dateOfBirth, age.getYears() );
      System.out.format( "%-17s %.2f\n", "Length:", length );
      System.out.format( "%-17s %.2f (bmi=%.1f)\n", "Weight:", weight, calcBMI() );
      System.out.format( "%-17s %d medicins\n", "Medication:", medicins.size() );
      medicins.writeShort();
      System.out.println( "===================================" );
   }

   // Write oneline info of patient to screen
   void writeOneliner()
   {
      System.out.format( "%10s %-20s [%s]\n", firstName, surName, dateOfBirth.toString() );
   }
}
