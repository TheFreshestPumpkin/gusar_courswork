<!DOCTYPE html>
<html>
    <head>
        <title>Gusar</title>
    </head>

    <body>
        <h1>Будем рады встрече, Гусар!</h1>
        <br>
        <p><a href="/">Вернуться на главную страницу</a></p>
        <h3>Записаться на услугу</h3>
        <br>
        <b>Имя барбера: </b>${barber.barbName}
        <br>
        <#assign hours=["9","10","11","12","13","14","15","16","17","18","19","20"]>
        <#assign mins=["00","30"]>
        <#assign x=0>
        <#list hours as hour>
        <#list mins as min>
            <#if x==timeId>
                <b>${hour}:${min}</b>
            </#if>
            <#assign x++>
        </#list>
        </#list>
        <form action="/barber/${barber.id}/appointment/${timeId}/add" method="post">
            <#if !barber.timeIsBusy[timeId] && !barber.timeIsBusy[timeId+1] && !barber.timeIsBusy[timeId+2]>
                <input type="radio" name="service" value="Мужская стрижка">Мужская стрижка - ${500*3+(barber.intRating()*100)} рублей<br>
            <#else>
                <span style="color: red;" >Мужская стрижка - ${500*3+(barber.intRating()*100)} рублей</span><br>
            </#if>
            <#if !barber.timeIsBusy[timeId] && !barber.timeIsBusy[timeId+1]>
                <input type="radio" name="service" value="Укладка бороды">Укладка бороды - ${500*2+(barber.intRating()*100)} рублей<br>
            <#else>
                <span style="color: red;" >Укладка бороды - ${500*2+(barber.intRating()*100)} рублей</span><br>
            </#if>
            <input type="radio" name="service" value="Бритьё опасной бритвой">Бритье опасной бритвой - ${500*1+(barber.intRating()*100)} рублей<br>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <input type="submit" value="Записаться"/>
        </form>
    </body>
</HTML>