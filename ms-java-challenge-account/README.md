# ms-java-challenge-account


Base de datos: H2 (No es necesario la ejecución de scripts en gestores de base de datos externos)
Tecnologias aplicadas:
- Spring boot
- Lombok
- MapStruct
- Open Api
- Open Feign

Para la correcta ejecución del proyecto:
- Realizar un mvn clean install y esperar la generación de las clases y mediante Open api
- Este proyecto se ejecuta en el puerto 8080 para que pueda ser consumido por el microservicio de clientes que se ejecuta en el puerto 8081

Archivo data.sql contiene data de prueba pero el analizador es libre de agregar o modificarla a su criterio

Dentro de la carpeta resources se encuentran tanto el archivo de OpenApi yaml como el JSON para la importación en Postman, ambos pueden utilizarse con este fin