# TP Patrones de Diseño - Excusas S.A.

Hola profe. Este es mi trabajo para la materia. El sistema automatiza cómo se manejan las excusas cuando los empleados llegan tarde.

## Cómo funciona el código:

Para que el código sea limpio y no repetir cosas, usé estos patrones:

1. **Cadena de Responsabilidad**: Las excusas pasan de uno a otro (Recepcionista -> Supervisora -> CEO). Si uno no puede, se la pasa al siguiente.
2. **Estado (State) + Delivery**: Los jefes cambian su humor según cuántas excusas procesaron. Hay tres estados: Productivo (menos de 5), Normal (5 a 10) y Vago (11 o más). Esto lo maneja la clase `DeliveryModo` dinámicamente con el método `modoPara()`.
3. **Método Plantilla (Template Method)**: En la clase base `Encargado` puse la lógica general de cómo revisar una excusa (`revisarExcusa()`) para no repetir el "if puede manejar -> procesa, sino -> deriva" en cada jefe.
4. **Builder**: Lo usé para armar la cadena de jefes de forma fácil y configurable en el main.
5. **Observer**: Cuando el CEO acepta algo inverosímil, crea un prontuario y se avisa automáticamente al equipo de dirección y al mismo CEO.
6. **Singleton**: Para que haya una sola lista (`AdministradorProntuarios`) de prontuarios en todo el sistema.

## Notas adicionales:
- En lugar de usar un `String` para el tipo de excusa, armé una jerarquía de clases (`ExcusaTrivial`, `ExcusaModerada`, etc.) para aprovechar el polimorfismo.
- Cumplí con los mensajes específicos de los emails para la Recepcionista, Supervisora (con el check de EDESUR) y el CEO.

## Cómo probarlo:
Para ver cómo funciona todo en la consola con diferentes casos:
```bash
javac -d target $(find src/main/java/com/empresa -name "*.java")
java -cp target com.empresa.Demo
```

Para correr los tests integrales que comprueban la cadena y los estados:
```bash
mvn test
```
