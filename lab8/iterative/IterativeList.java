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
	
	private IterativeNode<T> head = null;
	
	/**
		Adds the specified T to the end of the list.
		@param element T to add to list.
	*/
	public void addToEnd(T element)
	{
		if (this.head == null)
		{
			this.head = new IterativeNode<T>(element);
		}
		else
		{
			IterativeNode<T> node = this.head;
			while (node.next() != null)
				node = node.next();
			node.setNext(new IterativeNode<T>(element));
		}
	}
	
	/**
		Adds the specified T at index position in the list.
		The element previously at that position is now after this new element.
		@param index Position at which to add the element.
		@param element T to add to list.
	*/
	public void add(int index, T element) throws java.lang.IndexOutOfBoundsException
	{
		IterativeNode<T> node = this.head;
		
		if ((index < 0) || (index > 0 && node == null))
			throw new java.lang.IndexOutOfBoundsException();
		
		IterativeNode<T> elem = new IterativeNode<>(element);
		
		if (index == 0)
		{
			elem.setNext(node);
			this.head = elem;
			return;
		}
		
		for (int count = 0; count < index - 1; count++)
		{
			if (node == null) throw new java.lang.IndexOutOfBoundsException();
			node = node.next();
		}
		
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
		
		if (index == 0)
		{
			this.head = this.head.next();
			return;
		}
		
		IterativeNode<T> node = this.head;
		
		for (int count = 0; count < index - 1; count++)
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
		
		if (node == null) throw new java.lang.IndexOutOfBoundsException();
		
		for (int count = 0; count < index; count++)
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
		
		int index = 0;
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
		
		int count = 0;
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
	public <R> SimpleList<R> map(java.util.function.Function<T, R> function)
	{
		SimpleList<R> output = new IterativeList<R>();
		
		IterativeNode<T> node = this.head;
		
		while (node != null)
		{
			output.addToEnd(function.apply(node.value()));
			node = node.next();
		}
		return output;
	}
}