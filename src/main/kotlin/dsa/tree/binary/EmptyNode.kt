package dsa.tree.binary


import dsa.common.iterator.EmptyIterator

abstract class EmptyNode<E>(private val comparator: Comparator<E>): BinarySearchTree<E> {

    override val height: Int = 0

    override val size = 0

    override val left: BinarySearchTree<E>
        get() = this
    override val right: BinarySearchTree<E>
        get() = this
    override val value: E
        get() = throw UnsupportedOperationException("Empty tree has no value")
    override fun contains(element: E): Boolean = false
    override fun containsAll(elements: Collection<E>): Boolean = elements.isEmpty()
    override fun isEmpty(): Boolean = true
    override fun add(element: E): BinarySearchTree<E> = createLeafNode(comparator, element)
    override fun clear(): BinarySearchTree<E> = this
    override fun remove(element: E): BinarySearchTree<E> = this
    override fun removeAll(predicate: (E) -> Boolean): BinarySearchTree<E> = this
    override fun removeAll(elements: Collection<E>): BinarySearchTree<E> = this
    override fun retainAll(elements: Collection<E>): BinarySearchTree<E> = this
    override fun iterator(): Iterator<E> = EmptyIterator()
    abstract fun createLeafNode(comparator: Comparator<E>, value: E): BinarySearchTree<E>
}