<?php include ('functions.php');
	$cuentaE=$_GET['cuentaE'];
	$cuentaR=$_GET['cuentaR'];
	$monto=$_GET['monto'];

	ejecutarSQLCommand("INSERT INTO  `transferencia` (cuentaE,cuentaR,monto)
	VALUES (
	'$cuentaE' ,
	'$cuentaR' ,
	'$monto')

	ON DUPLICATE KEY UPDATE `cuentaE`= '$cuentaE',
	`cuentaR`='$cuentaR',
	`monto`='$monto';");
 ?>