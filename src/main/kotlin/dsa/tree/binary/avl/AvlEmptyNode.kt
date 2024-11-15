package dsa.tree.binary.avl

import dsa.common.iterator.EmptyIterator

class AvlEmptyNode<E> : AvlNode<E> {

    override val value: E
        get() = throw NoSuchElementException("Empty nodes contain no value.")

    override val left: AvlNode<E>
        get() = this

    override val right: AvlNode<E>
        get() = this

    override fun iterator(): Iterator<E> = EmptyIterator

    override val size: Int
        get() = 0

    override val height: Int
        get() = 0

    override fun contains(element: E, comparator: Comparator<E>): Boolean = false

    override fun add(element: E, comparator: Comparator<E>): AvlNode<E> = AvlInnerNode(element, this, this)

    override fun remove(element: E, comparator: Comparator<E>): AvlNode<E> = this

    override fun rebalance(): AvlNode<E> = this
}