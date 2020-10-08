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
package com.acme.flyweight;

import java.awt.Color;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

/**
 * a CircleFactory class to customize it by using flyweight design pattern.
 * Since we just draw circle with different colors, we can store color info in a
 * hashmap. If a circle has been drawn, the new circle will be checked with
 * color. If the circle with the same color has been found in the hashmap, the
 * circle will share the instance which is picked up from the hashmap instead of
 * creating a new one. We will reuse the object with different state, that is to
 * say we will share the instance and draw the circle with different start
 * position and radius on the fly.
 */
public class CircleFactory {

	static final ReferenceQueue<Circle> REF_QUEUE = new ReferenceQueue<>();
	static final AtomicLong NUM_OF_REQUESTS = new AtomicLong();
	static final AtomicLong NUM_OF_GC = new AtomicLong();
	private static final HashMap<Object, WeakReference<Circle>> cache = new HashMap<>();
	private static final Color colors[] = {
			Color.red,
			Color.blue,
			Color.yellow,
			Color.orange,
			Color.gray,
			Color.white,
			Color.cyan,
			Color.darkGray,
			Color.green,
			Color.magenta,
			Color.pink };
	private static final Random random = ThreadLocalRandom.current();
	private static final long MEGABYTE = 1024L * 1024L;

	public static Circle getCircle(boolean flyweight) {
		Circle circle = null;
		Color color = getRandomColor();
		if (flyweight) { // flyweight circles only use colors for state, so color is the cache key
			WeakReference<Circle> ref = cache.get(color);
			if (ref != null)
				circle = ref.get();
			if (circle == null) {
				circle = new Circle(color);
				cache.put(color, new WeakReference<>(circle, REF_QUEUE));
			}
		} else { // none flyweight circles use location and radix data, as well as color, so they
					// have a special key
			Integer x = getRandomX();
			Integer y = getRandomY();
			Integer r = getRandomR();
			CircleKey key = new CircleKey(color, x, y, r);
			WeakReference<Circle> ref = cache.get(key);
			if (ref != null)
				circle = ref.get();
			if (circle == null) {
				circle = new Circle(color, x, y, r);
				cache.put(key, new WeakReference<Circle>(circle, REF_QUEUE));
			}
		}
		NUM_OF_REQUESTS.incrementAndGet();
		return circle;
	}

	static void reportMemStatus() {
		long memory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		String format = "b";
		if (memory > MEGABYTE) {
			memory = memory / MEGABYTE;
			format = "MB";
		}
		System.out.printf("%d circles requestd, %d cached, %d%s memory used%n", NUM_OF_REQUESTS.get(), cache.size(), memory, format);
	}

	static int getRandomX() {
		return (int) (random.nextDouble() * FlyWeightTest.MAX_WIDTH);
	}

	static int getRandomY() {
		return (int) (random.nextDouble() * FlyWeightTest.MAX_HEIGHT);
	}

	static int getRandomR() {
		return (int) (random.nextDouble() * (FlyWeightTest.MAX_HEIGHT / 40));
	}

	static Color getRandomColor() {
		return colors[(int) (random.nextDouble() * colors.length)];
	}

	private static class CircleKey {

		private Integer x, y, r;
		private Color color;

		public CircleKey(Color color, Integer x, Integer y, Integer r) {
			super();
			this.color = color;
			this.x = x;
			this.y = y;
			this.r = r;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((color == null) ? 0 : color.hashCode());
			result = prime * result + ((r == null) ? 0 : r.hashCode());
			result = prime * result + ((x == null) ? 0 : x.hashCode());
			result = prime * result + ((y == null) ? 0 : y.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			CircleKey other = (CircleKey) obj;
			if (color == null) {
				if (other.color != null) {
					return false;
				}
			} else if (!color.equals(other.color)) {
				return false;
			}
			if (r == null) {
				if (other.r != null) {
					return false;
				}
			} else if (!r.equals(other.r)) {
				return false;
			}
			if (x == null) {
				if (other.x != null) {
					return false;
				}
			} else if (!x.equals(other.x)) {
				return false;
			}
			if (y == null) {
				if (other.y != null) {
					return false;
				}
			} else if (!y.equals(other.y)) {
				return false;
			}
			return true;
		}
	}

}
