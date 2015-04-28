import java.util.List;
import java.util.ArrayList;

public class VariableBindings
   implements Bindings
{ 
   // a hashtable is more appropriate, but we haven't discussed them
   private List<Binding> _list;
   public VariableBindings()
   {
      _list = new ArrayList<Binding>();
   }

   public void addBinding(String id, double value)
   {
      boolean found = false;
      for (Binding bind : _list)
      {
         if (bind.getKey().equals(id))
         {
            bind.setValue(value);
            found = true;
            break;
         }
      }
      if (!found)
      {
         _list.add(new Binding(id, value));
      }
   }

   public double lookupBinding(String id)
      throws UnboundIdentifierException
   {
      for (Binding bind : _list)
      {
         if (bind.getKey().equals(id))
         {
            return bind.getValue();
         }
      }

      throw new UnboundIdentifierException("unbound identifier: " + id);
   }

   private static class Binding
   {
      private String _key;
      private double _value;

      public Binding(String key, double value)
      {
         _key = key;
         _value = value;
      }

      public String getKey()
      {
         return _key;
      }

      public double getValue()
      {
         return _value;
      }

      public void setValue(double value)
      {
         _value = value;
      }
   }
}
