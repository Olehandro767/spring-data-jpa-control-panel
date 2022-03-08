package ua.spring.dataJPACP.util

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import ua.spring.dataJPACP.SpringDataJpaControlPanelApplicationTests
import ua.spring.dataJPACP.testJPAModels.Person

class ReflectionPackageJPAEntityScannerTest {

    private val rootClassLoader = SpringDataJpaControlPanelApplicationTests::class.java.classLoader

    @Test
    fun findClassesWithEntityAnnotationFromPackageTest() {
        val jpaEntityScanner = ReflectionPackageJPAEntityScanner(rootClassLoader)
        assertThrows<IllegalArgumentException> {
            jpaEntityScanner.findClassesWithEntityAnnotationFromPackage("")
        }
        val classes = jpaEntityScanner
            .findClassesWithEntityAnnotationFromPackage("ua.spring.dataJPACP.testJPAModels")
        assertTrue(classes.isNotEmpty())
        assertTrue(classes.contains(Person::class.java))
    }
}