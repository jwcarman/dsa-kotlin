package dsa.tree.binary.rbt

interface RedBlackNode<E> : Iterable<E> {

    enum class Color {
        RED {
            override fun blackHeight(): Int = 0
        },
        BLACK {
            override fun blackHeight(): Int = 1
        };

        abstract fun blackHeight(): Int
    }

    val color: Color
    val value: E
    val left: RedBlackNode<E>
    val right: RedBlackNode<E>
    val size: Int
    val blackHeight: Int

    fun isRed(): Boolean = color == Color.RED

    fun isNil(): Boolean
    fun toBlack(): RedBlackNode<E>
    fun balance(): RedBlackNode<E>
    fun add(element: E, comparator: Comparator<E>): RedBlackNode<E>
    fun remove(element: E, comparator: Comparator<E>): RedBlackNode<E>
    fun contains(element: E, comparator: Comparator<E>): Boolean
}