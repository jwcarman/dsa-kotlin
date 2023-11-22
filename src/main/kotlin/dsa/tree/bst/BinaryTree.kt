package dsa.tree.bst

import dsa.common.ChainedIterator
import dsa.common.SingletonIterator
import dsa.tree.Tree

internal class BinaryTree<T>(
    private val comparator: Comparator<T>,
    private val value: T,
    private val left: Tree<T>,
    private val right: Tree<T>
) : Tree<T> {

    constructor(comparator: Comparator<T>, value: T) : this(
        comparator,
        value,
        EmptyTree(comparator),
        EmptyTree(comparator)
    )

    override val size: Int = 1 + left.size + right.size
    override fun isEmpty(): Boolean = false
    override fun contains(element: T): Boolean {
        val comparison = comparator.compare(element, this.value)
        return when {
            comparison == 0 -> true
            comparison < 0 -> left.contains(element)
            else -> right.contains(element)
        }
    }

    override fun add(element: T): Tree<T> {
        val comparison = comparator.compare(element, this.value)
        return when {
            comparison == 0 -> throw IllegalArgumentException("Duplicate value $element")
            comparison < 0 -> BinaryTree(comparator, this.value, left.add(element), right)
            else -> BinaryTree(comparator, this.value, left, right.add(element))
        }
    }

    override fun iterator(): Iterator<T> = ChainedIterator(left.iterator(), SingletonIterator(value), right.iterator())
    override fun clear(): Tree<T> = EmptyTree(comparator)
    override fun remove(element: T): Tree<T> {
        val comparison = comparator.compare(element, this.value)
        return when {
            comparison == 0 -> removeSelf()
            comparison < 0 -> BinaryTree(comparator, this.value, left.remove(element), right)
            else -> BinaryTree(comparator, this.value, left, right.remove(element))
        }
    }

    private fun removeSelf(): Tree<T> {
        return when {
            left.isEmpty() -> right
            right.isEmpty() -> left
            else -> {
                val successor = right.first()
                BinaryTree(comparator, successor, left, right.remove(successor))
            }
        }
    }


    companion object {

        fun <T : Comparable<T>> empty(): Tree<T> = EmptyTree(Comparator.naturalOrder())
        fun <T : Comparable<T>> from(elements: Iterable<T>): Tree<T> = empty<T>().addAll(elements.toList())
        fun <T : Comparable<T>> of(vararg elements: T): Tree<T> = from(elements.toList())

        fun <T> empty(comparator: Comparator<T>): Tree<T> = EmptyTree(comparator)

        fun <T> from(comparator: Comparator<T>, elements: Iterable<T>): Tree<T> =
            empty(comparator).addAll(elements.toList())

        fun <T> of(comparator: Comparator<T>, vararg elements: T): Tree<T> = from(comparator, elements.toList())


    }
}