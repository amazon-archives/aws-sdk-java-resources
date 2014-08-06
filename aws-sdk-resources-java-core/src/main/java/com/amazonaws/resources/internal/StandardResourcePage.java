package com.amazonaws.resources.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.amazonaws.resources.ResourcePage;
import com.amazonaws.resources.ResultCapture;

/**
 * The standard implementation of {@code ResourcePage}.
 *
 * @param <E> the type of resource contained in the page
 */
public final class StandardResourcePage<E> implements ResourcePage<E> {

    private final ResourcePageImpl impl;
    private final ResourceCodec<E> codec;
    private final List<E> resources;

    public StandardResourcePage(
            ResourcePageImpl impl,
            ResourceCodec<E> codec) {

        this.impl = impl;
        this.codec = codec;

        List<E> list = new ArrayList<>(impl.getResources().size());
        for (ResourceImpl resource : impl.getResources()) {
            list.add(codec.transform(resource));
        }

        this.resources = Collections.unmodifiableList(list);
    }

    @Override
    public boolean hasNextPage() {
        return impl.hasNextPage();
    }

    @Override
    public ResourcePage<E> nextPage() {
        return nextPage(null);
    }

    @Override
    public ResourcePage<E> nextPage(ResultCapture<Object> extractor) {
        return new StandardResourcePage<E>(impl.nextPage(extractor), codec);
    }

    @Override
    public boolean contains(Object o) {
        return resources.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return resources.containsAll(c);
    }

    @Override
    public E get(int index) {
        return resources.get(index);
    }

    @Override
    public int indexOf(Object o) {
        return resources.indexOf(o);
    }

    @Override
    public boolean isEmpty() {
        return resources.isEmpty();
    }

    @Override
    public Iterator<E> iterator() {
        return resources.iterator();
    }

    @Override
    public int lastIndexOf(Object o) {
        return resources.lastIndexOf(o);
    }

    @Override
    public ListIterator<E> listIterator() {
        return resources.listIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return resources.listIterator(index);
    }

    @Override
    public int size() {
        return resources.size();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return resources.subList(fromIndex, toIndex);
    }

    @Override
    public Object[] toArray() {
        return resources.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return resources.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return resources.add(e);
    }

    @Override
    public void add(int index, E element) {
        resources.add(index, element);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return resources.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return resources.addAll(index, c);
    }

    @Override
    public void clear() {
        resources.clear();
    }

    @Override
    public boolean remove(Object o) {
        return resources.remove(o);
    }

    @Override
    public E remove(int index) {
        return resources.remove(index);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return resources.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return resources.retainAll(c);
    }

    @Override
    public E set(int index, E element) {
        return resources.set(index, element);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return resources.equals(obj);
    }

    @Override
    public int hashCode() {
        return resources.hashCode();
    }

    @Override
    public String toString() {
        return resources.toString();
    }
}
