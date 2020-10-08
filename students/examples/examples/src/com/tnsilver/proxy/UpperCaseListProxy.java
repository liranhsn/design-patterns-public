package com.tnsilver.proxy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class UpperCaseListProxy implements List<String> {

	private List<String> list;

	public UpperCaseListProxy(List<String> list) {
		super();
		this.list = new ArrayList<String>();
		if (list != null)
			list.forEach(e -> this.list.add(e.toUpperCase()));
	}

	public static void main(String[] args) {
		List<String> list = List.of("tomer", "Silverman", "the", "Instructor");
		UpperCaseListProxy proxy = new UpperCaseListProxy(list);
		proxy.add("lowerCaseElement");
		System.out.println(proxy);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o.toString().toUpperCase());
	}

	@Override
	public Iterator<String> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}

	@Override
	public boolean add(String e) {
		return list.add(e.toUpperCase());
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o.toString().toUpperCase());
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends String> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends String> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public String get(int index) {
		return list.get(index);
	}

	@Override
	public String set(int index, String element) {
		return null;
	}

	@Override
	public void add(int index, String element) {
	}

	@Override
	public String remove(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}

	@Override
	public ListIterator<String> listIterator() {
		return null;
	}

	@Override
	public ListIterator<String> listIterator(int index) {
		return null;
	}

	@Override
	public List<String> subList(int fromIndex, int toIndex) {
		return null;
	}

	@Override
	public String toString() {
		return "UpperCaseListProxy [list=" + list + "]";
	}

}
