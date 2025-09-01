# Sistema de Planning Poker para Bankify

## ¿Qué es este proyecto?

Este es un sistema simple de Planning Poker desarrollado en Java para el laboratorio 3 de la materia DOSW. Es como una herramienta para que los equipos de desarrollo estimen el esfuerzo de las historias de usuario de manera colaborativa.

## ¿Cómo funciona?

El sistema simula una reunión de Planning Poker donde varios miembros del equipo votan en historias de usuario usando la secuencia de Fibonacci (1, 2, 3, 5, 8, 13). Si no hay consenso en la primera ronda, se discute y se vota de nuevo hasta que todos estén de acuerdo.

## Patrones de diseño usados

- **Factory Pattern**: Usamos `UserStoryFactory` y `TeamMemberFactory` para crear objetos de manera organizada y centralizada.
- **Session Pattern**: `PlanningPokerSession` maneja todo el flujo de la sesión de votación.
- **Model Pattern**: `UserStory` representa los datos de las historias de usuario.

## Estructura del proyecto

```
src/main/java/edu/dosw/lab/
├── App.java                    # Clase principal que inicia todo
└── planningpoker/
    ├── PlanningPokerMain.java      # Controla el flujo principal
    ├── PlanningPokerSession.java   # Maneja la sesión de votación
    ├── TeamMemberFactory.java      # Crea los miembros del equipo
    ├── UserStory.java              # Modelo de datos de historias
    └── UserStoryFactory.java       # Crea las historias de usuario
```

## Historias de usuario incluidas

El sistema viene con 5 historias predefinidas de Bankify más una adicional:

1. **HU01**: Crear cuenta bancaria
2. **HU02**: Validar número de cuenta
3. **HU03**: Consultar saldo
4. **HU04**: Realizar depósito
5. **HU05**: Gestionar bancos registrados
6. **HU06**: Transferir dinero entre cuentas

## Miembros del equipo

- Juana (Desarrolladora)
- Carlos (Tester)
- Christian (Analista)

## ¿Cómo ejecutar?

1. Compila el proyecto: `mvn compile`
2. Ejecuta la aplicación: `java -cp target/classes edu.dosw.lab.App`

## Tecnologías usadas

- **Java**: Lenguaje de programación principal
- **Maven**: Para gestión de dependencias y build
- **JUnit**: Para pruebas unitarias (aunque no se usan en este lab)

## ¿Qué aprendí?

Este proyecto me ayudó a entender mejor:
- Cómo organizar código en paquetes
- El uso de patrones de diseño simples
- Manejo de entrada/salida por consola
- Trabajo con colecciones en Java
- Separación de responsabilidades en clases

¡Es un sistema básico pero funcional para practicar conceptos de POO!</content>
<parameter name="filePath">c:\Users\Chris\OneDrive\-University\6Semestry\DOSW\Labs\3\support\Laboratorio-3-DOSW-prev\Laboratorio-3-DOSW\README.md
