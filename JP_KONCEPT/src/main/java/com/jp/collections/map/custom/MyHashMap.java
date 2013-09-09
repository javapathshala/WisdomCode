/*
 * File: MyHashMap.java Date: 09-Sep-2013 This source code is part of Java
 * Pathshala-Wisdom Being Shared. This program is protected by copyright law but
 * you are authorise to learn & gain ideas from it. Its unauthorised use is
 * explicitly prohibited & any addition & removal of material. If want to
 * suggest any changes, you are welcome to provide your comments on GitHub
 * Social Code Area. Its unauthorised use gives Java Pathshala the right to
 * obtain retention orders and to prosecute the authors of any infraction. Visit
 * us at www.javapathshala.com
 */
package com.jp.collections.map.custom;

/**
 * @author dchadha
 */
public class MyHashMap<K, V> {

	private static final int SIZE = 16;
	private Entry table[] = new Entry[SIZE];

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
		myHashMap.put("key1", "value1");
		myHashMap.put("key2", "value2");
		myHashMap.put("key3", "value3");
		myHashMap.put("key4", "value4");
		myHashMap.put("key4", "value444");
		Object e = myHashMap.get("key4");
		System.out.println("" + e);
	}

	/**
	 * Associates the specified value with the specified key in this map. If the
	 * map previously contained a mapping for the key, the old value is
	 * replaced.
	 * 
	 * @param k
	 * @param v
	 */
	public void put(String k, String v) {
		int hash = k.hashCode() % SIZE;
		Entry e = table[hash];
		if (e != null) {
			// it means we are trying to insert duplicate
			// key-value pair, hence overwrite the current
			// pair with the old pair
			if (e.key.equals(k)) {
				e.value = v;
			} else {
				// traverse to the end of the list and insert new element in the
				// same bucket
				while (e.next != null) {
					e = e.next;
				}
				Entry entryInOldBucket = new Entry(k, v);
				e.next = entryInOldBucket;
			}
		} else {
			// new element in the map, hence creating new bucket
			Entry entryInNewBucket = new Entry(k, v);
			table[hash] = entryInNewBucket;
		}

	}

	/**
	 * Returns the entry associated with the specified key in the HashMap.
	 * Returns null if the HashMap contains no mapping for the key.
	 */
	public Object get(String k) {
		int hash = k.hashCode() % SIZE;
		Entry e = table[hash];

		// if bucket is found then traverse through the linked list and
		// see if element is present
		while (e != null) {
			if (e.key.equals(k)) {
				return e.value;
			}
			e = e.next;
		}
		return null;
	}

	/**
	 * User defined simple Map data structure with key and value. This is also
	 * used as linked list in case multiple key-value pairs lead to the same
	 * bucket with same hashcodes and different keys (collisions) using pointer
	 * 'next'.
	 * 
	 * @author dchadha
	 * 
	 */
	class Entry<K, V> {
		final String key;
		String value;
		Entry next;

		Entry(String k, String v) {
			key = k;
			value = v;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public String getKey() {
			return key;
		}
	}
}
