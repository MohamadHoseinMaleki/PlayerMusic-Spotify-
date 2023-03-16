import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

    public class LinkList<E> {
        private transient Entry<E> header = new Entry<E>(null, null, null);
        private transient int size = 0;
        private int modCount = 0 ;

        public E max(){
            E max = getFirst() ;
            for (int i = 0 ; i < size ; i++){
                if (max.equals(get(i)))
                    max = get(i) ;
            }
            return max ;
        }
        public LinkList() {
            header.next = header.previous = header;
        }
        public LinkList(Collection<? extends E> c) {
            this();
            addAll(c);
        }

        public E getFirst() {
            if (size==0)
                throw new NoSuchElementException();

            return header.next.element;
        }

        public E removeFirst() {
            return remove(header.next);
        }

        public E removeLast() {
            return remove(header.previous);
        }
        public void addFirst(E o) {
            addBefore(o, header.next);
        }

        public void addLast(E o) {
            addBefore(o, header);
        }
        public boolean contains(Object o) {
            return indexOf(o) != -1;
        }

        public int size() {
            return size;
        }

        public boolean add(E o) {
            addBefore(o, header);
            return true;
        }
        public boolean remove(Object o) {
            if (o==null) {
                for (Entry<E> e = header.next; e != header; e = e.next) {
                    if (e.element==null) {
                        remove(e);
                        return true;
                    }
                }
            } else {
                for (Entry<E> e = header.next; e != header; e = e.next) {
                    if (o.equals(e.element)) {
                        remove(e);
                        return true;
                    }
                }
            }
            return false;
        }
        public boolean addAll(Collection<? extends E> c) {
            return addAll(size, c);
        }
        public boolean addAll(int index, Collection<? extends E> c) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("Index: "+index+
                        ", Size: "+size);
            Object[] a = c.toArray();
            int numNew = a.length;
            if (numNew==0)
                return false;
            modCount++;

            Entry<E> successor = (index==size ? header : entry(index));
            Entry<E> predecessor = successor.previous;
            for (int i=0; i<numNew; i++) {
                Entry<E> e = new Entry<E>((E)a[i], successor, predecessor);
                predecessor.next = e;
                predecessor = e;
            }
            successor.previous = predecessor;

            size += numNew;
            return true;
        }

        public E get(int index) {
            return entry(index).element;
        }

        /**
         * Replaces the element at the specified position in this list with the
         * specified element.
         * @param index index of element to replace.
         * @param element element to be stored at the specified position.
         * @return the element previously at the specified position.
         * @throws IndexOutOfBoundsException if the specified index is out of
         *		  range (<tt>index &lt; 0 || index &gt;= size()</tt>).
         */
        public E set(int index, E element) {
            Entry<E> e = entry(index);
            E oldVal = e.element;
            e.element = element;
            return oldVal;
        }

        /**
         * Removes the element at the specified position in this list.  Shifts any
         * subsequent elements to the left (subtracts one from their indices).
         * Returns the element that was removed from the list.
         *
         * @param index the index of the element to removed.
         * @return the element previously at the specified position.
         *
         * @throws IndexOutOfBoundsException if the specified index is out of
         * 		  range (<tt>index &lt; 0 || index &gt;= size()</tt>).
         */
        public E remove(int index) {
            return remove(entry(index));
        }

        /**
         * Return the indexed entry.
         */
        private Entry<E> entry(int index) {
            if (index < 0 || index >= size)
                throw new IndexOutOfBoundsException("Index: "+index+ ", Size: "+size);
            Entry<E> e = header;
            if (index < (size >> 1)) {
                for (int i = 0; i <= index; i++)
                    e = e.next;
            } else {
                for (int i = size; i > index; i--)
                    e = e.previous;
            }
            return e;
        }



        public int indexOf(Object o) {
            int index = 0;
            if (o==null) {
                for (Entry e = header.next; e != header; e = e.next) {
                    if (e.element==null)
                        return index;
                    index++;
                }
            } else {
                for (Entry e = header.next; e != header; e = e.next) {
                    if (o.equals(e.element))
                        return index;
                    index++;
                }
            }
            return -1;
        }

        /**
         * Returns the index in this list of the last occurrence of the
         * specified element, or -1 if the list does not contain this
         * element.  More formally, returns the highest index i such that
         * <tt>(o==null ? get(i)==null : o.equals(get(i)))</tt>, or -1 if
         * there is no such index.
         *
         * @param o element to search for.
         * @return the index in this list of the last occurrence of the
         * 	       specified element, or -1 if the list does not contain this
         * 	       element.
         */
        public int lastIndexOf(Object o) {
            int index = size;
            if (o==null) {
                for (Entry e = header.previous; e != header; e = e.previous) {
                    index--;
                    if (e.element==null)
                        return index;
                }
            } else {
                for (Entry e = header.previous; e != header; e = e.previous) {
                    index--;
                    if (o.equals(e.element))
                        return index;
                }
            }
            return -1;
        }


        public E peek() {
            if (size==0)
                return null;
            return getFirst();
        }


        public E element() {
            return getFirst();
        }

        /**
         * Retrieves and removes the head (first element) of this list.
         * @return the head of this queue, or <tt>null</tt> if this queue is empty.
         * @since 1.5
         */
        public E poll() {
            if (size==0)
                return null;
            return removeFirst();
        }

        /**
         * Retrieves and removes the head (first element) of this list.
         * @return the head of this queue.
         * @throws NoSuchElementException if this queue is empty.
         * @since 1.5
         */
        public E remove() {
            return removeFirst();
        }

        /**
         * Adds the specified element as the tail (last element) of this list.
         *
         * @param o the element to add.
         * @return <tt>true</tt> (as per the general contract of
         * <tt>Queue.offer</tt>)
         * @since 1.5
         */
        public boolean offer(E o) {
            return add(o);
        }


        private class ListItr implements ListIterator<E> {
            private Entry<E> lastReturned = header;
            private Entry<E> next;
            private int nextIndex;
            private int expectedModCount = modCount;

            ListItr(int index) {
                if (index < 0 || index > size)
                    throw new IndexOutOfBoundsException("Index: "+index+
                            ", Size: "+size);
                if (index < (size >> 1)) {
                    next = header.next;
                    for (nextIndex=0; nextIndex<index; nextIndex++)
                        next = next.next;
                } else {
                    next = header;
                    for (nextIndex=size; nextIndex>index; nextIndex--)
                        next = next.previous;
                }
            }

            public boolean hasNext() {
                return nextIndex != size;
            }

            public E next() {
                checkForComodification();
                if (nextIndex == size)
                    throw new NoSuchElementException();

                lastReturned = next;
                next = next.next;
                nextIndex++;
                return lastReturned.element;
            }

            public boolean hasPrevious() {
                return nextIndex != 0;
            }

            public E previous() {
                if (nextIndex == 0)
                    throw new NoSuchElementException();

                lastReturned = next = next.previous;
                nextIndex--;
                checkForComodification();
                return lastReturned.element;
            }

            public int nextIndex() {
                return nextIndex;
            }

            public int previousIndex() {
                return nextIndex-1;
            }

            public void remove() {
                checkForComodification();
                Entry<E> lastNext = lastReturned.next;
                try {
                    LinkList.this.remove(lastReturned);
                } catch (NoSuchElementException e) {
                    throw new IllegalStateException();
                }
                if (next==lastReturned)
                    next = lastNext;
                else
                    nextIndex--;
                lastReturned = header;
                expectedModCount++;
            }

            public void set(E o) {
                if (lastReturned == header)
                    throw new IllegalStateException();
                checkForComodification();
                lastReturned.element = o;
            }

            public void add(E o) {
                checkForComodification();
                lastReturned = header;
                addBefore(o, next);
                nextIndex++;
                expectedModCount++;
            }

            final void checkForComodification() {
                if (modCount != expectedModCount)
                    throw new ConcurrentModificationException();
            }
        }

        private static class Entry<E> {
            E element;
            Entry<E> next;
            Entry<E> previous;

            Entry(E element, Entry<E> next, Entry<E> previous) {
                this.element = element;
                this.next = next;
                this.previous = previous;
            }
        }

        private Entry<E> addBefore(E o, Entry<E> e) {
            Entry<E> newEntry = new Entry<E>(o, e, e.previous);
            newEntry.previous.next = newEntry;
            newEntry.next.previous = newEntry;
            size++;
            modCount++;
            return newEntry;
        }

        private E remove(Entry<E> e) {
            if (e == header)
                throw new NoSuchElementException();

            E result = e.element;
            e.previous.next = e.next;
            e.next.previous = e.previous;
            e.next = e.previous = null;
            e.element = null;
            size--;
            modCount++;
            return result;
        }

        /**
         * Returns an array containing all of the elements in this list
         * in the correct order.
         *
         * @return an array containing all of the elements in this list
         * 	       in the correct order.
         */

        private static final long serialVersionUID = 876323262645176354L;

        /**
         * Save the state of this <tt>LinkedList</tt> instance to a stream (that
         * is, serialize it).
         *
         * @serialData The size of the list (the number of elements it
         *		   contains) is emitted (int), followed by all of its
         * elements (each an Object) in the proper order.
         */
        private void writeObject(java.io.ObjectOutputStream s)
                throws java.io.IOException {
            // Write out any hidden serialization magic
            s.defaultWriteObject();

            // Write out size
            s.writeInt(size);

            // Write out all elements in the proper order.
            for (Entry i = header.next; i != header; i = i.next)
                s.writeObject(i.element);
        }

        /**
         * Reconstitute this <tt>LinkedList</tt> instance from a stream (that is
         * deserialize it).
         */
        private void readObject(java.io.ObjectInputStream s)
                throws java.io.IOException, ClassNotFoundException {

            // Read in any hidden serialization magic
            s.defaultReadObject();

            // Read in size
            int size = s.readInt();

            // Initialize header
            header = new Entry<E>(null, null, null);
            header.next = header.previous = header;

            // Read in all elements in the proper order
            for (int i=0; i<size; i++)
                addBefore((E)s.readObject(), header);
        }
    }