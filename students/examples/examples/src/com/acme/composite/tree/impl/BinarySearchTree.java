/*
 * Copyright (C) 2014 - 2020 T.N.Silverman, All rights reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.acme.composite.tree.impl;

import java.util.Arrays;

import com.acme.composite.tree.api.ITree;
import com.acme.composite.tree.api.ITreeNode;

/**
 * The class BinarySearchTree is a BST implementation of
 * {@link AbstractBinaryTree}
 *
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> extends AbstractBinaryTree<T> {

	public BinarySearchTree(Class<T> clazz, T key) {
		super(clazz, key);
	}

	public BinarySearchTree(Class<T> clazz) {
		super(clazz);
	}

	/**
	 * binary search tree algorithm
	 */
	@Override
	public ITreeNode<T> search(T key) {
		if (root == null || key == null)
			return null;
		if (key.compareTo(root.key()) == 0)
			return root;
		else if (key.compareTo(root.key()) < 0)
			return search(root.left(), key);
		else
			return search(root.right(), key);
	}

	/**
	 * base delete algorithm for binary search tree with 3 cases:
	 *
	 * 1. node is leaf -> remove from tree
	 *
	 * 2. node has 1 child -> copy child key to node and delete child
	 *
	 * 3. node has 2 children -> find in order successor (min in right subtree) copy
	 * it to the node and delete it
	 */
	protected ITreeNode<T> delete(ITreeNode<T> root, T key) {
		if (root == null) // base case: if the tree is empty
			return root;
		if (key.compareTo(root.key()) < 0) // otherwise, recurse down the tree
			root.setLeft(delete(root.left(), key));
		else if (key.compareTo(root.key()) > 0)
			root.setRight(delete(root.right(), key));
		else { // if key is same as root's key, then this is the node to be deleted
			if (root.left() == null) // node with only one child or no child
				return root.right();
			else if (root.right() == null)
				return root.left();
			// node with two children:
			root.setKey(min(root.right())); // get the inorder successor (smallest in the right subtree)
			root.setRight(delete(root.right(), root.key())); // delete the inorder successor
		}
		return root;
	}

	@Override
	public ITree<T> delete(T key) {
		root = delete(root, key);
		return this;
	}

	@Override
	public T min() {
		ITreeNode<T> current = root;
		/* loop down to find the leftmost leaf */
		while (current != null && current.left() != null)
			current = current.left();
		return (current == null ? null : current.key());
	}

	@Override
	public T max() {
		ITreeNode<T> current = root;
		/* loop down to find the rightmost leaf */
		while (current != null && current.right() != null)
			current = current.right();
		return (current == null ? null : current.key());
	}

	/**
	 * 1. create in order array of the BT
	 *
	 * 2. sort the array
	 *
	 * 3. do in order traversal and copy array elements to nodes one by one
	 */
	protected void copyInOrder(ITreeNode<T> node, T[] arr, int[] i) {
		if (node == null)
			return;
		copyInOrder(node.left(), arr, i);
		node.setKey(arr[i[0]++]);
		copyInOrder(node.right(), arr, i);
	}

	protected void convertToBST() {
		T[] arr = inOrder();
		Arrays.sort(arr);
		copyInOrder(root, arr, new int[] { 0 });
	}

	public static void main(String[] args) {
		ITree<Integer> bst = new BinarySearchTree<>(Integer.class, 1);
		bst.root().setLeft(new TreeNode<>(2));
		bst.root().setRight(new TreeNode<>(3));
		bst.root().left().setLeft(new TreeNode<>(4));
		bst.root().left().setRight(new TreeNode<>(5));
		bst.root().right().setLeft(new TreeNode<>(6));
		bst.root().right().setRight(new TreeNode<>(7));
		bst.root().right().left().setRight(new TreeNode<>(8));
		bst.print();
		for (int i = 1; i < 9; i++)
			System.out.printf("level of key %s: %s%n", i, bst.level(bst.root(), i, 0));
		System.out.println("Dist(4, 5) = " + bst.distance(bst.root(), 4, 5));
		System.out.println("Dist(4, 6) = " + bst.distance(bst.root(), 4, 6));
		System.out.println("Dist(3, 4) = " + bst.distance(bst.root(), 3, 4));
		System.out.println("Dist(2, 4) = " + bst.distance(bst.root(), 2, 4));
		System.out.println("Dist(8, 5) = " + bst.distance(bst.root(), 8, 5));
	}
}
