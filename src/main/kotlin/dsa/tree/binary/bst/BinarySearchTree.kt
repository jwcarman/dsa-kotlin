package dsa.tree.binary.bst

import dsa.common.collection.SelfTypedPersistentSet
import dsa.tree.PersistentSetFactory
import kotlinx.collections.immutable.PersistentSet

class BinarySearchTree<E> private constructor(
    private val root: BstNode<E>,
    private val comparator: Comparator<E>
) : SelfTypedPersistentSet<BinarySearchTree<E>, E> {

    override val size: Int
        get() = root.size

    override fun add(element: E): BinarySearchTree<E> = BinarySearchTree(root.add(element, comparator), comparator)

    override fun clear(): BinarySearchTree<E> = BinarySearchTree(BstEmptyNode(), comparator)

    override fun remove(element: E): BinarySearchTree<E> =
        BinarySearchTree(root.remove(element, comparator), comparator)

    override fun contains(element: E): Boolean = root.contains(element, comparator)

    override fun isEmpty(): Boolean = root.isEmpty()

    override fun iterator(): Iterator<E> = root.iterator()

    companion object: PersistentSetFactory {

        override fun <E : Comparable<E>> create(): PersistentSet<E> = BinarySearchTree(BstEmptyNode(), Comparator.naturalOrder())
        override fun <E> create(comparator: Comparator<E>): PersistentSet<E> = BinarySearchTree(BstEmptyNode(), comparator)
    }
}