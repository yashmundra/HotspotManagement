<?php
$hostname_localhost ="localhost";
$database_localhost ="vision";
$username_localhost ="root";
$password_localhost ="root";
$localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost)
or
trigger_error(mysql_error(),E_USER_ERROR);


mysql_select_db($database_localhost, $localhost);

        $query1="SELECT  * FROM emp";

		$result1 = mysql_query($query1);
		$json["data"]= array();
		
		if(mysql_num_rows($result1))
		{
			$json["data"]= array();
		    while($row=mysql_fetch_assoc($result1))
			{
		       $product = array();
				$product["username"] = $row["Ename"];
				$product["password"] = $row["Epass"];
				
                array_push($json["data"], $product);
			}
		}
		
		echo json_encode($json);
?>
