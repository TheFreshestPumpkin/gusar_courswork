<!DOCTYPE html>
<html>
<head>
    <title>Gusar</title>
</head>
<body>
<h1>Горячо приветствую, Гусар!</h1>
<hr>
<h4>Панель администратора</h4>
<br>
<p><a href="/">Вернуться на главную страницу</a></p>
<table border="1">
    <tr>
        <th>Email</th>
        <th>Номер телефона</th>
        <th>Активность</th>
        <th>Роли</th>
        <th>Удалить</th>
        <th>Редактирование</th>
        <th>Подробная информация</th>
    </tr>
    <#list users as user>
        <tr>
            <th><#if user.email??>${user.email}<#else>none</#if></th>
            <th><#if user.phoneNumber??>${user.phoneNumber}<#else>none</#if></th>
            <th><#if user.active>true<#else>false</#if></th>
            <th><#list user.roles as role>${role}<#sep>, </#sep> <#else>none</#list></th>
            <th><a href="/admin/user/delete/${user.id}">удалить</a></th>
            <th><a href="/admin/user/edit/${user.id}">Редактировние</a></th>
            <th><a href="/user/${user.id}">Подробная информация</a></th>
        </tr>
    <#else>
        <h3>Пользователей нет</h3>
    </#list>
</table>
</body>
</html>