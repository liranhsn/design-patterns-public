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
package com.acme.composite.tree.api;

/**
 * The interface ITree defines the functionality of a tree
 *
 * @param <T> the type of node key
 */
public interface ITree<T extends Comparable<T>> {

	/**
	 * a method to insert a node into the tree
	 *
	 * @param key the value to insert
	 * @return the modified tree
	 */
	ITree<T> insert(T key);

	/**
	 * a method to delete a node from a tree
	 *
	 * @param key the value of the node to delete
	 * @return the modified tree
	 */
	ITree<T> delete(T key);

	/**
	 * A method to obtain an in order array (LEFT,ROOT,RIGHT representation of the
	 * tree
	 *
	 * @return the in order array representation of the tree
	 */
	T[] inOrder();

	/**
	 * A method to obtain an in order array (LEFT,ROOT,RIGHT representation of the
	 * tree starting with the given node
	 *
	 * @return the in order array representation of the tree
	 */
	T[] inOrder(ITreeNode<T> node);

	/**
	 * A method to obtain a pre order array (ROOT,LEFT, RIGHT representation of the
	 * tree
	 *
	 * @return the pre order array representation of the tree
	 */
	T[] preOrder();

	/**
	 * A method to obtain a post order array (LEFT, RIGHT, ROOT) representation of
	 * the tree
	 *
	 * @return the post order array representation of the tree
	 */
	T[] postOrder();

	/**
	 * A method to obtain a level order array (breadth first) representation of the
	 * tree
	 *
	 * @return the level order array (breadth first) array representation of the
	 *         tree
	 */
	T[] levelOrder();

	/**
	 * A method to obtain the minimum value of a node in a tree
	 *
	 * @return the minimum value of a node in the tree
	 */
	T min();

	/**
	 * A method to obtain the maximal value of a node in a tree
	 *
	 * @return the maximum value of a node in the tree
	 */
	T max();

	/**
	 * A method to obtain the in order successor tree node of the current node,
	 * which is typically the node with the smallest key in the right subtree
	 *
	 * In Binary Tree, Inorder successor of a node is the next node in Inorder
	 * traversal of the Binary Tree.
	 *
	 * Inorder Successor is NULL for the last node in In Oorder traversal.
	 *
	 * In Binary Search Tree, Inorder Successor of an input node can also be defined
	 * as the node with the smallest key greater than the key of input node.
	 *
	 * @param node the root node
	 * @return A method to obtain the in order successor tree node of the current
	 *         node, which is typically the node with the smallest key in the right
	 *         subtree, or null if the tree is empty
	 */
	ITreeNode<T> inOrderSuccessor(ITreeNode<T> node);

	/**
	 * A method to obtain the height of the root node of a tree
	 *
	 * @return the height of the tree
	 */
	int height();

	/**
	 * A method to obtain the number of nodes in a tree
	 *
	 * @return the number of nodes in a tree
	 */
	int size();

	/**
	 * a method to search a node with a given key
	 *
	 * @param key the key of the node to search
	 * @return the node with the given key or null if none found
	 */
	ITreeNode<T> search(T key);

	/**
	 * A method to obtain the root node of a tree
	 *
	 * @return the root node of a tree
	 */
	ITreeNode<T> root();

	/**
	 * a method to find the deepest and last node of a tree
	 *
	 * @return the deepest and last node of a tree or null if the tree is empty
	 */
	ITreeNode<T> last();

	/**
	 * A method to print the representation of a tree
	 */
	void print();

	/**
	 * return true is this is a full tree
	 *
	 * @return true is this is a full tree, otherwise false
	 */
	boolean isFull();

	/**
	 * returns true if this tree root node is null
	 *
	 * @return true if this tree root node is null, otherwise false
	 */
	boolean isEmpty();

	/**
	 * The lowest common ancestor between two nodes n1 and n2 is defined as the
	 * lowest node in T that has both n1 and n2 as descendants (where we allow a
	 * node to be a descendant of itself).
	 *
	 * @param root the root of the sub-tree
	 * @param key1 the first node's key
	 * @param key2 the second node's key
	 * @return The lowest common ancestor between two nodes
	 */
	ITreeNode<T> lowestCommonAncestor(ITreeNode<T> root, T key1, T key2);

	/**
	 * Returns level of key if it is present in tree, otherwise returns -1
	 *
	 * @param root  the root of the sub-tree
	 * @param key   the key
	 * @param level the level - starts at 0 with root
	 * @return level of key if it is present in tree, otherwise returns -1
	 */
	int level(ITreeNode<T> root, T key, int level);

	/**
	 * returns the distance between two nodes represented by key1 and key2. First
	 * find LCA of two nodes. Then we find distance from LCA to two nodes.
	 *
	 * @param root the root of this sub-tree
	 * @param key1 the first key
	 * @param key2 the second key
	 * @return the distance between two nodes represented by key1 and key2
	 */
	int distance(ITreeNode<T> root, T key1, T key2);
}
