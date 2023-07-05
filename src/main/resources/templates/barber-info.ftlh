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
        <a class="navbar-brand" href="/">Gusar</a>
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

        <#list images as image>
            <br>
            <img src="/images/${image.id}" height="200px"/>
            <br>
        </#list>
        <b>Имя барбера: </b>${barber.barbName}
        <br>
        <b>Стаж: </b>${barber.experience}
        <br>
        <b>Текущий рейтинг: </b>${barber.rating}
        <br>
        <b>Описание: </b>${barber.description}
        <p>Запись на сегодня:</p>
        <#assign hours=["9","10","11","12","13","14","15","16","17","18","19","20"]>
        <#assign mins=["00","30"]>
        <#assign x=0>
        <p>
        <#list hours as hour>
            <#list mins as min>
                <#if !schedule[x]>
                    <#if user.email??>  <a style="color: green;" href="/barber/${barber.id}/appointment/${x}">${hour}:${min}</a>
                    <#else> <a style="color: green;" href="/login">${hour}:${min}</a>
                    </#if>
                <#else>
                    <#if user.isManager() || user.isAdmin()>
                        <#list appointments as appointment>
                            <#if appointment.timeId==x>
                                <#assign y=x,appoint=appointment>
                                <a style="color: orange;" href="/user/${appoint.client.id}">${hour}:${min}</a>
                            </#if>
                        </#list>
                        <#if x!=y><a style="color: red;" href="/user/${appoint.client.id}">${hour}:${min}</a></#if>
                    <#else><span style="color: red;">${hour}:${min}</span>
                    </#if>
                </#if>
                <#assign x++>
            </#list>
        </#list>
        </p>
    </body>
</HTML>