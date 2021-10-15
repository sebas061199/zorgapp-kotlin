import java.util.Scanner;

class Medicin
{
   private String name;
   private String desc;
   private String type;
   private String dose;

   ////////////////////////////////////////////////////////////////////////////////
   /// ctor
   ////////////////////////////////////////////////////////////////////////////////
   Medicin( String name, String type, String description, String dose )
   {
      this.name = name;
      desc      = description;
      this.type = type;
      this.dose = dose;
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Copy ctor
   ////////////////////////////////////////////////////////////////////////////////
   Medicin( Medicin m )
   {
      name = m.name;
      desc = m.desc;
      type = m.type;
      dose = m.dose;
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Copy ctor, overwrite dose
   ////////////////////////////////////////////////////////////////////////////////
   Medicin( Medicin m, String dose )
   {
      name      = m.name;
      desc      = m.desc;
      type      = m.type;
      this.dose = dose;
   }

   public String name()
   {
      return name;
   }

   public String desc()
   {
      return desc;
   }

   public void setDose( String dose )
   {
      this.dose = dose;
   }

   public String kind()
   {
      return type;
   }

   public String dose()
   {
      return dose;
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// write medicin (oneliner)
   ////////////////////////////////////////////////////////////////////////////////
   public void writeShort()
   {
      System.out.format( "%s, %s\n", name, dose );
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// write medicin details
   ////////////////////////////////////////////////////////////////////////////////
   public void writeLong()
   {
      System.out.format( "naam:         %s\n", name );
      System.out.format( "soort:        %s\n", type );
      System.out.format( "omschrijving: %s\n", desc );
      System.out.format( "dosis:        %s\n", dose );
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Edit a perticular medicin (dose only!)
   ////////////////////////////////////////////////////////////////////////////////
   public Medicin edit()
   {
      System.out.format( "edit medicin (dose only): " ); //writeShort();
      System.out.format( "please enter a new dose\n" );

      Scanner input = new Scanner( System.in );
      String  dose  = input.nextLine().trim();

      // Create updated Medicin, or leave untouched in case of an empty string.
      Medicin m = !dose.isEmpty() ? new Medicin( this, dose ) : this;
      if (m != this)
      {
         System.out.format( "new medicin:\n" );
         m.writeLong();
      }

      return m;
   }
}
