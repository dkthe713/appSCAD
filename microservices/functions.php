<?php 
  header( 'Content-Type: text/html;charset=utf-8' );

$hostname = "localhost";
$database = "id7651094_biometricouce";
$username = "id7651094_biometricouce";
$password = "biometricouce";

  function ejecutarSQLCommand($commando){
   
    $mysqli = new mysqli($hostname, $username, $password, $database);

    /* check connection */
    if ($mysqli->connect_errno) {
      printf("Connect failed: %s\n", $mysqli->connect_error);
      exit();
    }

    if ( $mysqli->multi_query($commando)) {
      if ($resultset = $mysqli->store_result()) {
        while ($row = $resultset->fetch_array(MYSQLI_BOTH)) {
        		
        }
        $resultset->free();
      }   
    }

    $mysqli->close();
  }

  function getSQLResultSet($commando){
   
    $mysqli = new mysqli($hostname, $username, $password, $database);
    /* check connection */
    if ($mysqli->connect_errno) {
      printf("Connect failed: %s\n", $mysqli->connect_error);
      exit();
    }

    if ( $mysqli->multi_query($commando)) {
    	return $mysqli->store_result();    
       
    }

    $mysqli->close();
  }
?>