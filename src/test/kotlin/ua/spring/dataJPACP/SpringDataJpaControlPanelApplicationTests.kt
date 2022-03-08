package ua.spring.dataJPACP

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

@SpringBootTest
class SpringDataJpaControlPanelApplicationTests @Autowired constructor(
	private val applicationContext: ApplicationContext
) {

	@Test
	fun contextLoads() {
		for (className in applicationContext.beanDefinitionNames) {
			val rootClass = applicationContext.getBean(className).javaClass
			if (rootClass.isAnnotationPresent(SpringBootApplication::class.java)) {
				val classes = LinkedList<Class<*>>()
				val packagesQueue: Queue<String> = LinkedList()
				val classLoader = rootClass.classLoader
				val stream = classLoader
					.getResourceAsStream(rootClass.packageName.replace('.', '/'))!!
				val lines: Array<String> = BufferedReader(InputStreamReader(stream)).lines().toList().toTypedArray()
				for (string in lines) {
					if (string.endsWith(".class")) {
						classes.add(Class.forName("${rootClass.packageName}." +
								string.substring(0, string.lastIndexOf('.'))))
					} else {
						if (!string.contains('.')) {
							packagesQueue.add(string)
						}
					}
				}
				/*val classList = reader.lines().filter { it.endsWith(".class") }
					.map {
						Class.forName("${rootClass.packageName}.${it.substring(0, it.lastIndexOf('.'))}")
					}.toList()
				val annotation = try {
					Class.forName("javax.persistence.Entity") as Class<Annotation>
				} catch (e: Exception) {
					try {
						Class.forName("jakarta.persistence.Entity") as Class<Annotation>
					} catch (e: Exception) {
						throw e
					}
				}
				for (classElement in classList) {
					classElement.isAnnotationPresent(annotation)
				}*/
				break
			}
		}
	}
}