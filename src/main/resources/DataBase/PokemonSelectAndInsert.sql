/*SELECT*/
SELECT * FROM usuario;

SELECT * FROM entrenador;

SELECT * FROM pokemon;

SELECT COUNT(*) FROM entrenador WHERE usuario_id_usuario = 1;

/*USUARIO*/
INSERT INTO usuario(nombre, apellido, email, nick, contraseña)VALUES("hola","mundo","hola@mundo.com","holamundo","Hola12345!");
INSERT INTO usuario(nombre, apellido, email, nick, contraseña)VALUES("adios","espacio","adios@espacio.com","adiosespacio","Adios12345!");


/*ENTRENADORES*/
INSERT INTO entrenador(nombre, fecha_nacimiento, nacionalidad, genero, usuario_id_usuario)VALUES("Kiara","1988-12-03","Argentino","Femenino",1);
INSERT INTO entrenador(nombre, fecha_nacimiento, nacionalidad, genero, usuario_id_usuario)VALUES("Nicolas","2004-12-03","Argentino","Masculino",2);

/*POKEMONES*/
INSERT INTO pokemon(tipo, especie, poder)VALUES("Agua","Squirtle",55);
INSERT INTO pokemon(tipo, especie, poder)VALUES("Fuego","Charmander ",45);
INSERT INTO pokemon(tipo, especie, poder)VALUES("Planta","Bulbasaur",25);
INSERT INTO pokemon(tipo, especie, poder)VALUES("Piedra","Onix ",35);
INSERT INTO pokemon(tipo, especie, poder)VALUES("Electrico","Pikachu ",55);

INSERT INTO pokemon(tipo, especie, poder,entrenador_id_entrenador)VALUES("Agua","Squirtle",55,1);
INSERT INTO pokemon(tipo, especie, poder,entrenador_id_entrenador)VALUES("Electrico","Pikachu",55,2);