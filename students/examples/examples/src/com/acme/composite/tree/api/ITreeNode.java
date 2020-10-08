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
 * The interface ITreeNode defines the functionality of a tree node
 *
 * @param <T> the type of key
 */
public interface ITreeNode<T extends Comparable<T>> {

	/**
	 * return the height of the sub-tree rooted at this node
	 *
	 * @return the height of the sub-tree rooted at this node
	 */
	int height();

	/**
	 * return the size of the sub-tree rooted at this node
	 *
	 * @return the size of the sub-tree rooted at this node
	 */
	int size();

	/**
	 * provide a means to visually view the sub-trr rooted at this node
	 */
	void print();

	/**
	 * return this nodes key;
	 *
	 * @return this nodes key;
	 */
	T key();

	/**
	 * sets this key value
	 *
	 * @param value
	 */
	void setKey(T value);

	/**
	 * return this nodes right node
	 *
	 * @return this nodes right node
	 */
	ITreeNode<T> right();

	/**
	 * sets this right node
	 *
	 * @param right the node to set
	 */
	void setRight(ITreeNode<T> right);

	/**
	 * return this nodes left node
	 *
	 * @return this nodes left node
	 */
	ITreeNode<T> left();

	/**
	 * sets this left node
	 *
	 * @param left the node to set
	 */
	void setLeft(ITreeNode<T> left);

	/*
	 * return the next in order successor node
	 */
	ITreeNode<T> inOrderSuccessor();
}
