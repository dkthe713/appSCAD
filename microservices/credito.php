<?php include ('functions.php');
	$cedula=$_GET['cedula'];
	$cuenta=$_GET['cuenta'];
	$prestamo=1;
	$monto=$_GET['monto'];

	ejecutarSQLCommand("INSERT INTO  `transaccion` (cedula,cuenta,prestamo,monto)
	VALUES (
	'$cedula' ,
	'$cuenta' ,
	'$prestamo' ,
	'$monto')

	ON DUPLICATE KEY UPDATE `cedula`= '$cedula',
	`cuenta`='$cuenta',
	`prestamo`='$prestamo',
	`monto`='$monto';");
 ?>