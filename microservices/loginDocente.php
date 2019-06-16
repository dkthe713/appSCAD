<?php
	include('functions.php');
	$user=$_GET["user"];
	$pwd=$_GET["password"];
	$json=array();

	if($resultset=getSQLResultSet("SELECT u.usr_nick as user,u.usr_password as password,
 fd.fcdc_primer_nombre as primerNombre,fd.fcdc_segundo_nombre as segundoNombre,
 fd.fcdc_apellidos as apellidos,u.usr_identificacion as cedula from usuario u
JOIN ficha_docente fd on u.fcdc_id=fd.fcdc_id WHERE u.usr_nick='$user' AND u.usr_password='$pwd'")){
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
