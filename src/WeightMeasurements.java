import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.SortedMap;
import java.util.TreeMap;

public class WeightMeasurements
{
   SortedMap<LocalDate, Double> dateWeights = new TreeMap<>();

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public WeightMeasurements()
   {
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public WeightMeasurements( JSONObject obj )
   {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

      JSONArray jd = obj.getJSONArray( "wdates" );
      JSONArray jw = obj.getJSONArray( "weights" );
      for (int i = 0; i < jd.length(); i++)
      {
         var date = LocalDate.parse( jd.getString( i ), formatter );
         dateWeights.put( date, jw.getDouble( i ) );
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   JSONObject toJSON()
   {
      JSONObject obj = new JSONObject();

      JSONArray jd = new JSONArray();
      JSONArray jw = new JSONArray();
      for (var e : dateWeights.entrySet())
      {
         jd.put( e.getKey() );     // LocalDate
         jw.put( e.getValue() );   // Double
      }

      obj.put( "wdates", jd );
      obj.put( "weights", jw );

      return obj;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public void addMeasurement( LocalDate date, double weight )
   {
      dateWeights.put( date, weight );
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public int size()
   {
      return dateWeights.size();
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public void plot()
   {
      for (var dw : dateWeights.entrySet())
      {
         int n = nweight( dw.getValue() );
         System.out.format( "%s: %s (%4.1f)\n", dw.getKey().toString(), "*".repeat( n ), dw.getValue() );
      }
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   private int nweight( double w )
   {
      return (int) w;
   }

   ////////////////////////////////////////////////////////////////////////////////
   ////////////////////////////////////////////////////////////////////////////////
   public double mostRecent()
   {
      return dateWeights.get( dateWeights.lastKey() );
   }
}
