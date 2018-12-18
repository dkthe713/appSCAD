<?php
	include('functions.php');
	$ced=$_GET["cedula"];

	if($resultset=getSQLResultSet("SELECT id, nombre, cedula FROM `transaccion` WHERE cedula='$ced'")){
		while ($row = $resultset->fetch_array(MYSQLI_NUM)){
			echo json_encode($row);
		}
	}
?>