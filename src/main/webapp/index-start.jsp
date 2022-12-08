<%@ page import="com.company.app.models.User" %>
<%@ page import="com.company.app.util.TextFormatString" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  long cantUser = 0L;
  long cantProduct = 0L;
  long cantCategory = 0L;
  long cantMark = 0L;

  User user = (User) session.getAttribute("userLogin");
  String nombreCompleto = "";
  String descripcion = "";
  if (user != null) {
    cantUser = (long) request.getAttribute("cantUser");
    cantProduct = (long) request.getAttribute("cantProduct");
    cantCategory = (long) request.getAttribute("cantCategory");
    cantMark = (long) request.getAttribute("cantMark");

    nombreCompleto = user.getNombres().split(" ")[0] + " " + user.getApellidos().split(" ")[0];
    descripcion = TextFormatString.formatTextData(user.getUserRole().getDescripcion());
  }
%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Inicio | JC System</title>

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
      <i class="bi bi-list toggle-sidebar-btn"></i><!-- Icono de menu -->
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
              <span><%=descripcion%></span>
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
        <a class="nav-link" href="<%=request.getContextPath()%>/inicio">
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
      <h1>Estadísticas</h1>
    </div>

    <section class="section dashboard">
      <div class="row">

        <!-- Tarjeta usuarios registrados -->
        <% if ("ROLE_ADMIN".equals(user.getUserRole().getNombre())) { %>
        <div class="col-md-6 col-lg-3">
          <a href="<%=request.getContextPath()%>/control/usuarios">
            <div class="card info-card sales-card">
              <div class="card-body">
                <h5 class="card-title">Usuarios</h5>
                <div class="d-flex align-items-center">
                  <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                    <i class="bi bi-people"></i>
                  </div>
                  <div class="ps-3">
                    <h6><%=cantUser%></h6>
                  </div>
                </div>
              </div>
            </div>
          </a>
        </div>
        <% } %>
        
        <!-- Tarjeta productos registrados -->
        <div class="col-md-6 col-lg-3">
          <a href="<%=request.getContextPath()%>/inventario/productos">
            <div class="card info-card revenue-card">
              <div class="card-body">
                <h5 class="card-title">Productos</h5>
                <div class="d-flex align-items-center">
                  <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                    <i class="bi bi-boxes"></i>
                  </div>
                  <div class="ps-3">
                    <h6><%=cantProduct%></h6>
                  </div>
                </div>
              </div>
            </div>
          </a>
        </div>
        
        <!-- Tarjeta de categorias de productos -->
        <div class="col-md-6 col-lg-3">
          <a href="<%=request.getContextPath()%>/inventario/categoria-producto">
            <div class="card info-card customers-card">
              <div class="card-body">
                <h5 class="card-title">Categorías de productos</h5>
                <div class="d-flex align-items-center">
                  <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                    <i class="bi bi-card-list"></i>
                  </div>
                  <div class="ps-3">
                    <h6><%=cantCategory%></h6>
                  </div>
                </div>
              </div>
            </div>
          </a>
        </div>
        
        <!-- Tarjeta de marcas de productos -->
        <div class="col-md-6 col-lg-3">
          <a href="<%=request.getContextPath()%>/inventario/marca-producto">
            <div class="card info-card marks-card">
              <div class="card-body">
                <h5 class="card-title">Marcas de productos</h5>
                <div class="d-flex align-items-center">
                  <div class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                    <i class="bi bi-bookmark-check"></i>
                  </div>
                  <div class="ps-3">
                    <h6><%=cantMark%></h6>
                  </div>
                </div>
              </div>
            </div>
          </a>
        </div>
      </div>
    </section>
  </main>
  <!-- Archivos JavaScript -->
  <script src="<%=request.getContextPath()%>/resources/templates/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/jquery.slim.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</body>
</html>