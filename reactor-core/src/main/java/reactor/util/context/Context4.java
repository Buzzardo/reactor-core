/*
 * Copyright (c) 2011-2017 Pivotal Software Inc, All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package reactor.util.context;

import java.util.AbstractMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

final class Context4 implements Context {

	final Object key1;
	final Object value1;
	final Object key2;
	final Object value2;
	final Object key3;
	final Object value3;
	final Object key4;
	final Object value4;

	public Context4(Object key1, Object value1,
			Object key2, Object value2,
			Object key3, Object value3,
			Object key4, Object value4) {
		this.key1 = key1;
		this.value1 = value1;
		this.key2 = key2;
		this.value2 = value2;
		this.key3 = key3;
		this.value3 = value3;
		this.key4 = key4;
		this.value4 = value4;
	}

	@Override
	public Context put(Object key, Object value) {
		Objects.requireNonNull(key, "key");
		Objects.requireNonNull(value, "value");

		if(this.key1.equals(key)){
			return new Context4(key, value, key2, value2, key3, value3, key4, value4);
		}

		if (this.key2.equals(key)) {
			return new Context4(key1, value1, key, value, key3, value3, key4, value4);
		}

		if (this.key3.equals(key)) {
			return new Context4(key1, value1, key2, value2, key, value, key4, value4);
		}

		if (this.key4.equals(key)) {
			return new Context4(key1, value1, key2, value2, key3, value3, key, value);
		}

		return new Context5(this.key1, this.value1, this.key2, this.value2, this.key3, this.value3,
				this.key4, this.value4, key, value);
	}

	@Override
	public Context delete(Object key) {
		Objects.requireNonNull(key, "key");

		if(this.key1.equals(key)){
			return new Context3(key2, value2, key3, value3, key4, value4);
		}

		if (this.key2.equals(key)) {
			return new Context3(key1, value1, key3, value3, key4, value4);
		}

		if (this.key3.equals(key)) {
			return new Context3(key1, value1, key2, value2, key4, value4);
		}

		if (this.key4.equals(key)) {
			return new Context3(key1, value1, key2, value2, key3, value3);
		}

		return this;
	}

	@Override
	public boolean hasKey(Object key) {
		return this.key1.equals(key) || this.key2.equals(key) || this.key3.equals(key) || this.key4.equals(key);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T get(Object key) {
		if (this.key1.equals(key)) {
			return (T)this.value1;
		}
		if (this.key2.equals(key)) {
			return (T)this.value2;
		}
		if (this.key3.equals(key)) {
			return (T)this.value3;
		}
		if (this.key4.equals(key)) {
			return (T)this.value4;
		}
		throw new NoSuchElementException("Context does not contain key: "+key);
	}

	@Override
	public int size() {
		return 4;
	}

	@Override
	public Stream<Map.Entry<Object, Object>> stream() {
		return Stream.of(
				new AbstractMap.SimpleImmutableEntry<>(key1, value1),
				new AbstractMap.SimpleImmutableEntry<>(key2, value2),
				new AbstractMap.SimpleImmutableEntry<>(key3, value3),
				new AbstractMap.SimpleImmutableEntry<>(key4, value4));
	}

	@Override
	public String toString() {
		return "Context4{" + key1 + '='+ value1 + ", " + key2 + '=' + value2 + ", " +
				key3 + '=' + value3 + ", " + key4 + '=' + value4 + '}';
	}
}
