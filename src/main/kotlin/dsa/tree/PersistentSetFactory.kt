package dsa.tree

import kotlinx.collections.immutable.PersistentSet

interface PersistentSetFactory {
    fun <E> create(comparator: Comparator<E>): PersistentSet<E>
    fun <E : Comparable<E>> create(): PersistentSet<E>
    fun <E: Comparable<E>> of(vararg elements: E): PersistentSet<E> = create<E>().addAll(elements.toList())
    fun <E> of(comparator: Comparator<E>, vararg elements: E): PersistentSet<E> = create(comparator).addAll(elements.toList())
    fun <E: Comparable<E>> from(collection: Collection<E>): PersistentSet<E> = create<E>().addAll(collection)
    fun <E> from(comparator: Comparator<E>, collection: Collection<E>): PersistentSet<E> = create(comparator).addAll(collection)
}