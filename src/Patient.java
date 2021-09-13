public class Patient
{
   String surName;
   String firstName;
   String callName;

   // Constructor
   Patient( String surName, String firstName )
   {
      this.surName = surName;
      this.firstName = firstName;

      this.callName = firstName; // rest via separate method if needed.
   }

   // Write data to screen.
   void write()
   {
      System.out.println( "Surname:    " + surName );
      System.out.println( "First name: " + firstName );
      System.out.println( "Call:       " + callName );
   }
}
