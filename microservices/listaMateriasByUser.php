<?php
	include('functions.php');
	$user=$_GET["user"];
	$json=array();

	if($resultset=getSQLResultSet("SELECT DISTINCT m.id_materia,m.nombre_materia from `materia` m join docente_estudiante_materia dem on dem.id_materia=m.id_materia join docente d on d.id_docente=dem.id_docente join usuario u on u.id_docente=d.id_docente where u.user='$user'")){
		if($reg=mysqli_fetch_array($resultset)){
				$json['datos'][]=$reg;
		}	
		echo json_encode($json);
	}else{
		$json['datos'][]=$resultset;
		echo json_encode($json);
	}
?>