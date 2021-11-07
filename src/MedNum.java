import java.util.HashMap;

public enum MedNum
{
   ACEBUTOLOL( 1 ), ADEFOVIR( 2 ), ASPERINE( 3 ), CYAANKALI( 4 ), JANUMET( 5 ),
   METFORMINE( 6 ), MOGADON( 7 ), NOTRAZEPAM( 8 ), PARACETAMOL( 9 ), TENOFOVIR( 10 );

   public final int ival;

   static HashMap<Integer, MedNum> int2val = new HashMap<>();

   static
   {
      for (var e : values())
      {
         int2val.put( e.ival, e );
      }
   }

   MedNum( int m )
   {
      this.ival = m;
   }
}
