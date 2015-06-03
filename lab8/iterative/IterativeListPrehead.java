public class IterativeList<T> implements SimpleList<T>
{
	private static class IterativeNode<Q>
	{
		private Q element = null;
		private IterativeNode<Q> nextNode = null;
		
		public IterativeNode(Q elem)
		{
			this.element = elem;
		}
		
		public IterativeNode<Q> next()
		{
			return this.nextNode;
		}
		
		public Q value()
		{
			return this.element;
		}
		
		public void setNext(IterativeNode<Q> node)
		{
			this.nextNode = node;
		}
	}
	
	private IterativeNode<T> head = new IterativeNode<>(null);
	
	/**
		Adds the specified T to the end of the list.
		@param element T to add to list.
	*/
	public void addToEnd(T element)
	{
		IterativeNode<T> node = this.head;
		while (node.next() != null)
			node = node.next();
		node.setNext(new IterativeNode<T>(element));
	}
	
	/**
		Adds the specified T at index position in the list.
		The element previously at that position is now after this new element.
		@param index Position at which to add the element.
		@param element T to add to list.
	*/
	public void add(int index, T element) throws java.lang.IndexOutOfBoundsException
	{
		if (index < 0)
			throw new java.lang.IndexOutOfBoundsException();
		
		IterativeNode<T> node = this.head;
		
		for (int count = 0; count < index; count++)
		{
			if (node == null) throw new java.lang.IndexOutOfBoundsException();
			node = node.next();
		}
		
		IterativeNode<T> elem = new IterativeNode<>(element);
		
		elem.setNext(node.next());
		node.setNext(elem);
	}
	
	/**
		Removes the element at position index.
		@param index Position of element to remove.
	*/
	public void remove(int index)
	{
		if (index < 0) throw new java.lang.IndexOutOfBoundsException();
		
		IterativeNode<T> node = this.head;
		
		for (int count = 0; count < index; count++)
		{
			if (node == null) throw new java.lang.IndexOutOfBoundsException();
			node = node.next();
		}
		
		if (node.next() == null) throw new java.lang.IndexOutOfBoundsException();
		node.setNext(node.next().next());
	}
	
	/**
		Returns the element at position index.
		@param index Position from which to retrieve T.
		@return T at index position in list.
	*/
	public T get(int index)
	{
		if (index < 0) throw new java.lang.IndexOutOfBoundsException();
		
		IterativeNode<T> node = this.head;
		
		for (int count = -1; count < index; count++)
		{
			if (node.next() == null) throw new java.lang.IndexOutOfBoundsException();
			node = node.next();
		}
		
		return node.value();
	}
	
	/**
		Returns the index at which the element appears in the list.
		@param element Value to search for.
		@return Position of element.
	*/
	public int indexOf(T element) throws java.util.NoSuchElementException
	{
		IterativeNode<T> node = this.head;
		
		int index = -1;
		while (node != null)
		{
			if (node.value().equals(element)) return index;
			node = node.next();
			index++;
		}
		throw new java.util.NoSuchElementException();
	}
	
	
	/**
		Number of elements in the list.
		@return Number of elements in the list.
	*/
	public int size()
	{
		IterativeNode<T> node = this.head;
		
		int count = -1;
		while (node != null)
		{
			node = node.next();
			count++;
		}
		return count;
	}
	
	/**
		Returns a newly constructed list with the results of applying the
		input function to each element of this list.  Does not modify original
		list.
		@param function to map over list.
		@return Result list.
	*/
	public <R> SimpleList<R> map(java.util.function.Function<T, R> func)
	{
		SimpleList<R> output = new IterativeList<R>();
		
		IterativeNode<T> node = this.head.next();
		
		while (node != null)
		{
			output.addToEnd(func.apply(node.value()));
			node = node.next();
		}
		return output;
	}
}