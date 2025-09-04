A. ¿Cuál es la diferencia principal entre una prueba unitaria y una prueba de integración E2E?  

La prueba unitaria se enfoca en una unidad pequeña de código. Busca comprobar que la lógica de esa unidad está funcionando de manera correcta, sin depender de otras partes del sistema.  

La prueba de integración End to End verifica que múltiples componentes o el sistema completo funcionen cuando interactúan entre sí. En este estilo de pruebas se simula el recorrido real de un usuario a través de la aplicación desde el frontend hasta el backend. 

En conclusión, podemos decir que las pruebas unitarias comprueban que cada ladrillo funcione y el E2E asegura que toda la casa funcione.  

B. En Scrum ¿Cuál es el propósito de la Sprint Retrospective y porque es crucial para la mejora continua del equipo?  

La Sprint restrospective es un evento que ocurre al final de cada sprint el cual busca que el equipo reflexione sobre la manera en la que trabajaron juntos desde el sprint y se llegue a acuerdos para mejorar su trabaja para el próximo spring.  

Esto nos ayuda pues permite al quipo tener un aprendizaje constante, mejorar su proceso, prevenir problemas futuros y reforxar la mentalidad de que siempre se puede trabajar de forma más eficiente. 

C. Explique la diferencia entre una Épica, una Feature y una historia de Usuario. Proporcione un ejemplo de cada una si tenemos un sistema de streaming de video como lo es Netflix. 

Epica: Es una iniciativa grande, de alto nivle, que representa un objetivo amplio de negocio o producto. Es demasiado grande para completarse en un solo Sprint, por lo que se divide en features e historias de usuarios.  

Ejemplo: permitir a los usuarios disfrutar contenido personalizado según sus gustos y hábitos de visualización. 

Feature: Una feature es una funcionalidad concreta y significativa para el usuario, que contribuye a cumplir la épica. Generalmente se puede realizar en uno o varios Sprints.  

Ejemplo: recomendaciones automáticas de series y películas en la página de inicio. 

Historia de Usuario: Es la descripción, desde la perspectiva del usuario, de una necesidad pequeña y específica.  

Ejemplo: Como usuario quiero ver una sección “Recomendaciones para ti” en mi página de inicio para descubrir contenido que se ajuste a mis gustos.

D. ¿Qué es una cobertura de Código (code coverage) y porque una cobertura del 100% no garantiza necesariamente que el software esté libre de errores? 

La cobertura de código es una métrica que indica el porcentaje de código fuente que se ejecuta al correr las pruebas, permitiendo ver si las pruebas realmente están ejercitando el código escrito.  
Hay distintos niveles: line coverage, Branch coverage y Function coverage. 
La cobertura del 100% no garantiza la ausencia de errores pues, aunque logremos que todas las líneas se ejecuten en las pruebas, no significa que sean validas. Algunas razones son que una prueba puede ejecutar una línea sin comprobar si el resultado es correcto. También puede ocurrir que el código de la salida equivocada, aunque se ejecute completamente.  

E. Describa que es un Diagrama de Casos de Uso y que elementos lo componen. ¿Para qué sirve en la fase de análisis de requerimientos?  

Es un diagrama hecho en notación UML el cual se encarga de mostrar de forma clara y gráfica la interacción que tienen los agentes externos con el sistema de desarrollo. 

Este se encuentra compuesto por actores, lo cuales son los agentes externos que actúan con el sistema, los casos de uso que son los sistemas o las funcionalidades que se ofrecen a los actores, el propio sistema el cual representa el límite del software que se está utilizando y una de las partes más importantes del diagrama, las relaciones, las cuales se dividen en diferentes tipos como la de asociación, include, extend y generalización. 

F. ¿Cuál es la diferencia entre el uso de Junit y Jacoco en un proyecto, y como complementa SonarQube este proceso en términos de calidad de software?

La diferencia radica en que Junit se encarga de ejecutar pruebas automáticas sobre el código mientras que Jacoco no prueba el código por cuenta propia, en cambio se encarga de medir que porcentaje del código fue ejecutado cuando se corrieron las pruebas que pueden ser de Junit. SonarQube se encarga de integrar todo y hacer un análisis estático para obtener una visión global de la calidad del software. 

G. ¿Qué ventajas tiene el uso de Planning Poker frente a otros métodos de estimación tradicional y como ayuda a mejorar la transparencia y compromiso del equipo?  

Participación activa de todo el equipo: Todos los miembros pueden dar su opinión y no únicamente el líder de equipo lo cual se hace para poder aprovechar la experiencia colectiva. 

Menos influencia jerárquica: Todas las opiniones toman el mismo peso lo cual evita que alguna de estas se deje influenciar de la del jefe o algún miembro con más experiencia. 

Más precisión de estimación: Como se están presentando distintas perspectivas desde todos lados obtenemos estimaciones más realistas. 

Método ágil y dinámico: Se ajusta bien a las iteraciones cortas ya que es rápido y dinámico. 

Esto ayuda en la transparencia ya que todos son conscientes de cómo llegaron al resultado y saben que no fue únicamente decisión del jefe, por el lado del compromiso al participar todos activamente sienten más motivación al ver los resultados. 

H. Menciona los valores de Scrum y explica cual consideras más difícil de aplicar en un equipo. 

Compromiso: El equipo se compromete a alcanzar los objetivos del Sprint y a aportar valor al producto. 

Coraje: Los miembros están dispuestos asumir retos difíciles. 

Foco: El equipo se centra en cumplir el Sprint Goal y evitar distracciones. 

Apertura: Son receptivos a nuevas ideas y retroalimentación. 

Respeto: Reconocer y valorar los aportes de todos los miembros. 

La más difícil podría llegar a ser el respeto y no tanto por el significado de la palabra misma, sino más bien por la definición ya que muchas veces el ser humano por naturaleza puede llegar a ser envidioso y no reconocer los atributos de los demás por encima de los suyos. 