<?php
$csv = "files/concap.csv";

if (sizeof($_GET) > 0) {
    $index = $_GET["index"];
    unset($_GET["index"]);
    $file = fopen($csv, "r");
    $headline = fgetcsv($file);
    $data = [];
    while (!feof($file)) {
        array_push($data, fgetcsv($file));
    }
    fclose($file);

    $file = fopen($csv, "w");
    fputcsv($file, $headline);
    for($i = 0; $i < sizeof($data); $i++){
        if($i == $index){
            fputcsv($file, $_GET);
        }else if($data[$i] != null){

            fputcsv($file, $data[$i]);
        }
    }
    fclose($file);
}
?>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="refresh" content="1; url=./index.php">
</head>
</html>
