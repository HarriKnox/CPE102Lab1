import java.util.function.Function;

public interface SimpleList<T>
{
   /**
      Adds the specified T to the end of the list.
      @param element T to add to list.
   */
   void addToEnd(T element);

   /**
      Adds the specified T at index position in the list.
      The element previously at that position is now after this new element.
      @param index Position at which to add the element.
      @param element T to add to list.
   */
   void add(int index, T element);

   /**
      Removes the element at position index.
      @param index Position of element to remove.
   */
   void remove(int index);

   /**
      Returns the element at position index.
      @param index Position from which to retrieve T.
      @return T at index position in list.
   */
   T get(int index);

   /**
      Returns the index at which the element appears in the list.
      @param element Value to search for.
      @return Position of element.
   */
   int indexOf(T element);

   /**
      Number of elements in the list.
      @return Number of elements in the list.
   */
   int size();

   /**
      Returns a newly constructed list with the results of applying the
      input function to each element of this list.  Does not modify original
      list.
      @param function to map over list.
      @return Result list.
   */
   <R> SimpleList<R> map(Function<T, R> function);
}
