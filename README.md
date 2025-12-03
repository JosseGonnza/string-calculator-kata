<img width="100%" src="https://capsule-render.vercel.app/api?type=waving&color=9df2ea&animation=fadeIn&height=120&section=header"/>

# String Calculator Kata (TDD)

![Kata](https://img.shields.io/badge/Kata-String%20Calculator-blueviolet)
![Level](https://img.shields.io/badge/Level-Intermediate-blue)
![TDD](https://img.shields.io/badge/Practice-TDD-red?logo=mermaid)
![Baby Steps](https://img.shields.io/badge/Workflow-Baby%20Steps-green)
![Micro Commits](https://img.shields.io/badge/Commits-Micro%20Commits-yellow)


## 📌 Descripción

Esta kata forma parte de una pista de aprendizaje de **TDD (Test Driven Development)**.  
El objetivo es practicar:

- Ciclos **Red → Green → Refactor**.
- **Pasos muy pequeños** (baby steps).
- Código simple, sin “ingeniería de más”.
- **Micro-commits** claros y frecuentes.

La idea es ir añadiendo funcionalidad **regla a regla**, como si las especificaciones te fueran llegando poco a poco en un proyecto real.

> Regla de oro: **Haz solo lo imprescindible para que el test pase. Ni más, ni menos.**

---

## 🧪 Tecnologías usadas

- **Java 21** 
- **JUnit 5**
- **IntelliJ IDEA** 
- Estilo de desarrollo: **TDD estricto**, commits pequeños y descriptivos.

---

## 🧱 Estructura básica

Clase principal:

```java
public class StringCalculator {

    public int add(String numbers) {
        // implementación
        return 0;
    }
}
```
Todos los tests deberían vivir en algo como:

```java
class StringCalculatorTest {
    // tests aquí
}
```

---

## 🚦 Reglas de la Kata

A continuación están todas las fases que se deben ir implementando en orden.
Aunque tu implementación todavía no las tenga todas, este README sirve como guía completa del recorrido.


### ✅ Paso 1 — Calculadora simple (1 o 2 números)

Crear una calculadora de cadenas con:
```java
int add(String numbers);
```


Reglas:

- Si la cadena es vacía (```""```), devuelve ```0```.
- Si la cadena contiene un solo número, devuelve ese número.
- Si la cadena contiene dos números separados por comas, devuelve su suma.

Ejemplos:
```java
add("")      → 0
add("4")     → 4
add("1,2")   → 3
```

Idea TDD:

1. Test: ```""``` → ```0```.
2. Test: ```"4"``` → ```4```.
3. Test: ```"1,2"``` → ```3```.
4. Refactor si hace falta simplificar.

---

### ✅ Paso 2 — Cantidad arbitraria de números

Ahora el método ```add``` debe poder manejar una cantidad desconocida de números, todos separados por comas.

Ejemplo:
```java
add("1,2,3,4,5,6,7,8,9") → 45
```


Idea TDD:

1. Test con 3 números: ```"1,2,3"``` → ```6```.
2. Implementar bucle que sume todos los números resultantes del ```split(",")```.
3. Test con un caso más grande: ```"1,2,3,4,5,6,7,8,9"``` → ```45```.

---

### ✅ Paso 3 — Separador newline (```\n```)

Además de comas, el método debe aceptar saltos de línea (```\n```) como separador.
Ambos separadores (```","``` y ```"\n"```) se pueden usar mezclados.

Ejemplo:
```java
add("1\n2,3") → 6
```


> No hace falta manejar entradas inválidas como ```"1,\n2"```. Only happy path 😁

Idea TDD:

1. Test: ```"1\n2,3"``` → ```6```.
2. Adaptar la lógica para tratar ```","``` y ```"\n"``` como separadores válidos (por ejemplo, reemplazando ```\n``` por ```,``` antes de hacer el ```split```).

---

### ✅ Paso 4 — Separadores personalizados

Se puede definir un separador personalizado al principio de la cadena, usando el formato:

```java
"//<separator>\n<numbers>"
```

Ejemplo:
```java
add("//;\n1;2") → 3
```

Reglas:

- El nuevo separador se usa en lugar de la coma (aunque en la práctica se suele soportar ambos).
- Deben seguir funcionando las reglas anteriores (vacío, 1, varios números, etc.).

Idea TDD:

1. Test: ```"//;\n1;2"``` → ```3```.
2. Detectar si la cadena empieza por ```"//"```.
3. Extraer el separador antes del ```\n```.
4. Usar ese separador al hacer el ```split```.

---

### ✅ Paso 5 — No permitir números negativos

Si se pasa uno o más números negativos, el método debe lanzar una excepción con el mensaje:

```java
"negatives not allowed: " + lista de negativos
```

Ejemplos:

```java
add("1,-2")        → error: "negatives not allowed: -2"
add("1,-2,-3")     → error: "negatives not allowed: -2 -3"
```

Idea TDD:

1. Test que espera excepción con un negativo
2. Test con varios negativos que espera el mensaje completo.
3. En la implementación, tras parsear los números, revisar si alguno es negativo; si lo es, construir mensaje y lanzar excepción.

---

### ✅ Paso 6 — Ignorar números mayores a 1000

Cualquier número mayor que ```1000``` debe ser ignorado.

Ejemplo:
```java
add("2,1001") → 2
add("1000,2") → 1002
```


Idea TDD:

1. Test: ```"1001,2"``` → ```2```.
2. Test: ```"1000,2"``` → ```1002``` (para asegurar que ```1000``` sí cuenta).
3. En la implementación, al sumar, saltar cualquier número ```>1000```.

---

### ✅ Paso 7 — Separadores de longitud arbitraria

Ahora los separadores pueden ser de cualquier longitud, si están rodeados por corchetes.

Formato:
```java
"//[***]\n1***2***3" → 6
```

Ejemplo:
```java
add("//[***]\n1***2***3") → 6
```

Idea TDD:

1. Test: ```"//[***]\n1***2***3"``` → ```6```.
2. Adaptar la lógica de lectura del separador para soportar ```[...]``` y no solo un char.
3. Usar ese separador (cadena completa) para hacer el split.

---

### ✅ Paso 8 — Múltiples separadores de un solo carácter

Permitir definir múltiples separadores de un solo carácter, también entre corchetes:

Formato:
```java
"//[delim1][delim2]\n"
```

Ejemplo:
```java
add("//[*][%]\n1*2%3") → 6
```


Idea TDD:

1. Test: ```"//[*][%]\n1*2%3"``` → ```6```.
2. Parsear todos los delimitadores dentro de ```[...]```.
3. Tratar todos como separadores válidos (por ejemplo, reemplazarlos por una coma antes del ```split``` o usar ```regex```).

---

### ✅ Paso 9 — Múltiples separadores de cualquier longitud

Por último, permitir múltiples separadores con longitud arbitraria:

Ejemplo:
```java
add("//[foo][bar]\n1foo2bar3") → 6
```


Idea TDD:

1. Test: ```"//[foo][bar]\n1foo2bar3" → 6```
2. Reutilizar la lógica ya creada para varios separadores, pero sin asumir longitud 1.

---
## 🥋 Notas finales

Esta kata fue creada por Roy Osherove y es una de las prácticas de TDD más populares del mundo.  
Recuerda: lo importante no es terminar rápido, sino **entrenar el músculo del diseño guiado por tests**.


<img src="https://raw.githubusercontent.com/matfantinel/matfantinel/master/waves.svg" width="100%" height="100">