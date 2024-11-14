package dsa.tree.binary.bst

import dsa.common.iterator.ChainedIterator
import dsa.common.iterator.SingletonIterator

class BstInnerNode<E>(
    private val element: E,
    private val left: BstNode<E>,
    private val right: BstNode<E>
) :
    BstNode<E> {

    override val size: Int = 1 + left.size + right.size

    override fun contains(element: E, comparator: Comparator<E>): Boolean {
        val comparison = comparator.compare(element, this.element)
        return when {
            comparison == 0 -> true
            comparison > 0 -> right.contains(element, comparator)
            else -> left.contains(element, comparator)
        }
    }

    override fun add(element: E, comparator: Comparator<E>): BstNode<E> {
        val comparison = comparator.compare(element, this.element)
        return when {
            comparison == 0 -> this
            comparison > 0 -> BstInnerNode(this.element, left, right.add(element, comparator))
            else -> BstInnerNode(this.element, left.add(element, comparator), right)
        }
    }

    override fun remove(element: E, comparator: Comparator<E>): BstNode<E> {
        val comparison = comparator.compare(element, this.element)
        return when {
            comparison == 0 -> removeSelf(comparator)
            comparison > 0 -> BstInnerNode(this.element, left, right.remove(element, comparator))
            else -> BstInnerNode(this.element, left.remove(element, comparator), right)
        }
    }

    private fun removeSelf(comparator: Comparator<E>): BstNode<E> {
        return when {
            left.isEmpty() -> right
            right.isEmpty() -> left
            else -> {
                val successor = right.first()
                BstInnerNode(successor, left, right.remove(successor, comparator))
            }
        }
    }

    override fun iterator(): Iterator<E> =
        ChainedIterator(left.iterator(), SingletonIterator(element), right.iterator())

    override fun isEmpty(): Boolean = false
}