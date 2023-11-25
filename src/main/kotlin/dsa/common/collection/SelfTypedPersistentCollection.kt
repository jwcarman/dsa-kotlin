package dsa.common.collection

import kotlinx.collections.immutable.PersistentCollection


interface SelfTypedPersistentCollection<S: SelfTypedPersistentCollection<S, E>, E>: PersistentCollection<E> {

    override fun add(element: E): S
    override fun clear(): S
    override fun remove(element: E): S

    @Suppress("UNCHECKED_CAST")
    override fun addAll(elements: Collection<E>): S = elements.fold(this as S) { acc, e -> acc.add(e) }

    override fun containsAll(elements: Collection<E>): Boolean = elements.all { contains(it) }

    override fun retainAll(elements: Collection<E>): S = removeAll { !elements.contains(it) }

    @Suppress("UNCHECKED_CAST")
    override fun removeAll(elements: Collection<E>): S = elements.fold(this as S) { acc, e -> acc.remove(e) }

    override fun removeAll(predicate: (E) -> Boolean): S = removeAll(filter { e -> predicate.invoke(e) }.toList())

    override fun builder(): PersistentCollection.Builder<E> {
        throw UnsupportedOperationException("Builder not supported")
    }
}