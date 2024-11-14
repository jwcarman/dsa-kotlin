package dsa.tree.binary.rbt

import dsa.common.iterator.EmptyIterator

class NilNode<E> : Node<E> {

    override val value: E
        get() = throw NoSuchElementException("Nil nodes are empty.")

    override val left: Node<E>
        get() = this

    override val right: Node<E>
        get() = this

    override val isRed: Boolean
        get() = false

    override val isBlack: Boolean
        get() = true

    override val size: Int
        get() = 0

    override val isEmpty: Boolean
        get() = true

    override val blackHeight: Int
        get() = 0

    override fun iterator(): Iterator<E> = EmptyIterator

    override fun toBlack(): Node<E> = this

    override fun insert(element: E, comparator: Comparator<E>): Node<E> = RedNode(element)

    override fun contains(element: E, comparator: Comparator<E>): Boolean = false
}