<?php
	include('functions.php');
	$ced=$_GET["cedula"];

	if($resultset=getSQLResultSet("SELECT cedula, cuenta, balance FROM `transaccion` WHERE cedula='$ced'")){
		while ($row = $resultset->fetch_array(MYSQLI_NUM)){
			echo json_encode($row);
		}
	}
?>