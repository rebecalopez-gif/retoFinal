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

👉 Añadir los .jar al proyecto:

🟦 NetBeans → Properties → Libraries → Add JAR/Folder
🟪 IntelliJ → File → Project Structure → Modules → Dependencies
🗄️ Base de datos

📄 VirtualCreatures.sql
Contiene la estructura y datos necesarios del sistema.

▶️ Cómo usarlo
1. Crear base de datos en MySQL
2. Importar el script

💻 Consola:

mysql -u usuario -p nombre_bd < VirtualCreatures.sql

🖥️ MySQL Workbench:

Import SQL File
📦 Instalación
📥 Clonar repositorio
git clone https://github.com/rebecalopez-gif/retoFinal.git
📁 Importar proyecto
Abrir en Eclipse / IntelliJ / VS Code
Añadir dependencias (.jar)
➕ Dependencias manuales
Descargar:
MySQL Connector
Mockito
Añadir al proyecto
▶️ Ejecución
Configurar conexión a MySQL
Ejecutar clase main
🧪 Tests

El proyecto incluye tests con Mockito.

▶️ Ejecución
Desde el IDE ejecutar tests
Verificar que los JAR están añadidos
