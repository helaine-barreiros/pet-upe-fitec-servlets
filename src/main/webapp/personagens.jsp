<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Aula 06 - Implementação de Servlet - Vebo GET</title>
    </head>

    <body>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Dosis:wght@200&display=swap');

            *,
            *::before,
            *::after {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
                font-family: 'Dosis', sans-serif;
            }

            body {
                background: #2c302d;
            }

            h3 {
                font-size: .9rem;
                font-weight: 400;
                margin-top: 20px;
            }

            h2 {
                font-size: 2rem;
                font-weight: 600;
                letter-spacing: 1px;
                margin: 10px 30px;
            }

            .container {
                color: #fff;
                text-align: center;
                position: absolute;
                width: 100%;
                height: 100vh;
            }

            .card {
                width: 70%;
                height: 100%;
                margin-top: 20px;
                text-align: center;
                background: #242625;
                border-radius: 10px;
            }

            .card__container {
                display: grid;
                grid-gap: 20px;
                width: 100%;
                grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
                grid-auto-rows: minmax(190px, auto);
            }

            .card__content {
                width: 90%;
                height: 100%;
                background: #191a19;
                margin: 5px auto;
                border-radius: 5px;
                padding: 5px;
                cursor: pointer;
                box-shadow: 16px 16px 44px #0a0a0a,
                    -16px -16px 44px #282a28;
                transition: 0.3s all ease-in-out;
            }

            .card__header {
                text-transform: uppercase;
                font-size: 20px;
                margin: 10px auto;
            }

            .card__img {
                width: 50%;
            }
        </style>
        <div class="container">
            <h3>[ #Aula 06 - Servlets - Verbo Get ]</h3>
            <h2>Personagens do Rick & Morty</h2>
            <c:choose>
                <c:when test="${erro}">
                    <p>${erro}</p>
                </c:when>
                <c:otherwise>
                    <div class="card__container">
                        <c:forEach items="${personagens.results}" var="personagem">
                            <div class="card__content">
                                <h3 class="card__header">
                                    ${personagem.nome}
                                </h3>
                                <figure>
                                    <img class="card__img" src="${personagem.image}" alt="${personagem.nome}">
                                </figure>
                                <p>[Gender]:${personagem.gender}</p>
                                <p>[Species]:${personagem.species}</p>
                                <p>[Status]:${personagem.status}</p>
                            </div>
                        </c:forEach>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>

    </html>