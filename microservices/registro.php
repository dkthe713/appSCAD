<?php include ('functions.php');
	$nombre=$_GET['nombre'];
	$cedula=$_GET['cedula'];

	ejecutarSQLCommand("INSERT INTO  `transaccion` (nombre, cedula)
	VALUES (
	'$nombre' ,
	'$cedula')

	ON DUPLICATE KEY UPDATE `nombre`= '$nombre',
	`cedula`='$cedula';");
 ?>