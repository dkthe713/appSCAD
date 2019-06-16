<?php
	include('functions.php');
	$user=$_GET["docente"];
	$pwd=$_GET["materia"];
	$json=array();

	if($resultset=getSQLResultSet("SELECT DISTINCT fe.* from ficha_estudiante fe join horario_ficha_estudiante hfe on hfe.fces_id=fe.fces_id join horario h on h.hrr_id=hfe.hrr_id join ficha_docente fd on fd.fcdc_id=h.fcdc_id join materia m on h.mtr_id=m.mtr_id where fd.fcdc_id='$docente' and m.mtr_id='$materia' order by fe.fces_apellidos")){
		while($reg=mysqli_fetch_array($resultset)){
				$json['datos'][]=$reg;
		}	
		echo json_encode($json);
	}else{
		$json['datos'][]=[];
		echo json_encode($json);
	}
?>