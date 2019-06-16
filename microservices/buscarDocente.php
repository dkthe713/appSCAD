<?php
	include('functions.php');
	$docente=$_GET["docente"];
	$json=array();

	if($resultset=getSQLResultSet("SELECT DISTINCT fcdc.* from ficha_docente fd where fd.fcdc_primer_nombre like '%'$docente'%' or fd.fcdc_segundo_nombre like '%'$docente'%' or fd.fcdc_apellidos like '%'$docente'%' or fd.fcdc_identificacion like '%'$docente'%' order by fd.fcdc_apellidos")){
		while($reg=mysqli_fetch_array($resultset)){
				$json['datos']=$reg;
		}	
		echo json_encode($json);
	}else{
		$json['datos']=[];
		echo json_encode($json);
	}
?>