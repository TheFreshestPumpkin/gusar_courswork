<!DOCTYPE html>
<html>
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
      </div>
    </nav>
<h2 style="text-align: center">Подробная информация о пользователе ${user.name}</h2>
<#if user1.isManager() || user1.isAdmin() || (user1.id?? && user1.id==user.id)>
        <b>Номер телефона: </b> ${user.phoneNumber}<br>
        <b>Email: </b> ${user.email}<br>
        <h4>Записи пользователя</h4>
        <#assign hours=["9","10","11","12","13","14","15","16","17","18","19","20","21","22"]>
        <#assign mins=["00","30"]>
        <#list appointments as appointment>
            <#assign x=0>
            <div>
                <p><b>${appointment.service}</b> У мастера ${appointment.barber.barbName}
                <#list hours as hour>
                    <#list mins as min>
                        <#if x==appointment.timeId> <span> c </span> ${hour}:${min} </#if>
                        <#if x==(appointment.timeId + appointment.duration)> <span> до </span>${hour}:${min}</#if>
                        <#assign x++>
                    </#list>
                </#list>
                </p>
            </div>
            <#else>
            <h3>записей нет</h3>
        </#list>
<#else><p>У вас недостаточно прав для просмотра данной страницы</p>
</#if>
</body>
</html>