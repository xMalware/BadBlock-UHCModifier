package fr.badblock.bukkit.games.uhcmodifier.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.bukkit.Bukkit;

/**
 * Classe contenant plusieurs m�thodes utiles pour l'utilisation de la
 * r�flection. Pour la gestion des fields, pr�f�rez
 * {@link fr.badblock.gameapi.utils.reflection.Reflector}.
 * 
 * @author LeLanN
 */
public class ReflectionUtils {
	/**
	 * R�cup�re la version Bukkit
	 * 
	 * @return La version
	 */
	public static String getBukkitVersion() {
		String name = Bukkit.getServer().getClass().getPackage().getName();
		return name.substring(name.lastIndexOf('.') + 1) + ".";
	}

	/**
	 * R�cup�re un constructeur
	 * 
	 * @param clazz
	 *            La classe contenenant le constructeur
	 * @param args
	 *            Les arguments du constructeur
	 * @return Le constructeur
	 */
	public static Constructor<?> getConstructor(Class<?> clazz, Class<?>... args) {
		try {
			return clazz.getConstructor(args);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * R�cup�re et rend accessible un Field
	 * 
	 * @param clazz
	 *            Classe contenant le field
	 * @param name
	 *            Nom du field
	 * @return Le Field
	 */
	public static Field getField(Class<?> clazz, String name) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * R�cup�re la m�thode getHandle pr�sente sur beaucoup d'objets OBC
	 * 
	 * @param obj
	 *            L'object en question
	 * @return Le r�sultat du getHandle()s
	 */
	public static Object getHandle(Object obj) {
		try {
			return getMethod(obj.getClass(), "getHandle", new Class[0]).invoke(obj, new Object[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * R�cup�re une m�thode
	 * 
	 * @param clazz
	 *            La classe contenenant la m�thode
	 * @param name
	 *            Le nom de la m�thode
	 * @param args
	 *            Les arguments de la m�thode
	 * @return La m�thode
	 */
	public static Method getMethod(Class<?> clazz, String name, Class<?>... args) {
		try {
			Method method = clazz.getDeclaredMethod(name, args);
			method.setAccessible(true);
			
			return method;
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * R�cup�re une classe NMS
	 * 
	 * @param className
	 *            Le nom de la classe recherch�e
	 * @return La classe trouv�e
	 */
	public static Class<?> getNMSClass(String className) {
		try {
			return Class.forName("net.minecraft.server." + getBukkitVersion() + className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * R�cup�re une classe OBC (org.bukkit.craftbukkit)
	 * 
	 * @param className
	 *            Le nom de la classe � trouver
	 * @return La classe trouv�e
	 */
	public static Class<?> getOBCClass(String className) {
		try {
			return Class.forName("org.bukkit.craftbukkit." + getBukkitVersion() + className);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * R�cup�re une classe appartenant � une autre
	 * 
	 * @param clazz
	 *            La classe "m�re"
	 * @param name
	 *            Le nom de la sous classe
	 * @return La classe trouv�e
	 */
	public static Class<?> getSubClass(Class<?> clazz, String name) {
		for (Class<?> c : clazz.getDeclaredClasses()) {
			if (c.getSimpleName().equals("EnumTitleAction")) {
				return c;
			}
		}

		return null;
	}

	/**
	 * Enl�ve le flag 'final' � un Field.
	 * 
	 * @param field
	 *            Le field auquel enlever le flag 'final'.
	 */
	public static void removeFinal(Field field) {
		if (Modifier.isFinal(field.getModifiers())) {
			try {
				Reflector reflector = new Reflector(field);
				reflector.setFieldValue("modifiers", field.getModifiers() & 0xffffffef);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
