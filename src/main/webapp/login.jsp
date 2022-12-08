<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Login | JC System</title>

  <!-- Icono pestaña -->
  <link href="<%=request.getContextPath()%>/resources/img/favicon.png" rel="icon">

  <!-- Archivos CSS -->
  <link href="<%=request.getContextPath()%>/resources/templates/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/templates/bootstrap/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="<%=request.getContextPath()%>/resources/css/style.css" rel="stylesheet">
</head>
<body>

  <!-- Contenido -->
  <main>
    <div class="container">
      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="d-flex justify-content-center py-4">
                <a href="" class="logo d-flex align-items-center w-auto">
                  <img src="<%=request.getContextPath()%>/resources/img/logo.png" alt="">
                  <span class="d-none d-lg-block">JC System</span>
                </a>
              </div>

              <div class="card mb-3">
                <div class="card-body">
                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">Inicio de Sesión</h5>
                    <p class="text-center small">Ingrese su nombre de usuario y contraseña</p>
                  </div>

                  <form class="row g-3 needs-validation" action="<%=request.getContextPath()%>/sesion" method="post" novalidate>

                    <div class="col-12">
                      <label for="username" class="form-label">Nombre de usuario</label>
                      <div class="input-group has-validation">
                        <span class="input-group-text" id="inputGroupPrepend">@</span>
                        <input type="text" name="username" class="form-control" id="username" required>
                        <div class="invalid-feedback">Porfavor ingrese su nombre de usuario.</div>
                      </div>
                    </div>

                    <div class="col-12">
                      <label for="password" class="form-label">Contraseña</label>
                      <input type="password" name="password" class="form-control" id="password" required>
                      <div class="invalid-feedback">Porfavor ingrese su contraseña</div>
                    </div>

                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="submit">Ingresar</button>
                    </div>
                  </form>
                </div>
              </div>

              <% if (request.getAttribute("invalidLogin") != null) {%>
              <div class="alert alert-danger" role="alert">
                  <%=request.getAttribute("invalidLogin")%>
              </div>
              <% } %>

            </div>
          </div>
        </div>
      </section>
    </div>
  </main>

  <!-- Archivos JavaScript -->
  <script src="<%=request.getContextPath()%>/resources/templates/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/jquery.slim.min.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/validation.js"></script>
  <script src="<%=request.getContextPath()%>/resources/js/main.js"></script>
</body>
</html>