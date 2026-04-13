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

🔌 Conector JDBC MySQL (mysql-connector-java-x.x.x.jar)
🧪 Mockito (mockito-core-x.x.x.jar)

👉 Asegúrate de añadir los .jar al proyecto:

🟦 NetBeans: Properties → Libraries → Add JAR/Folder
🟪 IntelliJ: File → Project Structure → Modules → Dependencies
🗄️ Base de datos

El proyecto incluye el script de base de datos:

📄 VirtualCreatures.sql
→ Contiene la estructura y datos necesarios del sistema.

▶️ Cómo usarlo
Crear una base de datos en MySQL
Importar el fichero VirtualCreatures.sql
💻 Desde consola:
mysql -u usuario -p nombre_bd < VirtualCreatures.sql
🖥️ Con herramienta gráfica:
MySQL Workbench → “Import SQL File”
📦 Instalación
📥 Clonar el repositorio
git clone https://github.com/rebecalopez-gif/retoFinal.git
📁 Importar el proyecto
Abrir el proyecto en Eclipse / IntelliJ / VS Code
Añadir dependencias manualmente (JARs)
➕ Añadir dependencias
Descargar:
Conector JDBC MySQL
Mockito
Añadir los .jar al proyecto
▶️ Ejecución
Configurar conexión a la base de datos
Ejecutar la aplicación desde el main
🧪 Tests

El proyecto incluye pruebas unitarias con Mockito.

▶️ Cómo ejecutarlos
Ejecutar tests desde el IDE
Asegurarse de que los JAR de Mockito están añadidos
