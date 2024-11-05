package dsa.tree.binary.rbt


interface Node<E> {

    val value: E
    val left: Node<E>
    val right: Node<E>
    val isRed: Boolean
    val isBlack: Boolean
    val size: Int
    val blackHeight: Int
    val isEmpty: Boolean

    fun insert(element: E, comparator: Comparator<E>): Node<E>
    fun toBlack(): Node<E>
    fun iterator(): Iterator<E>
    fun contains(element: E, comparator: Comparator<E>): Boolean
}