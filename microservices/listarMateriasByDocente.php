<?php
	include('functions.php');
	$user=$_GET["user"];
	$json=array();

	if($resultset=getSQLResultSet("SELECT DISTINCT m.mtr_id,m.mtr_nombre from horario h join materia m on h.mtr_id=m.mtr_id where h.fcdc_id='$user'")){
		while($reg=mysqli_fetch_array($resultset)){
				$json['datos'][]=$reg;
		}	
		echo json_encode($json);
	}else{
		$json['datos'][]=[];
		echo json_encode($json);
	}
?>