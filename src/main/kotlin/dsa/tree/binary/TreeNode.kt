package dsa.tree.binary

import dsa.common.iterator.ChainedIterator
import dsa.common.iterator.SingletonIterator

abstract class TreeNode<E>(
    private val comparator: Comparator<E>,
    final override val value: E,
    final override val left: BinarySearchTree<E>,
    final override val right: BinarySearchTree<E>
) : BinarySearchTree<E> {

    final override val size: Int = 1 + left.size + right.size

    final override val height: Int = 1 + maxOf(left.height, right.height)

    override fun add(element: E): BinarySearchTree<E> {
        val comparison = comparator.compare(element, this.value)
        return when {
            comparison == 0 -> throw IllegalArgumentException("Duplicate value $element not allowed")
            comparison < 0 -> createSubtree(comparator, this.value, left.add(element), right)
            else -> createSubtree(comparator, this.value, left, right.add(element))
        }
    }

    override fun clear(): BinarySearchTree<E> = emptyTree(comparator)

    override fun contains(element: E): Boolean {
        val comparison = comparator.compare(element, this.value)
        return when {
            comparison == 0 -> true
            comparison < 0 -> left.contains(element)
            else -> right.contains(element)
        }
    }

    override fun isEmpty(): Boolean = false

    override fun remove(element: E): BinarySearchTree<E> {
        val comparison = comparator.compare(element, this.value)
        return when {
            comparison == 0 -> removeSelf()
            comparison < 0 -> createSubtree(comparator, this.value, left.remove(element), right)
            else -> createSubtree(comparator, this.value, left, right.remove(element))
        }
    }

    private fun removeSelf(): BinarySearchTree<E>{
        return when {
            left.isEmpty() -> right
            right.isEmpty() -> left
            else -> {
                val successor = right.first()
                createSubtree(comparator, successor, left, right.remove(successor))
            }
        }
    }

    override fun iterator(): Iterator<E> = ChainedIterator(left.iterator(), SingletonIterator(value), right.iterator())

    abstract fun createSubtree(comparator: Comparator<E>, value: E, left: BinarySearchTree<E>, right: BinarySearchTree<E>): BinarySearchTree<E>

    abstract fun emptyTree(comparator: Comparator<E>): BinarySearchTree<E>
}