<?php

include_once "fpdf/fpdf.php";

$csv = "files/concap.csv";

$file = fopen($csv, "r");
$headline = fgetcsv($file);
$data = [];
while (!feof($file)) {
    array_push($data, fgetcsv($file));
}
fclose($file)

?>
<!DOCTYPE html>
<html>
<head>
    <title>Beispiel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <a href="form.php?action=save" class="btn btn-success">Add</a>
    <table class="table">
        <tr>
            <th></th>
            <?php
            foreach ($headline as $item) {
                echo "<th>" . $item . "</th>";
            }
            ?>
            <th></th>
            <th></th>
        </tr>
        <?php
        for ($i = 0; $i < count($data); $i++) {
            $row = $data[$i];
            if ($row != null) {
                echo "<tr>";
                echo "<form action='form.php'>";
                echo "<td>" . $i . "</td>";

                foreach ($row as $item) {
                    echo "<td>" . $item . "</td>";
                }
                echo "<td><a href='delete.php?index=" . $i . "' class='btn btn-danger'> Löschen </a></td>";
                echo "<input type='hidden' value='edit' name='action'/>";
                echo "<input type='hidden' value='".$i."' name='index'/>";
                for ($i2 = 0; $i2 < count($headline); $i2++) {
                    echo "<input type='hidden' value='" . $row[$i2] . "' name='" . $headline[$i2] . "'/>";
                }
                echo "<td><input type='submit' value='Bearbeiten' class='btn btn-warning'></td>";
                echo "</form>";
                echo "</tr>";
            }
        }
        ?>
    </table>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</html>