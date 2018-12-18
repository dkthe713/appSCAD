<?php
	include('functions.php');

	if($resultset=getSQLResultSet("SELECT * FROM `estudiante`")){
		while ($row = $resultset->fetch_array(MYSQLI_NUM)){
			echo json_encode($row);
		}
	}
?>