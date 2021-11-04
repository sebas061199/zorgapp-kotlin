import org.json.JSONObject;

class Medicin
{
   private final String name;
   private final String desc;
   private final String type;
   private       String dose;

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

   ////////////////////////////////////////////////////////////////////////////////
   /// ctor
   ////////////////////////////////////////////////////////////////////////////////
   Medicin( String name, String type, String description, String dose )
   {
      this.name = name;
      this.desc = description;
      this.type = type;
      this.dose = dose;
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// CTOR: construct from JSONObject
   ////////////////////////////////////////////////////////////////////////////////
   public Medicin( JSONObject obj )
   {
      name = obj.getString( "name" );
      desc = obj.getString( "desc" );
      type = obj.getString( "type" );
      dose = obj.getString( "dose" );
   }

   ////////////////////////////////////////////////////////////////////////////////
   /// Serialise
   ////////////////////////////////////////////////////////////////////////////////
   public JSONObject toJSON()
   {
      JSONObject jobj = new JSONObject();

      jobj.put( "name", name );
      jobj.put( "desc", desc );
      jobj.put( "type", type );
      jobj.put( "dose", dose );

      return jobj;
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

      var    input = new BScanner();
      String dose  = input.scanString().trim();

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
