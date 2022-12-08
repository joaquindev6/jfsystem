<%@ page import="java.util.List" %>
<%@ page import="com.company.app.models.DocumentType" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.company.app.models.User" %>
<%@ page import="com.company.app.models.UserRole" %>
<%@ page import="com.company.app.util.TextFormatString" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<DocumentType> documentTypes = (List<DocumentType>) request.getAttribute("documents");
  List<UserRole> roles = (List<UserRole>) request.getAttribute("roles");
  Map<String, String> errors = (Map<String, String>) request.getAttribute("errorsView");

  User user = (User) request.getAttribute("user");

  String nombres = "";
  String apellidos = "";
  String email = "";
  long documentType = 0L;
  String nroDocu = "";
  String username = "";
  String password = "";
  long rol = 0L;

  if (user != null) {
    nombres = user.getNombres();
    apellidos = user.getApellidos();
    email = user.getEmail();
    documentType = user.getDocumentType().getId();
    nroDocu = user.getNrodocu();
    username = user.getUsername();
    password = user.getPassword();
    rol = user.getUserRole().getId();
  }

  User userLogin = (User) session.getAttribute("userLogin");
  String nombreCompleto = "";
  if (userLogin != null) {
    nombreCompleto = userLogin.getNombres().split(" ")[0] + " " + userLogin.getApellidos().split(" ")[0];
  }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Registro de Usuario | JC System</title>

  <!-- Icono pestaña -->
  <link href="<%=request.getContextPath()%>/resources/img/favicon.png" rel="icon">

  <!-- Archivos CSS -->
  <link href="<%=request.getContextPath()%>/resources/templates/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/templates/bootstrap/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet">
</head>
<body>
  <!-- Cabecera -->
  <header id="header" class="header fixed-top d-flex align-items-center">

    <!-- Logo y nombre del sistema -->
    <div class="d-flex align-items-center justify-content-between">
      <a href="<%=request.getContextPath()%>/inicio" class="logo d-flex align-items-center">
        <img src="<%=request.getContextPath()%>/resources/img/logo.png" alt="">
        <span class="d-none d-lg-block">JC System</span>
      </a>
      <i class="bi bi-list toggle-sidebar-btn"></i> <!-- Icono de menu -->
    </div>

    <!-- Menu perfil -->
    <nav class="header-nav ms-auto">
      <ul class="d-flex align-items-center">
        <li class="nav-item dropdown pe-3">

          <!-- Imagen de perfil -->
          <a class="nav-link nav-profile d-flex align-items-center pe-0" href="#" data-bs-toggle="dropdown">
            <img src="<%=request.getContextPath()%>/resources/img/icono-user.png" alt="Profile" class="rounded-circle">
            <span class="d-none d-md-block dropdown-toggle ps-2"><%=nombreCompleto%></span>
          </a>

          <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow profile">
            <li class="dropdown-header">
              <h6><%=nombreCompleto%></h6>
              <span><%=TextFormatString.formatTextData(userLogin.getUserRole().getDescripcion())%></span>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="<%=request.getContextPath()%>/usuario/perfil">
                <i class="bi bi-person"></i>
                <span>Mi perfil</span>
              </a>
            </li>
            <li>
              <hr class="dropdown-divider">
            </li>

            <li>
              <a class="dropdown-item d-flex align-items-center" href="<%=request.getContextPath()%>/logout">
                <i class="bi bi-box-arrow-right"></i>
                <span>Cerrar sesión</span>
              </a>
            </li>
          </ul>

        </li>
      </ul>
    </nav>
  </header>

  <!-- Menu -->
  <aside id="sidebar" class="sidebar">
    <ul class="sidebar-nav" id="sidebar-nav">

      <li class="nav-item">
        <a class="nav-link collapsed" href="<%=request.getContextPath()%>/inicio">
          <i class="bi bi-grid"></i>
          <span>Inicio</span>
        </a>
      </li>

      <% if ("ROLE_ADMIN".equals(userLogin.getUserRole().getNombre())) { %>
      <li class="nav-item">
        <a class="nav-link" data-bs-target="#control" data-bs-toggle="collapse" href="#">
          <i class="bi bi-journal-text"></i><span>Control</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="control" class="nav-content collapse show" data-bs-parent="#sidebar-nav">
          <li>
            <a href="<%=request.getContextPath()%>/control/usuarios" class="active">
              <i class="bi bi-circle"></i><span>Usuarios</span>
            </a>
          </li>
        </ul>
      </li>
      <% } %>

      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#inventory" data-bs-toggle="collapse" href="#">
          <i class="bi bi-boxes"></i><span>Inventario</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="inventory" class="nav-content collapse" data-bs-parent="#sidebar-nav">
          <li>
            <a href="<%=request.getContextPath()%>/inventario/productos">
              <i class="bi bi-circle"></i><span>Productos</span>
            </a>
          </li>
          <li>
            <a href="<%=request.getContextPath()%>/inventario/categoria-producto">
              <i class="bi bi-circle"></i><span>Categorías</span>
            </a>
          </li>
          <li>
            <a href="<%=request.getContextPath()%>/inventario/marca-producto">
              <i class="bi bi-circle"></i><span>Marcas</span>
            </a>
          </li>
        </ul>
      </li>

      <li class="nav-heading">Información</li>

      <li class="nav-item">
        <a class="nav-link collapsed">
          <span>&copy; Copyright JC System</span>
        </a>
      </li>
    </ul>
  </aside>

  <!-- Contenido -->
  <main id="main" class="main">
    <div class="pagetitle">
      <h1>Formulario de Usuario</h1>
    </div>
    <% if(errors != null) { %>
    <div class="alert alert-danger" role="alert">
      <ul>
        <% if(errors.containsKey("namesError")) { %>
        <li><%=errors.get("namesError")%></li>
        <% } %>
        <% if(errors.containsKey("lastNamesError")) { %>
        <li><%=errors.get("lastNamesError")%></li>
        <% } %>
        <% if(errors.containsKey("emailError")) { %>
        <li><%=errors.get("emailError")%></li>
        <% } %>
        <% if(errors.containsKey("nroDocuError")) { %>
        <li><%=errors.get("nroDocuError")%></li>
        <% } %>
        <% if(errors.containsKey("usernameError")) { %>
        <li><%=errors.get("namesError")%></li>
        <% } %>
        <% if(errors.containsKey("passError")) { %>
        <li><%=errors.get("passError")%></li>
        <% } %>
      </ul>
    </div>
    <% } %>
    <section class="section">
      <div class="row justify-content-center">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <form class="row g-3 needs-validation mt-2" action="<%=request.getContextPath()%>/control/usuarios/formulario/save" method="post" novalidate>
                <div class="col-md-6">
                  <label for="names" class="form-label">Nombres</label>
                  <input type="text" class="form-control" id="names" name="names" value="<%=nombres%>" required>
                  <div class="invalid-feedback">Porfavor ingrese el nombre.</div>
                </div>
                <div class="col-md-6">
                  <label for="lastNames" class="form-label">Apellidos</label>
                  <input type="text" class="form-control" id="lastNames" name="lastNames" value="<%=apellidos%>" required>
                  <div class="invalid-feedback">Porfavor ingrese el apellido.</div>
                </div>
                <div class="col-md-12">
                  <label for="email" class="form-label">Email</label>
                  <input type="email" class="form-control" id="email" name="email" value="<%=email%>" required>
                  <div class="invalid-feedback">Porfavor ingrese el correo electrónico.</div>
                </div>
                <div class="col-md-6">
                  <label for="tipeDocu" class="form-label">Tipo de documento</label>
                  <select id="tipeDocu" class="form-select" name="tipeDocu" required>
                    <option value="">-Seleccionar-</option>
                    <% for(DocumentType d: documentTypes) { %>
                    <option value="<%=d.getId()%>" <%= documentType == d.getId() ? "selected" : "" %>><%=d.getNombre()%></option>
                    <% } %>
                  </select>
                  <div class="invalid-feedback">Porfavor seleccione el tipo de documento.</div>
                </div>
                <div class="col-md-6">
                  <label for="nroDocu" class="form-label">Número de documento</label>
                  <input type="number" class="form-control" id="nroDocu" name="nroDocu" value="<%=nroDocu%>" required>
                  <div class="invalid-feedback">Porfavor ingrese el numero de documento.</div>
                </div>
                <div class="col-md-4">
                  <label for="username" class="form-label">Nombre de usuario</label>
                  <input type="text" class="form-control" id="username" name="username" value="<%=username%>" required>
                  <div class="invalid-feedback">Porfavor ingrese el nombre de usuario.</div>
                </div>
                <div class="col-md-4">
                  <label for="password" class="form-label">Contraseña</label>
                  <input type="password" class="form-control" id="password" name="password" value="<%=password%>" required>
                  <div class="invalid-feedback">Porfavor ingrese la contraseña.</div>
                </div>
                <div class="col-md-4">
                  <label for="role" class="form-label">Cargo</label>
                  <select id="role" class="form-select" name="role" required>
                    <option value="">-Seleccionar-</option>
                    <% for (UserRole r: roles) { %>
                    <option value="<%=r.getId()%>" <%= rol == r.getId() ? "selected" : "" %>><%=r.getDescripcion()%></option>
                    <% } %>
                  </select>
                  <div class="invalid-feedback">Porfavor seleccione el cargo.</div>
                </div>
                <div class="text-end">
                  <a class="btn btn-danger me-2" href="<%=request.getContextPath()%>/control/usuarios">Volver</a>
                  <button type="submit" class="btn btn-primary" onclick="return confirm('¿Está seguro de guardar los datos?');">Guardar</button>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>

  <!-- Archivos JavaScript -->
  <script src="<%=request.getContextPath()%>/resources/templates/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/jquery.slim.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/validation.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</body>
</html>