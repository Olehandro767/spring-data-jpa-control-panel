package ua.spring.dataJPACP.util

import java.io.BufferedReader
import java.io.InputStreamReader

class ReflectionPackageJPAEntityScanner(private val rootClassLoader: ClassLoader) {

    private val entityAnnotation: Class<Annotation> = try {
        Class.forName("javax.persistence.Entity") as Class<Annotation>
    } catch (e: Exception) {
        throw e
    }

    fun findClassesWithEntityAnnotationFromPackage(packageName: String): Array<Class<*>> {
        if (packageName.isEmpty())
            throw IllegalArgumentException("Package name is empty")
        return BufferedReader(InputStreamReader(
            try {
                rootClassLoader.getResourceAsStream(packageName.replace('.', '/'))!!
            } catch (ignore: NullPointerException) {
                return emptyArray()
            }
        ))
            .lines()
            .filter { it.endsWith(".class") }
            .map { line -> Class.forName("$packageName.${line.substring(0, line.lastIndexOf('.'))}") }
            .filter { classEntity -> classEntity.isAnnotationPresent(entityAnnotation) }
            .toList()
            .toTypedArray()
    }

    fun findClassesWithEntityAnnotationFromPackages(vararg packageNames: String): Array<Class<*>> {
        val resultArray = ArrayList<Class<*>>()
        packageNames
            .map { packageName -> findClassesWithEntityAnnotationFromPackage(packageName) }
            .forEach { arrayOfClasses -> resultArray.addAll(arrayOfClasses) }
        return resultArray.toTypedArray()
    }
}