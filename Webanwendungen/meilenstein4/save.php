<?php
$csv = "files/concap.csv";

if (sizeof($_GET) > 0) {
    $file = fopen($csv, "a");
    fputcsv($file, $_GET);
    fclose($file);
}
?>
<!DOCTYPE html>
<html>
<head>
   <meta http-equiv="refresh" content="1; url=./index.php">
</head>
</html>
