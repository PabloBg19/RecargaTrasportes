# 🚌 Proyecto AbonoBus

Este proyecto consiste en una **aplicación Java Swing** que simula el proceso de **inicio de sesión, selección y recarga de un abono de transporte (bus)**, mostrando finalmente una **factura de recarga** con los datos ingresados.

Consta de **tres interfaces gráficas principales**:

1. `AbonoBus` – Pantalla de inicio de sesión y validación de datos del usuario.  
2. `ElegirAbono` – Pantalla para introducir número de tarjeta y fechas de validez del abono.  
3. `FacturaAbono` – Pantalla que muestra la factura final calculada según las fechas seleccionadas.  

---

## 🧭 Flujo general de la aplicación

```
AbonoBus (Inicio de sesión)
        │
        ├──> Valida Nombre, Apellidos, DNI y TyC
        │
        └──> Si todo es correcto:
               ↓
       ElegirAbono (Selección y recarga del abono)
               │
               ├──> Valida número de tarjeta y fechas
               │
               └──> Si todo es correcto:
                      ↓
             FacturaAbono (Factura con cálculo final)
```

---

## 🧩 1. `AbonoBus`

### 📝 Descripción

Esta es la **primera pantalla** que ve el usuario.  
Simula un **formulario de acceso** donde se introducen los siguientes datos:

- **Nombre**
- **Apellidos**
- **DNI**
- **Aceptación de los Términos y Condiciones**

También incluye imágenes publicitarias en los laterales y un botón **“Acceder”**.

---

### ⚙️ Funcionalidad principal

- **Validación de campos** mediante el método `validarCampos()`.
- **Verificación de formato del DNI** con `esDNIValido(String dni)`.
- **Cambio dinámico de tamaño de imágenes** en los banners (`ajustarImagen()`).
- Si todo es correcto:
  - Se **cierra la ventana actual**.
  - Se **abre la siguiente interfaz**, `ElegirAbono`.

---

### 🔍 Código destacado

```java
private boolean esDNIValido(String dni) {
    if (!dni.matches("^[0-9]{8}[A-Z]$")) return false;
    String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
    int numero = Integer.parseInt(dni.substring(0, 8));
    char letraCorrecta = letras.charAt(numero % 23);
    return letraCorrecta == dni.charAt(8);
}
```

---

## 🧾 2. `ElegirAbono`

### 📝 Descripción

Una vez validado el usuario, esta interfaz permite al usuario **introducir el número de su tarjeta de transporte** y **seleccionar las fechas de inicio y fin del abono** usando un **selector de calendario** (`JDateChooser`).

Incluye un botón **“Recargar”**, que valida la información y, si es correcta, genera la factura correspondiente.

---

### ⚙️ Funcionalidad principal

- Uso de **dos calendarios** (`JDateChooser`) para seleccionar las fechas.
- Validación del **número de tarjeta (10 dígitos exactos)**.
- Comprobación de que la **fecha de fin sea posterior** a la de inicio.
- Si todo es válido:
  - Crea un objeto `FacturaAbono`.
  - Muestra la ventana con el desglose del pago.

---

### 🔍 Código destacado

```java
if (!numeroTarjeta.matches("\d{10}")) {
    mensaje.append(" - El número de tarjeta debe tener exactamente 10 dígitos.\n");
}
if (fechaInicio == null) mensaje.append(" - Debes seleccionar una fecha de inicio.\n");
if (fechaFin == null) mensaje.append(" - Debes seleccionar una fecha de finalización.\n");
if (fechaInicio != null && fechaFin != null && !fechaFin.after(fechaInicio)) {
    mensaje.append(" - La fecha de finalización debe ser posterior a la de inicio.\n");
}
```

---

## 💶 3. `FacturaAbono`

### 📝 Descripción

Es la **pantalla final** que muestra el **resumen de la recarga del abono** con los datos introducidos previamente.

Incluye:
- Número de tarjeta.
- Fecha de inicio y fin.
- Cantidad de días calculada.
- Precio por día (0.15 €).
- Total a pagar.
- Botón **“Cerrar”**.

---

### ⚙️ Funcionalidad principal

- Conversión de fechas (`java.util.Date` → `LocalDate`).
- Cálculo del número de días entre fechas con `ChronoUnit.DAYS.between()`.
- Cálculo del total (`días × precioPorDía`).
- Cierre de la ventana al pulsar el botón.

---

### 🔍 Código destacado

```java
LocalDate inicio = Instant.ofEpochMilli(fechaInicioUtil.getTime())
        .atZone(ZoneId.systemDefault()).toLocalDate();
LocalDate fin = Instant.ofEpochMilli(fechaFinUtil.getTime())
        .atZone(ZoneId.systemDefault()).toLocalDate();

long dias = ChronoUnit.DAYS.between(inicio, fin);
double total = dias * PRECIO_POR_DIA;
```

---

## 🔗 Relación entre las clases

| Clase | Llama a | Propósito |
|-------|----------|------------|
| `AbonoBus` | `ElegirAbono` | Si los datos personales son válidos |
| `ElegirAbono` | `FacturaAbono` | Si la tarjeta y fechas son válidas |
| `FacturaAbono` | — | Pantalla final con el cálculo |

---

## 🧱 Tecnologías y librerías utilizadas

- **Java Swing** – Para crear la interfaz gráfica.
- **toedter.calendar.JDateChooser** – Selector de fechas en los formularios.
- **AWT & BorderFactory** – Manejo de layouts, márgenes y bordes.
- **Java Time API (java.time)** – Cálculo de fechas y días entre ellas.

---

## ▶️ Ejecución del proyecto

1. Compila los tres archivos `.java` en el mismo paquete o directorio.
2. Ejecuta la clase principal:
   ```bash
   java AbonoBus
   ```
3. Sigue los pasos en la interfaz:
   - Introducir nombre, apellidos y DNI válidos.
   - Aceptar términos.
   - Introducir número de tarjeta y fechas.
   - Ver la factura final.

---

## 📘 Ejemplo de uso

- Nombre: `Juan`
- Apellidos: `Pérez López`
- DNI: `12345678Z`
- Tarjeta: `1234567890`
- Fechas: del `01/10/2025` al `31/10/2025`

Resultado →  
**Número de días:** 30  
**Precio por día:** 0.15 €  
**Total:** 4.50 €
