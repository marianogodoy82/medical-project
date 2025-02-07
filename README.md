# Medical Records Parent Project

## 📌 Descripción
Este proyecto es una **arquitectura basada en microservicios** desarrollada con **Spring Boot 3**, **Spring Cloud Gateway con Reactividad**, **Spring Security**, **MongoDB** y **Docker Compose**. Proporciona autenticación segura con **JWT**, gestión de usuarios y almacenamiento de historiales médicos.

## 🚀 Tecnologías
- **Java 21**
- **Spring Boot 3**
- **Spring Cloud Gateway (Reactividad con WebFlux)**
- **Spring Security (JWT)**
- **MongoDB**
- **Docker & Docker Compose**
- **Micrometer + Prometheus + Grafana** (Monitoreo)

## 📁 Estructura del Proyecto
```bash
medical-records-parent/
│── auth-service/           # Microservicio de autenticación y gestión de usuarios
│── gateway-service/        # API Gateway con validación JWT
│── medical-records-service/ # Microservicio de gestión de historiales médicos
│── pom.xml                 # POM padre para gestionar dependencias y módulos
│── docker-compose.yml      # Orquestación de los microservicios
│── README.md               # Documentación del proyecto
```

## 🔧 Instalación y Configuración
### **1️⃣ Requisitos Previos**
- Docker & Docker Compose
- Java 21
- Maven

### **2️⃣ Clonar el Repositorio**
```bash
git clone https://github.com/marianogodoy82/medical-project.git
cd medical-project
```

### **3️⃣ Configurar Variables de Entorno**
Crea un archivo `.env` en la raíz del proyecto:
```env
MONGO_URI=mongodb://mongo:27017/medical_records
JWT_SECRET=mysecretkey
```

### **4️⃣ Construir y Ejecutar con Docker Compose**
```bash
docker-compose up --build
```

### **5️⃣ Acceder a la API**
- **Autenticación**: `http://localhost:8080/api/auth`
- **Historiales Médicos**: `http://localhost:8080/api/historiales`

## 📜 Endpoints Principales

### 🔑 **Autenticación (`auth-service`)** (`/api/auth`)
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `POST` | `/register` | Registrar un nuevo usuario |
| `POST` | `/login` | Iniciar sesión y obtener JWT |

### 🚪 **API Gateway (`gateway-service`)**
- **Validación de JWT antes de redirigir peticiones a los microservicios.**
- **Protección de endpoints sensibles con Spring Security.**

### 🏥 **Gestión de Historiales Médicos (`medical-records-service`)** (`/api/historiales`)
| Método | Endpoint | Descripción |
|--------|---------|-------------|
| `POST` | `/` | Crear un historial médico (Solo `USER`) |
| `GET` | `/{userId}` | Obtener historial médico (Autorización basada en rol) |

## 📊 Monitoreo con Prometheus y Grafana
- **Prometheus**: `http://localhost:9090`
- **Grafana**: `http://localhost:3000` (Usuario: `admin` / Contraseña: `admin`)

## 🧪 Pruebas
Para probar la API, puedes usar **Postman** o **Swagger UI**.

## 📌 Contribuciones
Si deseas contribuir, abre un **Pull Request** o reporta un **Issue** en GitHub.

## 📄 Licencia
Este proyecto está bajo la **Licencia MIT**.