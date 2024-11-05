package dsa.tree.binary.rbt

import dsa.common.iterator.ChainedIterator
import dsa.common.iterator.SingletonIterator

abstract class InnerNode<E>(
    final override val value: E,
    final override val left: Node<E>,
    final override val right: Node<E>
) : Node<E> {

    final override val size: Int = 1 + left.size + right.size

    final override val isEmpty: Boolean
        get() = size == 0

    override val isBlack: Boolean
        get() = !isRed

    final override fun iterator(): Iterator<E> =
        ChainedIterator(left.iterator(), SingletonIterator(value), right.iterator())

    final override fun contains(element: E, comparator: Comparator<E>): Boolean {
        val comparison = comparator.compare(element, value)
        return when {
            comparison == 0 -> true
            comparison > 0 -> right.contains(element, comparator)
            else -> left.contains(element, comparator)
        }
    }

    final override fun insert(element: E, comparator: Comparator<E>): Node<E> {
        val comparison = comparator.compare(element, value)
        return when {
            comparison == 0 -> this
            comparison > 0 -> insertToRight(element, comparator)
            else -> insertToLeft(element, comparator)
        }
    }

    abstract fun insertToRight(element: E, comparator: Comparator<E>): Node<E>
    abstract fun insertToLeft(element: E, comparator: Comparator<E>): Node<E>
}