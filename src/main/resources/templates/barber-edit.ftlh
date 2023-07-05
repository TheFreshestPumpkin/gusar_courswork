<!DOCTYPE html>
<html>
<head>
    <title>Gusar</title>
</head>
<body>
<h1>Gusar</h1><hr>
<h3>Редактирование мастера ${barber.barbName}</h3>
<form action="/manager/barber/edit" method="post">
    <div>
        Стаж: <input type="number" value="${barber.experience}" name="experience"/>
        <br>
        Уровень мастера: <input type="number" name="rating" value="${barber.intRating()}" min="0" max="5"/>
        <br>
        Описание: <input type="text" value="${barber.description}" name="description"/>
        <br>
    </div>
    <input type="hidden" value="${barber.id}" name="barberId">
    <input type="hidden" value="${_csrf.token}" name="_csrf">
    <button type="submit">Сохранить</button>
</form>
</body>
</html>