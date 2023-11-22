package dsa.tree.bst

import dsa.common.EmptyIterator
import dsa.tree.Tree

internal class EmptyTree<T>(private val comparator: Comparator<T>) : Tree<T> {
    override val size = 0
    override fun contains(element: T): Boolean = false
    override fun containsAll(elements: Collection<T>): Boolean = elements.isEmpty()
    override fun isEmpty(): Boolean = true
    override fun add(element: T): Tree<T> = BinaryTree(comparator, element)

    override fun clear(): Tree<T> = this
    override fun remove(element: T): Tree<T> = this
    override fun removeAll(predicate: (T) -> Boolean): Tree<T> = this
    override fun removeAll(elements: Collection<T>): Tree<T> = this
    override fun retainAll(elements: Collection<T>): Tree<T> = this
    override fun iterator(): Iterator<T> = EmptyIterator()
}