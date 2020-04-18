# ServiExpressBackend

Este projecto es generado [SPRINGBOOT](https://spring.io/) 
>Es necesario Eclipse o Visual Studio Code e instalar SpringBoot y Maven para este proyecto

> Backend  SpringBoot
>La creación de tablas se realiza mediante hibérnate, por ende, no es necesario tener un script de base de dato, solo será necesario >insertar los datos que están a continuación.
>Estos servicios, para poder probarlo se deberá utilizar algún cliente REST por ejemplo [POSTMAN](https://www.postman.com/).

>FrontEnd Angular
>Aquí podrá encontrar el cliente el que se esta conectando a estos servicios
>[ServiExpressFront](https://github.com/juliojimenez-98/ServiExpressFront)




# ServiExpress
> Inserción de roles y cuenta de adminitrador
```
Servicios aquí están ejemplo de creación de usuario y creación de cliente


Una vez iniciado el proyecto insertar los siguientes roles y creación de administrador la contraseña de admin es 123456 solo que se inserta encriptada



Insert into ROLES (ID,NAME) values ('1','ROLE_ADMIN');
Insert into ROLES (ID,NAME) values ('2','ROLE_CLIENT');
Insert into ROLES (ID,NAME) values ('3','ROLE_EMPLOYE');
Insert into ROLES (ID,NAME) values ('4','ROLE_COMPANY');



Insert into USERS (ID,CREATED_AT,UPDATED_AT,ACTIVE,EMAIL,NAME,PASSWORD,USERNAME) values ('0',to_timestamp('06/04/20 21:55:54,877000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('06/04/20 21:55:54,877000000','DD/MM/RR HH24:MI:SSXFF'),0,'mar.astorgag@alumnos.duoc.cl','admin','$2a$10$DaTuufdd/jZpScQKO18BRu96pZz5Lqn5Yjwl9jSEATRguk273X7H.','admin');
Insert into USER_ROLES (USERID,ROLEID) values ('0','1');


COMMIT WORK;


SELECT * FROM ROLES;

SELECT * FROM USERS;

SELECT * FROM USER_ROLES;

```
# Crear cuenta empleado o cliente
> Creación de cuenta usuario cliente o cliente empresa PUT
> rol 2 cliente
> rol 4 cliente empresa

## Signup PUT
```

http://127.0.0.1:8090/api/auth/signup

{
	"name" : "marco",
	"username" : "dams",
	"email" :"dams@live.cl",
	"password" : "1234567",
	"role" : "2"
}

```
## Login POST
```
http://127.0.0.1:8090/api/auth/signin POST

{
	"usernameOrEmail" : "dams",
	"password" : "1234567"
}
Te devolverá un token parecido a este

Respuesta
{
    "iduser": 0,
    "idrole": 1,
    "Avtivo": false,
    "rolename": "ROLE_ADMIN",
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIwIiwiaWF0IjoxNTg3MjIxMjA5LCJleHAiOjE1ODc4MjYwMDl9.xek-608wKSg7xBs6Xzpf7v89fp95kq5N3AAV2zDAnJYXJR7aRthdrIGbic8BTqPTzCJoQjI3p2eiocPSxZwxtg",
    "tokenType": "Bearer",
    "username": "admin"
}

```
## Creación de cliente PUT
```

http://127.0.0.1:8090/entidad/cliente
recuerda cambiar el id usuario por el usuario cliente creado, para el frontend se debe hacer de manera interna.
{
	"id_usuario" :1,
	"rut" : "1234567-8",
	"nombre" : "marco",
	"apellido" : "astorga",
	"telefono" : "324234",
	"fechaNacimiento" : "1989-09-10"
}

```
## Actualizar de cliente POST
```

http://127.0.0.1:8090/entidad/cliente
{
	"idcliente" :13,
	"id_usuario" :1,
	"rut" : "174244979",
	"nombre" : "Marco",
	"apellido" : "Astorga",
	"telefono" : "324234",
	"fechaNacimiento" : "1989-09-10"
}

```
## Cambio de contraseña PUT


> PRIMERO 
```
Enviar por url (username)=al nombre de usuario que solicitara el cambio de contraseña
http://127.0.0.1:8090/api/auth/requestpass/username
respuesta
Código de usuario encriptado
eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.Nv6r_I3Irg7AKNr-PO0lni_dXCzoX_D1SjFNm0uEhSnkcw_h7at6sA.gGxuKBsoxLSo7fVCtlefMQ.rWwFkmnigwlZbNUsQZCg9w.4ypH_Kp1bIkj7iB9lC-blA
```
> SEGUNDO
```
Después de /api/auth/ insertar el código en la url 
http://127.0.0.1:8090/api/auth/changepassword/eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.Nv6r_I3Irg7AKNr-PO0lni_dXCzoX_D1SjFNm0uEhSnkcw_h7at6sA.gGxuKBsoxLSo7fVCtlefMQ.rWwFkmnigwlZbNUsQZCg9w.4ypH_Kp1bIkj7iB9lC-blA

Body
{
	"password" : "1234567"
}

si esta todo ok devolvera respuesta
{
    "peticion": {
        "fechaRsp": "09-04-2020",
        "horaRsp": "10:29:29",
        "mensaje": "El cambio de contraseña pudo efectuarse correctamente",
        "codigo": "OK-200"
    }
}

```
# Proceso admin o empleado

## Login POST
```
Ingrese con User admin y contraseña 123456 creado con
http://127.0.0.1:8090/api/auth/signin POST

{
	"usernameOrEmail" : "admin",
	"password" : "123456"
}

```
## Signup Empleado Admin PUT 
```
Solo el administrador puede crear estas cuenta, el cual los datos de la cuentas se le enviara al correo registrado, para
que el adminirador o empleado nuevo continuen con sus datos personales.
rol 1 admin
rol 3 empleado

Para ingresar a esta ruta se debe tener token del admin.
http://127.0.0.1:8090/api/auth/signupwork

{
	"name" : "marco",
	"username" : "dams",
	"email" :"dams@live.cl",
	"password" : "1234567",
	"role" : "1"
}

```
# Listar Clientes

## Listar Todos los Clientes GET NO RECOMENDADO (Test Mode)
```
al igual que crear empleado se debe iniciar con cuenta adiestrador y obtener token para tener acceso a este rest
http://127.0.0.1:8090/entidad/allclientes
retornara lista de clientes
[
    {
        "idcliente": 2,
        "id_usuario": null,
        "rut": "2434",
        "nombre": "marco",
        "apellido": "astorga",
        "telefono": "234234",
        "fechaNacimiento": null
    },
    {
        "idcliente": 3,
        "id_usuario": null,
        "rut": "2434",
        "nombre": "marco",
        "apellido": "astorga",
        "telefono": "234234",
        "fechaNacimiento": null
    },
]
```
## Listar cliente a modo paginación GET RECOMENDADO
```
http://127.0.0.1:8090/entidad/clientes?page=0&size=3
http://127.0.0.1:8090/entidad/clientes?page=0

?page=1 Numero de la pagina por defecto traerá 10 objeto
&size=3 Cantidad objeto por pagina

[
    {
        "idcliente": 2,
        "id_usuario": null,
        "rut": "2434",
        "nombre": "marco",
        "apellido": "astorga",
        "telefono": "234234",
        "fechaNacimiento": null
    },
    {
        "idcliente": 3,
        "id_usuario": null,
        "rut": "2434",
        "nombre": "marco",
        "apellido": "astorga",
        "telefono": "234234",
        "fechaNacimiento": null
    },
    {
        "idcliente": 4,
        "id_usuario": null,
        "rut": "2434",
        "nombre": "marco",
        "apellido": "astorga",
        "telefono": "234234",
        "fechaNacimiento": null
    }
]

```
