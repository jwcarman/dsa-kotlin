package dsa.tree.binary.avl

import dsa.common.collection.SelfTypedPersistentSet
import dsa.tree.PersistentSetFactory
import kotlinx.collections.immutable.PersistentSet

class AvlTree<E> private constructor(
    private val root: AvlNode<E>,
    private val comparator: Comparator<E>
) : SelfTypedPersistentSet<AvlTree<E>, E> {

    override val size: Int
        get() = root.size

    override fun add(element: E): AvlTree<E> = AvlTree(root.add(element, comparator), comparator)

    override fun clear(): AvlTree<E> = AvlTree(AvlEmptyNode(), comparator)

    override fun remove(element: E): AvlTree<E> = AvlTree(root.remove(element, comparator), comparator)

    override fun contains(element: E): Boolean = root.contains(element, comparator)

    override fun isEmpty(): Boolean = root.isEmpty()

    override fun iterator(): Iterator<E> = root.iterator()

    companion object: PersistentSetFactory {

        override fun <E : Comparable<E>> create(): PersistentSet<E>  = AvlTree(AvlEmptyNode(), Comparator.naturalOrder())
        override fun <E> create(comparator: Comparator<E>): PersistentSet<E> = AvlTree(AvlEmptyNode(), comparator)
    }
}