<?php
	include('functions.php');
	$user=$_GET["user"];
	$pwd=$_GET["password"];
	$json=array();

	if($resultset=getSQLResultSet("SELECT user,password,nombre,apellido,cedula FROM `usuario` WHERE user='$user' AND password='$pwd'")){
		if($reg=mysqli_fetch_array($resultset)){
				$json['datos'][]=$reg;
		}	
		echo json_encode($json);
	}else{
		$results["user"]='';
		$results["password"]='';
		$results["nombre"]='';
		$results["apellido"]='';
		$results["cedula"]='';
		$json['datos'][]=$results;
		echo json_encode($json);
	}
?>