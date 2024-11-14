package dsa.tree.binary.bst

interface BstNode<E> : Iterable<E> {
    val size: Int
    fun contains(element: E, comparator: Comparator<E>): Boolean
    fun add(element: E, comparator: Comparator<E>): BstNode<E>
    fun remove(element: E, comparator: Comparator<E>): BstNode<E>
    fun isEmpty(): Boolean = size == 0
}