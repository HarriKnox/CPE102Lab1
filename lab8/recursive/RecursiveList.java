import java.util.function.Function;

public class RecursiveList<T> implements SimpleList<T>
{
	
	private RecursiveNode<T> head = new EmptyNode<T>();
	
	private static interface RecursiveNode<Q>
	{
		Q value();
		RecursiveNode<Q> next();
		void setNext(RecursiveNode<Q> node);
		
		RecursiveNode<Q> addToEnd(Q element);
		RecursiveNode<Q> add(int index, Q element);
		RecursiveNode<Q> remove(int index);
		Q get(int index);
		int indexOf(Q element);
		int size();
		<S> RecursiveNode<S> map(Function<Q, S> function);
	}
	
	private static class EmptyNode<Q> implements RecursiveNode<Q>
	{
		public Q value() {throw new java.lang.UnsupportedOperationException();}
		public RecursiveNode<Q> next() {throw new java.lang.UnsupportedOperationException();}
		public void setNext(RecursiveNode<Q> node) {throw new java.lang.UnsupportedOperationException();}
		
		public RecursiveNode<Q> addToEnd(Q element)
		{
			return new NonEmptyNode<Q>(element, this);
		}
		
		public RecursiveNode<Q> add(int index, Q element)
		{
			if (index == 0)
				return this.addToEnd(element);
			throw new java.lang.IndexOutOfBoundsException();
		}
		
		public RecursiveNode<Q> remove(int index) {throw new java.lang.IndexOutOfBoundsException();}
		public Q get(int index) {throw new java.lang.IndexOutOfBoundsException();}
		public int indexOf(Q element) {throw new java.util.NoSuchElementException();}
		
		public int size() {return 0;}
		
		public <R> RecursiveNode<R> map(Function<Q, R> function) {return new EmptyNode<R>();}
	}
	
	private static class NonEmptyNode<Q> implements RecursiveNode<Q>
	{
		private Q element;
		private RecursiveNode<Q> nextNode;
		
		public NonEmptyNode(Q elem, RecursiveNode<Q> node)
		{
			this.element = elem;
			this.nextNode = node;
		}
		
		public NonEmptyNode(Q elem)
		{
			this(elem, new EmptyNode<Q>());
		}
		
		public Q value() {return this.element;}
		public RecursiveNode<Q> next() {return this.nextNode;}
		public void setNext(RecursiveNode<Q> node) {this.nextNode = node;}
		
		public RecursiveNode<Q> addToEnd(Q element)
		{
			this.nextNode = this.nextNode.addToEnd(element);
			return this;
		}
		
		public RecursiveNode<Q> add(int index, Q element)
		{
			if (index == 0)
				return new NonEmptyNode<Q>(element, this);
			this.nextNode = this.nextNode.add(index - 1, element);
			return this;
		}
		
		public RecursiveNode<Q> remove(int index)
		{
			if (index == 0)
				return this.nextNode;
			this.nextNode = this.nextNode.remove(index - 1);
			return this;
		}
		
		public Q get(int index)
		{
			if (index == 0)
				return this.element;
			return this.nextNode.get(index - 1);
		}
		
		public int indexOf(Q element)
		{
			if (this.element.equals(element))
				return 0;
			return 1 + this.nextNode.indexOf(element);
		}
		
		public int size()
		{
			return 1 + this.nextNode.size();
		}
		
		public <R> RecursiveNode<R> map(Function<Q, R> function)
		{
			return new NonEmptyNode<R>(function.apply(this.element), this.nextNode.map(function));
		}
	}
	
	/**
		Adds the specified T to the end of the list.
		@param element T to add to list.
	*/
	public void addToEnd(T element)
	{
		this.head = this.head.addToEnd(element);
	}
	
	/**
		Adds the specified T at index position in the list.
		The element previously at that position is now after this new element.
		@param index Position at which to add the element.
		@param element T to add to list.
	*/
	public void add(int index, T element)
	{
		this.head = this.head.add(index, element);
	}
	
	/**
		Removes the element at position index.
		@param index Position of element to remove.
	*/
	public void remove(int index)
	{
		this.head = this.head.remove(index);
	}
	
	/**
		Returns the element at position index.
		@param index Position from which to retrieve T.
		@return T at index position in list.
	*/
	public T get(int index)
	{
		return this.head.get(index);
	}
	
	/**
		Returns the index at which the element appears in the list.
		@param element Value to search for.
		@return Position of element.
	*/
	public int indexOf(T element)
	{
		return this.head.indexOf(element);
	}
	
	/**
		Number of elements in the list.
		@return Number of elements in the list.
	*/
	public int size()
	{
		return this.head.size();
	}
	
	/**
		Returns a newly constructed list with the results of applying the
		input function to each element of this list.  Does not modify original
		list.
		@param function to map over list.
		@return Result list.
	*/
	public <R> SimpleList<R> map(Function<T, R> function)
	{
		RecursiveList<R> out = new RecursiveList<R>();
		out.head = this.head.map(function);
		return out;
	}
}
