package dsa.tree.binary.unbalanced

import dsa.tree.binary.AbstractBinaryTreeTest
import dsa.tree.binary.BinarySearchTree

class UnbalancedTreeTest: AbstractBinaryTreeTest() {

    override fun <T : Comparable<T>> emptyTree(): BinarySearchTree<T> = BinarySearchTree.unbalanced()

    override fun <T> emptyTree(comparator: Comparator<T>): BinarySearchTree<T> = BinarySearchTree.unbalanced(comparator)

    override fun <T: Comparable<T>> treeOf(vararg values: T): BinarySearchTree<T> = BinarySearchTree.unbalancedOf<T>(*values)

    override fun <T> treeOf(comparator: Comparator<T>, vararg values: T): BinarySearchTree<T> = BinarySearchTree.unbalancedOf(comparator, *values)

    override fun <T : Comparable<T>> treeFrom(values: Collection<T>): BinarySearchTree<T> =
        BinarySearchTree.unbalancedFrom(values)

    override fun <T> treeFrom(comparator: Comparator<T>, values: Collection<T>): BinarySearchTree<T> =
        BinarySearchTree.unbalancedFrom(comparator, values)
}