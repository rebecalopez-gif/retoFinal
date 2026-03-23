# Juego de Criaturas Virtuales

Este proyecto es un juego educativo donde los usuarios pueden crear criaturas, interactuar con ellas y administrar objetos en su habitación virtual.

## Contenido

- **Vista**: Interfaces gráficas (`VentanaPrincipal`, `VentanaNewUsuario`, `VentanaPartidaNew`, `VentanaPartidas`, `VentanaHabitacion`)  
- **Modelo**: Clases `Creature`, `UserGame`, `Objectos`  
- **Controlador**: `CriaturasControlador` gestiona la lógica del juego y acceso a la base de datos

## Funcionalidades principales

1. **Inicio de sesión y registro**
   - Ventana principal para loguearse
   - Registro de nuevos usuarios
2. **Gestión de partidas**
   - Crear, eliminar y seleccionar partidas de criaturas
3. **Habitación de la criatura**
   - Interacción con la criatura (bichito)
   - Acceso al armario y objetos
   - Opción de dormir / salir del juego

## Acceso a la base de datos

- La clase `CriaturasControlador` implementa los métodos de acceso a la BD
- Los métodos incluyen:
  - `iniciarSesion(UserGame user)`
  - `introducirUser(UserGame user)`
  - `comprobarUser(UserGame user)`
  - `obtenerPartidas(UserGame user)`
  - `eliminarPartida(Creature criatura)`

## Documentación

- La documentación de cada clase se encuentra en los comentarios **Javadoc** de las clases  
- Cada `actionPerformed` de las vistas está documentado indicando qué botones y acciones controla  

## Instalación y ejecución

1. Clonar el repositorio
2. Abrir el proyecto en un IDE Java (Eclipse, IntelliJ, NetBeans)
3. Ejecutar la clase `VentanaPrincipal` para iniciar el juego

## Autor

- Tu Nombre
- Fecha / versión: 2026-03-23
