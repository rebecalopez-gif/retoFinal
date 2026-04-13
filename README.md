🚀 Proyecto: Juego de Criaturas Virtuales 
📝 Descripción
Este proyecto es un juego educativo donde los usuarios pueden crear criaturas, interactuar con ellas y administrar objetos en su habitación virtual.
 
🧰 Tecnologías utilizadas
💻 Java + JavaFX
🗄️ MySQL + XAMPP
🔌 JDBC (MySQL Connector)
🧪 JUnit / Mockito
📚 Javadoc
🛠️ Eclipse
🔄 Git + GitHub
 
📚 Dependencias
Este proyecto utiliza las siguientes librerías externas:
·        🔌 Conector JDBC MySQL (ej: mysql-connector-java-x.x.x.jar)
·        🧪 Mockito (ej: mockito-core-x.x.x.jar)
👉 Asegúrate de añadir los .jar al proyecto:
·        En NetBeans: Properties → Libraries → Add JAR/Folder
·        En IntelliJ: File → Project Structure → Modules → Dependencies
 
🗄️ Base de datos
El proyecto incluye el script de base de datos:
📄 VirtualCreatures.sql
▶️ Cómo usarlo
1.     Crear una base de datos en MySQL
2.  Importar el fichero VirtualCreatures.sql
a.     Desde consola:
mysql -u usuario -p nombre_bd < VirtualCreatures.sql


b. 	O usando una herramienta gráfica como MySQL Workbench
 
📦 Instalación
📥 Clonar el repositorio
git clone https://github.com/rebecalopez-gif/retoFinal.git



📁 Importar el proyecto
·        Abrir el proyecto en tu IDE (Eclipse/ IntelliJ / VS Code)
➕ Añadir dependencias manualmente
1.     Descargar:
a.     Conector JDBC de MySQL
b.     Librerías de Mockito
2.     Añadir los .jar al proyecto (ver sección anterior)
 
▶️ Ejecución
1.     Configurar la conexión a la base de datos en el proyecto
2.  Ejecutar la aplicación desde el main
 
🧪 Tests
El proyecto incluye pruebas unitarias usando Mockito.
Para ejecutarlas:
·        Desde el IDE → Ejecutar tests
·        Asegúrate de que los JAR de Mockito están correctamente añadidos
