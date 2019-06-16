<?php
	include('functions.php');
	$materia=$_GET["materia"];
	$json=array();

	if($resultset=getSQLResultSet("select distinct uc.*,cont.* from syllabo s join malla_curricular_materia mcm on s.mlcrmt_id=mcm.mlcrmt_id join unidad_curricular uc on uc.syl_id=s.syl_id join contenido cont on cont.uncr_id=uc.uncr_id where mcm.mtr_id='$materia'")){
		while($reg=mysqli_fetch_array($resultset)){
				$json['datos'][]=$reg;
		}	
		echo json_encode($json);
	}else{
		$json['datos'][]=[];
		echo json_encode($json);
	}
?> 