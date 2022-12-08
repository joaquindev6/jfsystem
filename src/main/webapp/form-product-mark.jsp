<%@ page import="com.company.app.models.MarkProduct" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.company.app.models.User" %>
<%@ page import="com.company.app.util.TextFormatString" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Map<String, String> errors = (Map<String, String>) request.getAttribute("errorsView");
  MarkProduct markProduct = (MarkProduct) request.getAttribute("mark");

  String nombre = "";
  String descripcion = "";
  if (markProduct != null) {
    nombre = markProduct.getNombre();
    descripcion = markProduct.getDescripcion();
  }

  User user = (User) session.getAttribute("userLogin");
  String nombreCompleto = "";
  if (user != null) {
    nombreCompleto = user.getNombres().split(" ")[0] + " " + user.getApellidos().split(" ")[0];
  }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Registro de Marca | JC System</title>

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
      <i class="bi bi-list toggle-sidebar-btn"></i>
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
              <span><%=TextFormatString.formatTextData(user.getUserRole().getDescripcion())%></span>
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

      <% if ("ROLE_ADMIN".equals(user.getUserRole().getNombre())) { %>
      <li class="nav-item">
        <a class="nav-link collapsed" data-bs-target="#control" data-bs-toggle="collapse" href="#">
          <i class="bi bi-journal-text"></i><span>Control</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="control" class="nav-content collapse" data-bs-parent="#sidebar-nav">
          <li>
            <a href="<%=request.getContextPath()%>/control/usuarios">
              <i class="bi bi-circle"></i><span>Usuarios</span>
            </a>
          </li>
        </ul>
      </li>
      <% } %>

      <li class="nav-item">
        <a class="nav-link" data-bs-target="#inventory" data-bs-toggle="collapse" href="#">
          <i class="bi bi-boxes"></i><span>Inventario</span><i class="bi bi-chevron-down ms-auto"></i>
        </a>
        <ul id="inventory" class="nav-content collapse show" data-bs-parent="#sidebar-nav">
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
            <a href="<%=request.getContextPath()%>/inventario/marca-producto" class="active">
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
      <h1>Formulario de Marca</h1>
    </div>
    <% if(errors != null) { %>
    <div class="alert alert-danger" role="alert">
      <ul>
        <% if(errors.containsKey("nameError")) { %>
        <li><%=errors.get("nameError")%></li>
        <% } %>
      </ul>
    </div>
    <% } %>
    <section class="section">
      <div class="row justify-content-center">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <form class="row g-3 needs-validation mt-2" action="<%=request.getContextPath()%>/inventario/marca-producto/formulario/save" method="post" novalidate>
                <div class="col-md-12">
                  <label for="name" class="form-label">Nombre de la marca</label>
                  <input type="text" class="form-control" id="name" name="name" value="<%=nombre%>" required>
                  <div class="invalid-feedback">Porfavor ingrese el nombre de la marca.</div>
                </div>
                <div class="col-md-12">
                  <label for="description" class="form-label">Descripción</label>
                  <textarea class="form-control" id="description" name="description"><%=descripcion%></textarea>
                </div>
                <div class="text-end">
                  <a class="btn btn-danger me-2" href="<%=request.getContextPath()%>/inventario/marca-producto">Volver</a>
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