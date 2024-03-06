# FilsmTar - Tu Plataforma Cinematográfica Personalizada

Bienvenido a FilsmTar , el componente que facilita la integración de funcionalidades relacionadas con películas en tu aplicación o proyecto. FilsmTar te permite acceder a información detallada sobre películas, calificaciones y reseñas proporcionadas por la comunidad.

## Características Principales

- **Explora Películas:** Obtén información detallada sobre una amplia variedad de películas, incluyendo detalles como el título, género, año de lanzamiento y una breve sinopsis.

- **Calificaciones y Reseñas:** Accede a las calificaciones y reseñas proporcionadas por la comunidad de usuarios. Esto te permite obtener una perspectiva más completa antes de ver una película.

- **Búsqueda paginada:** Utiliza la funcionalidad de búsqueda para encontrar películas paginadas y ordenadas como desees.

## Uso

Para empezar a utilizar FilsmTar, simplemente lanza la aplicación en tu local y realiza solicitudes HTTP a la URL base de la API. Aquí tienes un ejemplo utilizando cURL para obtener información sobre películas:

```bash
curl -X GET -H "Authorization: Bearer TU_TOKEN" https://localhost:8080/api/v1/auth/movies
```

## Documentacion

[Enlace a la documentación de PostMan](https://documenter.getpostman.com/view/32189077/2sA2r53kYt)
