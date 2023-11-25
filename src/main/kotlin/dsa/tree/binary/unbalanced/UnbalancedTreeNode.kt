package dsa.tree.binary.unbalanced

import dsa.tree.binary.BinarySearchTree
import dsa.tree.binary.TreeNode

internal class UnbalancedTreeNode<E>(
    comparator: Comparator<E>,
    value: E,
    left: BinarySearchTree<E>,
    right: BinarySearchTree<E>
) : TreeNode<E>(comparator, value, left, right) {

    override fun createSubtree(
        comparator: Comparator<E>,
        value: E,
        left: BinarySearchTree<E>,
        right: BinarySearchTree<E>
    ): BinarySearchTree<E> = UnbalancedTreeNode<E>(comparator, value, left, right)

    override fun emptyTree(comparator: Comparator<E>): BinarySearchTree<E> = UnbalancedEmptyNode(comparator)
}