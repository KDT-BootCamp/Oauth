<%@ page 	language="java" 
			contentType="text/html; charset=UTF-8"
    		pageEncoding="UTF-8" %>

<html>
    <head>
        <style>
            .center{
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                flex-direction: column;
            }
            .btn{
                width: 150px;
                height: 150px;
                margin-bottom: 10px;
                text-align: center;
            }
            .btn-kakao{
                background-color: yellow;
                color: black;
            }
        </style>
    </head>
    <body>
        <div class="center">
            <a class="btn btn-kakao" href="/oauth2/authorization/kakao">Kakao Login</a>
            <a class="btn btn-kakao" href="/oauth2/authorization/Naver">Naver Login</a>
            <a class="btn btn-kakao" href="/oauth2/authorization/Google">Google Login</a>
        </div>

    </body>
</html>
