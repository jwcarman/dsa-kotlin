package dsa.tree.binary.rbt

import dsa.common.iterator.EmptyIterator

class NilNode<E> : RedBlackNode<E> {

    override fun iterator(): Iterator<E> = EmptyIterator

    override val color: RedBlackNode.Color
        get() = RedBlackNode.Color.BLACK

    override val value: E
        get() = throw NoSuchElementException("Nil nodes are empty.")

    override val left: RedBlackNode<E>
        get() = this

    override val right: RedBlackNode<E>
        get() = this

    override val size: Int
        get() = 0

    override val blackHeight: Int
        get() = 0

    override fun isNil(): Boolean = true

    override fun balance(): RedBlackNode<E> = this

    override fun add(element: E, comparator: Comparator<E>): RedBlackNode<E> =
        InnerNode(RedBlackNode.Color.RED, element, this, this)

    override fun remove(element: E, comparator: Comparator<E>): RedBlackNode<E> = this

    override fun contains(element: E, comparator: Comparator<E>): Boolean = false

    override fun toBlack(): RedBlackNode<E> = this
}