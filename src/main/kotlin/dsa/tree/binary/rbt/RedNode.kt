package dsa.tree.binary.rbt

import kotlin.math.max

class RedNode<E>(value: E, left: Node<E> = NilNode(), right: Node<E> = NilNode()) : InnerNode<E>(value, left, right) {

    override val blackHeight: Int = max(left.blackHeight, right.blackHeight)

    override val isRed: Boolean
        get() = true

    override fun insertToRight(element: E, comparator: Comparator<E>): Node<E> =
        RedNode(value, left, right.insert(element, comparator))

    override fun insertToLeft(element: E, comparator: Comparator<E>): Node<E> =
        RedNode(value, left.insert(element, comparator), right)

    override fun toBlack(): Node<E> = BlackNode(value, left, right)
}