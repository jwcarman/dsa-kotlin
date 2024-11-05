package dsa.tree.binary.rbt

import kotlin.math.max

class BlackNode<E>(value: E, left: Node<E> = NilNode(), right: Node<E> = NilNode()) : InnerNode<E>(value, left, right) {

    override val blackHeight: Int = 1 + max(left.blackHeight, right.blackHeight)

    override val isRed: Boolean
        get() = false

    override fun insertToRight(element: E, comparator: Comparator<E>): Node<E> {
        val uncle = left
        val parent = right.insert(element, comparator)
        if (parent.isBlack) {
            return BlackNode(value, left, parent)
        }
        return when {
            uncle.isRed and (parent.left.isRed or parent.right.isRed) -> RedNode(value, left.toBlack(), parent.toBlack())
            uncle.isBlack and parent.right.isRed -> BlackNode(parent.value, RedNode(value, left, parent.left), parent.right)
            uncle.isBlack and parent.left.isRed -> BlackNode(parent.left.value, RedNode(value, left, parent.left.left), RedNode(parent.value, parent.left.right, parent.right))
            else -> BlackNode(value, left, parent)
        }
    }

    override fun insertToLeft(element: E, comparator: Comparator<E>): Node<E> {
        val uncle = right
        val parent = left.insert(element, comparator)
        if (parent.isBlack) {
            return BlackNode(value, parent, right)
        }
        return when {
            uncle.isRed and (parent.left.isRed or parent.right.isRed) -> RedNode(value, parent.toBlack(), right.toBlack())
            uncle.isBlack and parent.left.isRed -> BlackNode(parent.value, parent.left, RedNode(value, parent.right, right))
            uncle.isBlack and parent.right.isRed -> BlackNode(parent.right.value, RedNode(parent.value, parent.left, parent.right.left), RedNode(value, parent.right.right, right))
            else -> BlackNode(value, parent, right)
        }
    }

    override fun toBlack(): Node<E> = this
}