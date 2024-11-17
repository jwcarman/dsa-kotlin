package dsa.tree.binary.rbt

import dsa.common.iterator.ChainedIterator
import dsa.common.iterator.SingletonIterator
import dsa.tree.binary.rbt.RedBlackNode.Color.BLACK
import dsa.tree.binary.rbt.RedBlackNode.Color.RED

class InnerNode<E>(
    override val color: RedBlackNode.Color,
    override val value: E,
    override val left: RedBlackNode<E>,
    override val right: RedBlackNode<E>
) : RedBlackNode<E> {

    override val size: Int = 1 + left.size + right.size

    override val blackHeight: Int = color.blackHeight() + maxOf(left.blackHeight, right.blackHeight)

    override fun iterator(): Iterator<E> = ChainedIterator(left.iterator(), SingletonIterator(value), right.iterator())

    override fun toBlack(): RedBlackNode<E> = InnerNode(BLACK, value, left, right)

    override fun isNil() = false

    override fun add(element: E, comparator: Comparator<E>): RedBlackNode<E> {
        val comparison = comparator.compare(element, value)
        return when {
            comparison == 0 -> this
            comparison < 0 -> addToLeft(element, comparator)
            else -> addToRight(element, comparator)
        }
    }

    private fun addToRight(element: E, comparator: Comparator<E>): RedBlackNode<E> {
        val uncle = left
        val parent = right.add(element, comparator)

        return when {
            isRed() -> copy(right = parent)
            parent.isBlack() -> copy(right = parent)
            uncle.isRed() and (parent.left.isRed() or parent.right.isRed()) -> copy(
                color = RED,
                left = left.toBlack(),
                right = parent.toBlack()
            )

            uncle.isBlack() and parent.right.isRed() -> InnerNode(
                BLACK,
                parent.value,
                InnerNode(RED, value, left, parent.left),
                parent.right
            )

            uncle.isBlack() and parent.left.isRed() -> InnerNode(
                BLACK,
                parent.left.value,
                InnerNode(RED, value, left, parent.left.left),
                InnerNode(RED, parent.value, parent.left.right, parent.right)
            )

            else -> copy(right = parent)
        }

    }

    private fun addToLeft(element: E, comparator: Comparator<E>): RedBlackNode<E> {
        val uncle = right
        val parent = left.add(element, comparator)

        return when {
            isRed() -> copy(left = parent)
            parent.isBlack() -> copy(left = parent)
            uncle.isRed() and (parent.left.isRed() or parent.right.isRed()) -> copy(
                color = RED,
                left = parent.toBlack(),
                right = right.toBlack()
            )

            uncle.isBlack() and parent.left.isRed() -> InnerNode(
                BLACK,
                parent.value,
                parent.left,
                InnerNode(RED, value, parent.right, right)
            )

            uncle.isBlack() and parent.right.isRed() -> InnerNode(
                BLACK,
                parent.right.value,
                InnerNode(RED, parent.value, parent.left, parent.right.left),
                InnerNode(RED, value, parent.right.right, right)
            )

            else -> copy(left = parent)
        }
    }

    override fun balance(): RedBlackNode<E> {
        return when {
            right.isRed() && !left.isRed() -> rotateLeft()
            left.isRed() && (left as InnerNode).left.isRed() -> rotateRight()
            left.isRed() && right.isRed() -> recolor()
            else -> this
        }
    }

    private fun rotateLeft(): RedBlackNode<E> {
        val newRoot = right as InnerNode
        return newRoot.copy(
            color = this.color, left = copy(color = RedBlackNode.Color.RED, right = newRoot.left)
        )
    }

    private fun rotateRight(): RedBlackNode<E> {
        val newRoot = left as InnerNode
        return newRoot.copy(
            color = this.color, right = copy(color = RedBlackNode.Color.RED, left = newRoot.right)
        )
    }

    private fun recolor(): RedBlackNode<E> = copy(
        color = RedBlackNode.Color.RED, left = left.toBlack(), right = right.toBlack()
    )

    override fun remove(element: E, comparator: Comparator<E>): RedBlackNode<E> {
        val comparison = comparator.compare(element, value)
        val newNode = when {
            comparison == 0 -> removeSelf(comparator)
            comparison < 0 -> removeFromLeft(element, comparator)
            else -> removeFromRight(element, comparator)
        }
        return newNode.balance()
    }

    private fun removeFromRight(element: E, comparator: Comparator<E>) = copy(right = right.remove(element, comparator))

    private fun removeFromLeft(element: E, comparator: Comparator<E>) = copy(left = left.remove(element, comparator))

    private fun removeSelf(comparator: Comparator<E>) = when {
        left.isNil() -> right
        right.isNil() -> left
        else -> {
            val successor = right.first()
            copy(value = successor, right = right.remove(successor, comparator))
        }
    }

    override fun contains(element: E, comparator: Comparator<E>): Boolean {
        val comparison = comparator.compare(element, value)
        return when {
            comparison == 0 -> true
            comparison > 0 -> right.contains(element, comparator)
            else -> left.contains(element, comparator)
        }
    }

    private fun copy(
        color: RedBlackNode.Color = this.color,
        value: E = this.value,
        left: RedBlackNode<E> = this.left,
        right: RedBlackNode<E> = this.right
    ): InnerNode<E> = InnerNode(color, value, left, right)
}