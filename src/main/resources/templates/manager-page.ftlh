<!DOCTYPE html>
<html>
<head>
    <title>Gusar</title>
</head>
<body>
<h1>Gusar</h1>
<hr>
<h4>Панель Менеджера</h4>
<br>
<p><a href="/">Вернуться на главную страницу</a></p>
<#assign hours=["9","10","11","12","13","14","15","16","17","18","19","20","21","22"]>
<#assign mins=["00","30"]>
<table border="1">
    <tr>
        <th>Барбер</th>
        <#list hours as hour>
            <#list mins as min>
                <th>${hour}:${min}</th>
            </#list>
        </#list>
        <th>Редактировать</th>
    </tr>
    <#list barbers as barber>
        <tr>
            <th><#if barber.barbName??>${barber.barbName}<#else>none</#if></th>
            <#list 0..27 as x>
                <th>
                    <#if barber.timeIsBusy[x]>
                        <#list barber.appointments as appointment>
                            <#if appointment.timeId==x><#assign name=appointment.client.name id=appointment.client.id></#if>
                        </#list>
                        <a style="color: black;" href="/user/${id}">${name}</a>
                    <#else> <span>---</span>
                    </#if>
                </th>
            </#list>
            <th><a href="/manager/barber/edit/${barber.id}">Редактировние</a></th>
        </tr>
    <#else>
        <h3>>Мастеров нет</h3>
    </#list>
</table>
</body>
</html>