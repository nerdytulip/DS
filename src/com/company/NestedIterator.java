package com.company;


import java.util.*;


// This is the interface that allows for creating nested lists.
  // You should not implement it, or speculate about its implementation
interface NestedInteger {

  // @return true if this NestedInteger holds a single integer, rather than a nested list.
  public boolean isInteger();

  // @return the single integer that this NestedInteger holds, if it holds a single integer
  // Return null if this NestedInteger holds a nested list
  public Integer getInteger();

  // @return the nested list that this NestedInteger holds, if it holds a nested list
  // Return empty list if this NestedInteger holds a single integer
  public List<NestedInteger> getList();
  }

public class NestedIterator implements Iterator<Integer> {

    private Deque<ListIterator<NestedInteger>> stackOfIterators = new ArrayDeque();
    private Integer peeked = null;

    public NestedIterator(List<NestedInteger> nestedList) {
        stackOfIterators.addFirst(nestedList.listIterator());
    }

    private void setPeeked() {
        if(peeked!=null) return;

        while(!stackOfIterators.isEmpty()){
            if(!stackOfIterators.peekFirst().hasNext()){
                stackOfIterators.removeFirst();
                continue;
            }

            NestedInteger next = stackOfIterators.peekFirst().next();

            if(next.isInteger()){
                peeked = next.getInteger();
                return;
            }

            stackOfIterators.addFirst(next.getList().listIterator());
        }
    }


    @Override
    public Integer next() {
        // As per Java specs, throw an exception if there are no further elements.
        if (!hasNext()) throw new NoSuchElementException();

        // hasNext() called setPeeked(), which ensures peeked has the next integer
        // in it. We need to clear the peeked field so that the element is returned
        // again.
        Integer result = peeked;
        peeked = null;
        return result;
    }

    @Override
    public boolean hasNext() {
        // Try to set the peeked field. If any integers are remaining, it will
        // contain the next one to be returned after this call.
        setPeeked();

        // There are elements remaining iff peeked contains a value.
        return peeked != null;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */

