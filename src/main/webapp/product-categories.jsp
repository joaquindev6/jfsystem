<%@ page import="java.util.List" %>
<%@ page import="com.company.app.models.CategoryProduct" %>
<%@ page import="com.company.app.models.User" %>
<%@ page import="com.company.app.util.TextFormatString" %>
<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%
  List<CategoryProduct> categories = (List<CategoryProduct>) request.getAttribute("categories");
  String successMessage = (String) request.getAttribute("success");

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
  <title>Categorías de Productos | JC System</title>

  <!-- Icono pestaña -->
  <link href="<%=request.getContextPath()%>/resources/img/favicon.png" rel="icon">

  <!-- Archivos CSS -->
  <link href="<%=request.getContextPath()%>/resources/templates/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/templates/bootstrap/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/templates/datatables/datatables.min.css" rel="stylesheet">
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
            <a href="<%=request.getContextPath()%>/inventario/categoria-producto" class="active">
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
    <% if (successMessage != null) { %>
    <div class="alert alert-success" role="alert">
      <%=successMessage%>
    </div>
    <% } %>
    <div class="pagetitle">
      <h1>Control Categorías de Productos</h1>
    </div>
    <!-- Botones -->
    <div class="row">
      <div class="col-lg-3">
        <a class="btn btn-primary me-2 mb-4" href="<%=request.getContextPath()%>/inventario/categoria-producto/formulario">Nueva Categoría</a>
        <a class="btn btn-secondary me-2 mb-4" href="<%=request.getContextPath()%>/inventario/categoria-producto/reporte-de-categorias-de-productos.pdf" target="_blank">Imprimir PDF</a>
      </div>
    </div>
    <section class="section">
      <div class="row justify-content-center">
        <div class="col-lg-12">
          <div class="card">
            <div class="card-body">
              <div class="row mt-3">
                <div class="col-lg-12 overflow-hidden">
                  <!-- Tabla de datos -->
                  <table id="table" class="table table-hover">
                    <thead>
                      <tr class="text-center">
                        <th class="text-center">ID</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">Descripción</th>
                        <th class="text-center">Acciones</th>
                      </tr>
                    </thead>
                    <tbody>
                      <% for (CategoryProduct c: categories) { %>
                      <tr>
                        <td class="text-center"><%=c.getId()%></td>
                        <td><%=c.getNombre()%></td>
                        <td><%=c.getDescripcion()%></td>
                        <td class="text-center">
                          <div class="d-flex justify-content-center align-items-center">
                            <a class="btn btn-success me-2" href="<%=request.getContextPath()%>/inventario/categoria-producto/data-show?id=<%=c.getId()%>"><i class="bi bi-pencil-fill"></i></a>
                            <a class="btn btn-danger" href="<%=request.getContextPath()%>/inventario/categoria-producto/data-show?idDelete=<%=c.getId()%>"
                               onclick="return confirm('¿Está seguro de eliminar el registro?');"><i class="bi bi-trash-fill"></i></a>
                          </div>
                        </td>
                      </tr>
                      <% } %>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>

  <!-- Archivos JavaScript -->
  <script src="<%=request.getContextPath()%>/resources/templates/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/jquery.slim.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/templates/datatables/datatables.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/tabla-edit.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</body>
</html>