package de.unibayreuth.bayceer.bayeos.objekt;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ArrayConverter {
	
	public static <T extends Object> T toClass(Class<T> clazz, Object[] o) throws ArraySerializationException{		
		return clazz.cast(toObject(clazz, o));	
	}

	@SuppressWarnings("unchecked")
	public static Object toObject(@SuppressWarnings("rawtypes") Class clazz, Object[] o) throws ArraySerializationException {
		if (!clazz.isAnnotationPresent(ArraySerializable.class)) {
			throw new ArraySerializationException(
					"The class " + clazz.getSimpleName() + " is not annotated with ArraySerializable");
		}
		
		if (o == null) {
			return null;
		}
		List<Field> fields = getAllFields(clazz);
		try {
			Object obj = clazz.getDeclaredConstructor().newInstance();
			for (Field field : fields) {
				if (field.isAnnotationPresent(ArrayElement.class)) {
					ArrayElement ae = field.getAnnotation(ArrayElement.class);
					int i = ae.value();
					if (i < o.length) {
						field.set(obj, o[i]);
					}
				}
			}
			return obj;
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new ArraySerializationException(e.getMessage());
		}
	}

	public static Object[] toArray(Object o) throws ArraySerializationException {
		
			if (o==null) {
				return null;
			}
		
			Class<?> clazz = o.getClass();
			if (!clazz.isAnnotationPresent(ArraySerializable.class)) {
				throw new ArraySerializationException("The class " + clazz.getSimpleName() + " is not annotated with ArraySerializable");
			}

			Map<Integer, Object> ol = new HashMap<Integer, Object>(20);
			List<Field> fields = getAllFields(clazz);

			int nmax = 0;
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(ArrayElement.class)) {
					ArrayElement ae = field.getAnnotation(ArrayElement.class);
					try {
						ol.put(ae.value(), field.get(o));
					} catch (IllegalArgumentException | IllegalAccessException e) {
						throw new ArraySerializationException(e.getMessage());
					}
					if (nmax < ae.value()) {
						nmax = ae.value();
					}
				}
			}
			Object[] out = new Object[nmax + 1];
			for (int i = 0; i <= nmax; i++) {
				out[i] = ol.get(i);
			}
			return out;
		
	}

	private static List<Field> getAllFields(@SuppressWarnings("rawtypes") Class clazz) {
		if (clazz == null) {
			return Collections.emptyList();
		}

		List<Field> result = new ArrayList<>(getAllFields(clazz.getSuperclass()));
		List<Field> filteredFields = Arrays.stream(clazz.getDeclaredFields())
				.filter(f -> Modifier.isPublic(f.getModifiers()) || Modifier.isProtected(f.getModifiers()))
				.collect(Collectors.toList());
		result.addAll(filteredFields);
		return result;
	}

}
