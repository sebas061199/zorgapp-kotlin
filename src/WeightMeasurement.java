import org.json.JSONObject;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeightMeasurement
{
   private LocalDate date;
   private double    weight;

   public WeightMeasurement( LocalDate date, double weight )
   {
      this.date   = date;
      this.weight = weight;
   }

   public WeightMeasurement( JSONObject obj )
   {
      // Read date first as string, then convert to LocalDate.
      String            sdate     = obj.getString( "date" );
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd" );

      this.date   = LocalDate.parse( sdate, formatter );
      this.weight = obj.getDouble( "weight" );
   }

   public JSONObject toJSON()
   {
      JSONObject obj = new JSONObject();

      obj.put( "date", date );
      obj.put( "weight", weight );

      return obj;
   }

   public double getWeight()
   {
      return weight;
   }

   public LocalDate getDate()
   {
      return date;
   }
}
