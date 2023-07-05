<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gusar</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    </head>

    <body>
    <nav class="navbar navbar-expand-lg bg-body-tertiary" data-bs-theme="dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Gusar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarText">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <#if user.email??>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/user/${user.id}"> Мой профиль </a>
                </li>
                <#if user.isAdmin()>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/admin"> Панель администратора </a>
                    </li>
                </#if>
                <#if user.isManager()>
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/manager"> Панель менеджера </a>
                    </li>
                </#if>
                <li class="position-absolute top-0 end-0">
                    <form action="/logout" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-light" value="Выйти"/>
                    </form>
                </li>
              </ul>
            <#else>
                <span class="position-absolute top-0 end-0">
                    <a class="nav-link active" aria-current="page"  href="/login">Войти</a>
                </span>
            </#if>
        </div>
      </div>
    </nav>
    <br>
        <h3>Наши Барберы</h3>
        <hr>
        <form action="/" method="get">
            Найти мастера по имени: <input type="text" name="barbName"><br>
            <input type="submit" value="Поиск"/>
        </form>
        <div class="row row-cols-1 row-cols-md-3 g-4">
            <#list barbers as barber>
                <div class="col" style="width: 20rem; height: 27rem;">
                    <div class="card" style="width: 18rem; height: 25rem;">
                        <img src="/images/${barber.previewImageId}" class="img-thumbnail" style="width: 100%; height: 60%; object-fit: cover;">
                        <div class="card-body">
                            <h5 class="card-title">${barber.barbName}</h5>
                            <p>уровень барбера - ${barber.rating}</p>
                            <p><a href="/barber/${barber.id}">Подробнее... </a></p>
                            <#if user.isManager()><p><a href="/manager/barber/delete/${barber.id}">удалить</a></p></#if>
                        </div>
                    </div>
                </div>
                <#else>
                <h4>Берберы не найдены</h4>
            </#list>
        </div>
        <#if user.isManager()>
        <div class="accordion accordion-flush" id="accordionFlushExample">
          <div class="accordion-item">
            <h2 class="accordion-header">
              <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapseOne" aria-expanded="false" aria-controls="flush-collapseOne">
                Добавить мастера
              </button>
            </h2>

            <div id="flush-collapseOne" class="accordion-collapse collapse" data-bs-parent="#accordionFlushExample">
              <div class="accordion-body">
                <div style="width: 70%; padding-left: 10px;">
                    <form action="/barber/add" method="post" enctype="multipart/form-data">

                        <div class="input-group mb-3">
                          <span class="input-group-text" id="basic-addon1">Имя</span>
                          <input type="text" class="form-control" aria-label="name" aria-describedby="basic-addon1" name="barbName"/>
                        </div>

                        <div class="input-group mb-3">
                          <span class="input-group-text" id="basic-addon1">Опыт работы</span>
                          <input type="number" name="experience" class="form-control" aria-label="name" aria-describedby="basic-addon1"/>
                        </div>

                        <div class="input-group mb-3">
                          <span class="input-group-text" id="basic-addon1">Уровень мастера</span>
                          <input type="number" name="rating" value="0" min="0" max="5" class="form-control" placeholder="experience" aria-label="name" aria-describedby="basic-addon1"/>
                        </div>

                        <div class="input-group">
                          <span class="input-group-text">Описание</span>
                          <textarea class="form-control" aria-label="description" name="description"></textarea>
                        </div>
                        <br>
                        <div class="input-group mb-3">
                          <input type="file" class="form-control" id="inputGroupFile01" name="file1"/>
                          <label class="input-group-text" for="inputGroupFile01">Upload</label>
                        </div>

                        <div class="input-group mb-3">
                          <input type="file" class="form-control" id="inputGroupFile02" name="file2"/>
                          <label class="input-group-text" for="inputGroupFile02">Upload</label>
                        </div>

                        <div class="input-group mb-3">
                          <input type="file" class="form-control" id="inputGroupFile03" name="file3"/>
                          <label class="input-group-text" for="inputGroupFile03">Upload</label>
                        </div>

                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" value="добавить мастера"/>
                        <br>
                    </form>
              </div>
            </div>
          </div>
        </div>
        </#if>


        <br>
        <br>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
       </div>
    </body>
</HTML>