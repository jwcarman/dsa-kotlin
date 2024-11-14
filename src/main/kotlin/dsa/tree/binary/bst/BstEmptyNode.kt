package dsa.tree.binary.bst

import dsa.common.iterator.EmptyIterator

class BstEmptyNode<E> : BstNode<E> {
    override val size: Int
        get() = 0

    override fun contains(element: E, comparator: Comparator<E>): Boolean = false

    override fun add(element: E, comparator: Comparator<E>): BstNode<E> = BstInnerNode(element, this, this)

    override fun remove(element: E, comparator: Comparator<E>): BstNode<E> = this

    override fun iterator(): Iterator<E> = EmptyIterator
}