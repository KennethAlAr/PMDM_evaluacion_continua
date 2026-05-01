# App Piece - One Piece Fan App

## Descripción
App Piece es una aplicación Android desarrollada en Kotlin diseñada para fans de la serie One Piece. La aplicación permite explorar a los miembros de la tripulación de los Sombrero de Paja, escuchar melodías icónicas de la serie, visualizar openings y participar en un generador aleatorio de capitanes piratas.

## Características Principales

### 1. Sección de Capitanes
Permite al usuario generar un capitán aleatorio de una base de datos local.
* Carga dinámica de datos desde un archivo JSON ubicado en los assets del proyecto.
* Uso de la biblioteca org.json para el parseo de objetos.

### 2. Biblioteca de Música (OST)
Un reproductor de audio integrado para escuchar las melodías del Grand Line.
* Implementación de la biblioteca androidx.media3 (ExoPlayer) para un control preciso del flujo de audio.
* Interfaz de control personalizada con funciones de reproducción, pausa, avance y retroceso.
* Barra de progreso (SeekBar) sincronizada en tiempo real mediante un Handler con una tasa de refresco de 500ms.
* Gestión del estado de la interfaz para evitar conflictos entre la actualización automática y la interacción del usuario.

### 3. Galería de Mugiwaras (Nakamas)
Un catálogo detallado de la tripulación.
* Uso de RecyclerView con GridLayoutManager para una visualización organizada en rejilla.
* Sistema de paso de datos entre actividades mediante el uso de Intent y Bundle.
* Visualización detallada de descripciones, apodos y nombres completos.

### 4. Openings y Video
Sección dedicada a los videos musicales de la serie.
* Integración de PlayerView de la biblioteca Media3 para la reproducción de archivos de video locales (recursos RAW).
* Adaptador personalizado para la gestión de listas de video con optimización de memoria.

## Detalles Técnicos

### Tecnologías Utilizadas
* Lenguaje: Kotlin.
* Interfaz de Usuario: XML con sistemas de Layouts tradicionales (LinearLayout, FrameLayout).
* Componentes de Android: RecyclerView, CardView, SeekBar, ImageButton.
* Multimedia: Media3 (ExoPlayer) para audio y video.
* Formato de Datos: JSON para la persistencia local de ciertos modelos de datos.

## Instalación
Para probar la aplicación en un dispositivo local:
1. Clonar el repositorio.
2. Abrir el proyecto en Android Studio.
3. Compilar el APK mediante el menú Build > Build APK(s) o ejecutar directamente en un dispositivo con la depuración USB activada.