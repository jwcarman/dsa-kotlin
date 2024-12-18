package dsa.tree.binary.avl

import dsa.common.iterator.ChainedIterator
import dsa.common.iterator.SingletonIterator

private const val RIGHT_UNBALANCED = 2
private const val LEFT_UNBALANCED = -2


class AvlInnerNode<E>(
    override val value: E,
    override val left: AvlNode<E>,
    override val right: AvlNode<E>
) : AvlNode<E> {

    override val size: Int = 1 + left.size + right.size
    override val height: Int = 1 + maxOf(left.height, right.height)

    override fun iterator(): Iterator<E> =
        ChainedIterator(left.iterator(), SingletonIterator(value), right.iterator())

    override fun contains(element: E, comparator: Comparator<E>): Boolean {
        val comparison = comparator.compare(element, this.value)
        return when {
            comparison == 0 -> true
            comparison > 0 -> right.contains(element, comparator)
            else -> left.contains(element, comparator)
        }
    }

    override fun add(element: E, comparator: Comparator<E>): AvlNode<E> {
        val comparison = comparator.compare(element, this.value)
        return when {
            comparison == 0 -> this
            comparison > 0 -> AvlInnerNode(this.value, left, right.add(element, comparator)).rebalance()
            else -> AvlInnerNode(this.value, left.add(element, comparator), right).rebalance()
        }
    }


    override fun remove(element: E, comparator: Comparator<E>): AvlNode<E> {
        val comparison = comparator.compare(element, this.value)
        return when {
            comparison == 0 -> removeSelf(comparator).rebalance()
            comparison > 0 -> AvlInnerNode(this.value, left, right.remove(element, comparator)).rebalance()
            else -> AvlInnerNode(this.value, left.remove(element, comparator), right).rebalance()
        }
    }

    private fun removeSelf(comparator: Comparator<E>): AvlNode<E> {
        return when {
            left.isEmpty() -> right
            right.isEmpty() -> left
            else -> {
                val successor = right.first()
                AvlInnerNode(successor, left, right.remove(successor, comparator))
            }
        }
    }

    override fun rebalance(): AvlNode<E> {
        val balance = right.height - left.height
        return when (balance) {
            RIGHT_UNBALANCED -> rotateLeft()
            LEFT_UNBALANCED -> rotateRight()
            else -> this
        }
    }

    private fun rotateLeft(): AvlNode<E> {
        return if (right.isRightWeighted()) {
            leftLeftRotation()
        } else {
            rightLeftRotation()
        }
    }

    private fun rightLeftRotation(): AvlNode<E> = AvlInnerNode(
        right.left.value,
        AvlInnerNode(value, left, right.left.left),
        AvlInnerNode(right.value, right.left.right, right.right)
    )

    private fun leftLeftRotation(): AvlNode<E> = AvlInnerNode(
        right.value,
        AvlInnerNode(value, left, right.left),
        right.right
    )

    private fun rotateRight(): AvlNode<E> {
        return if (left.isLeftWeighted()) {
            rightRightRotation()
        } else {
            leftRightRotation()
        }
    }

    private fun leftRightRotation(): AvlNode<E> = AvlInnerNode(
        left.right.value,
        AvlInnerNode(left.value, left.left, left.right.left),
        AvlInnerNode(value, left.right.right, right)
    )

    private fun rightRightRotation(): AvlNode<E> = AvlInnerNode(
        left.value,
        left.left,
        AvlInnerNode(value, left.right, right)
    )

}