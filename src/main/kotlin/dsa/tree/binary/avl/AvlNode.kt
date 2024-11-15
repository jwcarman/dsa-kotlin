package dsa.tree.binary.avl

interface AvlNode<E> : Iterable<E> {

    val value: E
    val left: AvlNode<E>
    val right: AvlNode<E>
    val size: Int
    val height: Int

    fun contains(element: E, comparator: Comparator<E>): Boolean
    fun add(element: E, comparator: Comparator<E>): AvlNode<E>
    fun remove(element: E, comparator: Comparator<E>): AvlNode<E>

    fun rebalance(): AvlNode<E>

    fun isEmpty(): Boolean = size == 0
    fun isLeftWeighted(): Boolean = left.height > right.height
    fun isRightWeighted(): Boolean = right.height > left.height
}