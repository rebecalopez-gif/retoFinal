# 🚀 Proyecto: Juego de Criaturas Virtuales

---

## 📝 Descripción
Este proyecto es un juego educativo donde los usuarios pueden crear criaturas, interactuar con ellas y administrar objetos en su habitación virtual.

---

## 🧰 Tecnologías utilizadas

* **Lenguaje:** Java 💻
* **Base de Datos:** MySQL + XAMPP 🗄️
* **Conector:** JDBC (MySQL Connector) 🔌
* **Testing:** JUnit / Mockito 🧪
* **Documentación:** Javadoc 📚
* **IDE:** Eclipse 🛠️
* **Control de versiones:** Git + GitHub 🔄

---

## 📚 Dependencias

Este proyecto utiliza las siguientes librerías externas que deben ser añadidas al *Build Path*:

1. **Conector JDBC MySQL** (`mysql-connector-java-x.x.x.jar`)
2. **Mockito** (`mockito-core-x.x.x.jar`)

### 🔹 Cómo añadir los .jar al proyecto:

* **NetBeans:** `Properties` ➔ `Libraries` ➔ `Add JAR/Folder`
* **IntelliJ:** `File` ➔ `Project Structure` ➔ `Modules` ➔ `Dependencies`
* **Eclipse:** `Right Click Project` ➔ `Build Path` ➔ `Configure Build Path`

---

## 🗄️ Base de datos

El archivo principal es **`VirtualCreatures.sql`**. Contiene la estructura y datos necesarios del sistema.

### ▶️ Cómo usarlo:
1. Crear base de datos en MySQL.
2. Importar el script.

**Comando de Consola:**
```bash
mysql -u usuario -p nombre_bd < VirtualCreatures.sql
```

---

## 📦 Instalación

### 📥 Clonar repositorio
```bash
git clone [https://github.com/rebecalopez-gif/retoFinal.git](https://github.com/rebecalopez-gif/retoFinal.git)
```

### 📁 Importar proyecto
Abrir en Eclipse, IntelliJ o VS Code.

### ➕ Añadir las dependencias .jar mencionadas anteriormente de forma manual al Build Path.
  1. **Descargar**:  
      a. Conector JDBC de MySQL  
      b. Librerías de Mockito  
  2. Añadir los .jar al proyecto (ver sección anterior)  

---

## ▶️ Ejecución
Configurar los parámetros de conexión a MySQL (host, usuario, contraseña) en el código fuente.

Localizar y ejecutar la clase Main.

---

## 🧪 Tests
El proyecto incluye pruebas unitarias para validar la lógica de negocio de las criaturas.

**Herramienta**: Mockito / JUnit.

**Ejecución**: Clic derecho sobre el archivo de test ➔ Run as JUnit Test.

---


**Nota**: Asegúrate de tener los archivos JAR de Mockito correctamente vinculados en las librerías del proyecto antes de ejecutar las pruebas.
