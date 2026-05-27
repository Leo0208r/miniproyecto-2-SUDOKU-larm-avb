[README.md](https://github.com/user-attachments/files/28291083/README.md)
# 🎮 Sudoku 6x6 — Mini Proyecto #2

**Curso:** 750014C Fundamentos de Programación Orientada a Eventos  
**Universidad del Valle**
## 👥 Autores
- Leonardo Alexis Rosero Mendez-2518313 — Universidad del Valle
- Alejandro Velez Bejarano 2521169 — Universidad del Valle

---

## 📋 Descripción

Implementación del juego Sudoku con una cuadrícula de 6x6. El objetivo es completar el tablero con números del 1 al 6, asegurando que en cada fila, columna y bloque de 2x3 aparezcan todos los números sin repetir.

---

## 🎯 Funcionalidades

- Tablero 6x6 generado dinámicamente en cada partida
- Cada bloque 2x3 inicia con exactamente 2 números pre-llenados
- Ingreso de números mediante teclado (1-6)
- Eliminación de números con Backspace o Delete
- Validación en tiempo real con resaltado visual de errores (borde rojo)
- Resaltado de fila, columna y celdas con el mismo número al seleccionar
- Botón de ayuda que sugiere un número válido para una celda vacía
- Detección automática de victoria al completar el tablero correctamente
- Pantalla de menú, juego y victoria

---

## 🏗️ Arquitectura MVC

El proyecto sigue estrictamente la arquitectura **Modelo - Vista - Controlador**:

```
src/
└── main/
    ├── java/
    │   └── com/example/sudoku/
    │       ├── model/
    │       │   ├── Cell.java
    │       │   ├── TreeNode.java
    │       │   ├── SudokuBoard.java
    │       │   ├── SudokuGenerator.java
    │       │   ├── SudokuValidator.java
    │       │   ├── SudokuSolver.java
    │       │   ├── IValidator.java
    │       │   └── ISolver.java
    │       ├── controller/
    │       │   ├── MenuController.java
    │       │   ├── GameController.java
    │       │   └── WinController.java
    │       └── view/
    │           ├── MenuStage.java
    │           ├── GameStage.java
    │           └── EndStage.java
    └── resources/
        └── com/example/sudoku/
            ├── menu-view.fxml
            ├── game-view.fxml
            └── end-view.fxml
```

---

## 🌳 Estructura de datos — Árbol N-ario

El tablero se implementa como un **árbol n-ario** en lugar de una matriz tradicional:

```
root (-1, -1)
├── rowNode (0, -1)
│   ├── cellNode (0,0) → Cell(value, fixed)
│   ├── cellNode (0,1) → Cell(value, fixed)
│   └── cellNode (0,5) → Cell(value, fixed)
├── rowNode (1, -1)
│   └── ...
└── rowNode (5, -1)
    └── ...
```

Clases involucradas:
- **`Cell`** — almacena el valor (0-6) y si la celda es fija
- **`TreeNode`** — nodo del árbol con posición, celda y lista de hijos
- **`SudokuBoard`** — raíz del árbol con métodos `getValue`, `setValue`, `isFixed`

---

## 🔌 Interfaces

| Interfaz | Implementación | Contrato |
|---|---|---|
| `IValidator` | `SudokuValidator` | `isValidate(board, row, col, value)` |
| `ISolver` | `SudokuSolver` | `solve(board)`, `getHint(board)` |

---

## 📦 Descripción de clases

### Modelo

| Clase | Responsabilidad |
|---|---|
| `Cell` | Dato de una celda: valor numérico y estado fijo |
| `TreeNode` | Nodo del árbol n-ario: posición, celda e hijos |
| `SudokuBoard` | Tablero completo usando árbol n-ario |
| `SudokuGenerator` | Genera tablero válido aleatorio con 2 números por bloque |
| `SudokuValidator` | Valida filas, columnas y bloques 2x3 |
| `SudokuSolver` | Resuelve el tablero con backtracking y genera pistas |

### Controlador

| Clase | Responsabilidad |
|---|---|
| `MenuController` | Maneja eventos del menú principal |
| `GameController` | Maneja el juego: render, input, validación, ayuda y victoria |
| `WinController` | Maneja la pantalla de victoria |

### Vista

| Clase | Responsabilidad |
|---|---|
| `MenuStage` | Carga y muestra `menu-view.fxml` |
| `GameStage` | Carga y muestra `game-view.fxml` |
| `EndStage` | Carga y muestra `end-view.fxml` |

---

## 🎮 Manejo de eventos

Dentro de `GameController` se implementan dos **clases internas** que actúan como manejadores de eventos:

### `CellClickHandler` — MouseEvent
- Selecciona la celda al hacer clic
- Resalta en naranja la celda seleccionada
- Resalta en azul claro la fila, columna y celdas con el mismo número

### `KeyHandler` — KeyEvent
- Acepta números del 1 al 6
- `Backspace` / `Delete` borra el número ingresado
- Muestra borde rojo si el número viola las reglas del Sudoku
- Verifica victoria después de cada entrada válida

---

## 🔁 Flujo de navegación

```
Main.java
  └── MenuStage.showView()
        └── [Botón Jugar] → GameStage.showView()
              └── [Victoria] → EndStage.showView()
                    ├── [Jugar de nuevo] → GameStage.showView()
                    └── [Volver al menú] → MenuStage.showView()
```

---

## ⚙️ Tecnologías

| Tecnología | Uso |
|---|---|
| Java SE 17+ | Lenguaje principal |
| JavaFX | Interfaz gráfica |
| Scene Builder (FXML) | Diseño de vistas |
| IntelliJ IDEA | IDE |
| Git / GitHub | Control de versiones |
| Javadoc | Documentación |

---

## 🚀 Cómo ejecutar

1. Clona el repositorio:
```bash
git clone https://github.com/usuario/sudoku-6x6.git
```

2. Abre el proyecto en **IntelliJ IDEA**

3. Asegúrate de tener configurado **JavaFX SDK** en el proyecto

4. Ejecuta `Main.java`

---

## 📐 Reglas del Sudoku 6x6

- El tablero tiene 6 filas y 6 columnas
- Está dividido en 6 bloques de 2x3
- Cada fila debe contener los números 1 al 6 sin repetir
- Cada columna debe contener los números 1 al 6 sin repetir
- Cada bloque 2x3 debe contener los números 1 al 6 sin repetir

```
┌──────────┬──────────┐
│ Bloque 1 │ Bloque 2 │
├──────────┼──────────┤
│ Bloque 3 │ Bloque 4 │
├──────────┼──────────┤
│ Bloque 5 │ Bloque 6 │
└──────────┴──────────┘
```

---


