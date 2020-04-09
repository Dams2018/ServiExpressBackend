Servicios aquí están ejemplo de creación de usuario y creación de cliente

Una vez iniciado el proyecto insertar los siguientes roles y creación de administrador la contraseña de admin es 123456 solo que se inserta encriptada


Insert into ROLES (ID,NAME) values ('1','ROLE_ADMIN');
Insert into ROLES (ID,NAME) values ('2','ROLE_USER');
Insert into ROLES (ID,NAME) values ('3','ROLE_EMPLOYE');
Insert into ROLES (ID,NAME) values ('4','ROLE_PROVIDER');

Insert into USERS (ID,CREATED_AT,UPDATED_AT,ACTIVE,EMAIL,NAME,PASSWORD,USERNAME) values ('0',to_timestamp('06/04/20 21:55:54,877000000','DD/MM/RR HH24:MI:SSXFF'),to_timestamp('06/04/20 21:55:54,877000000','DD/MM/RR HH24:MI:SSXFF'),0,'mar.astorgag@alumnos.duoc.cl','admin','$2a$10$DaTuufdd/jZpScQKO18BRu96pZz5Lqn5Yjwl9jSEATRguk273X7H.','admin');
Insert into USER_ROLES (USER_ID,ROLE_ID) values ('0','1');


COMMIT WORK;


SELECT * FROM ROLES;

SELECT * FROM USERS;

SELECT * FROM USER_ROLES;


Creación de cuenta usuario cliente PUT
el campo role no se toma en cuenta ya que, por defecto tendrá el rol 1
http://127.0.0.1:8090/api/auth/signup

{
	"name" : "marco",
	"username" : "dams",
	"email" :"dams@live.cl",
	"password" : "1234567",
	"role" : "1"
}

Login
http://127.0.0.1:8090/api/auth/signin POST

{
	"usernameOrEmail" : "dams",
	"password" : "1234567"
}
Te devolverá un token parecido a este

Respuesta
{
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNTg2NDQ2NjExLCJleHAiOjE1ODcwNTE0MTF9.vS9FEyAIFeZIX4ICNH19Afw6O4PwAsPzPQ2mbKyc958nsOILmghoRglBEkZquakX_I1fef1tzHeVMQwdQB8wlw",
    "tokenType": "Bearer"
}

Creacion de cliente PUT
http://127.0.0.1:8090/entidad/cliente
recuerda cambiar el id usuario por el usuario cliente 
{
	"id_usuario" :1,
	"rut" : "1234567-8",
	"nombre" : "marco",
	"apellido" : "astorga",
	"telefono" : "324234",
	"fechaNacimiento" : "1989-09-10"
}


Para el cambio de contraseña PUT
PRIMERO 
Enviar por url (username)=al nombre de usuario que solicitara el cambio de contraseña
http://127.0.0.1:8090/api/auth/requestpass/username
respuesta
Código de usuario encriptado
eyJhbGciOiJBMTI4S1ciLCJlbmMiOiJBMTI4Q0JDLUhTMjU2In0.Nv6r_I3Irg7AKNr-PO0lni_dXCzoX_D1SjFNm0uEhSnkcw_h7at6sA.gGxuKBsoxLSo7fVCtlefMQ.rWwFkmnigwlZbNUsQZCg9w.4ypH_Kp1bIkj7iB9lC-blA

SEGUNDO
después de /api/auth/ insertar el código en la url 
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

para el proceso de empleado y proveedor está en desarrollo pero en teoría seria los mismo
