<?php
$days = [
    31,
    28,
    31,
    30,
    31,
    30,
    31,
    31,
    30,
    31,
    30,
    31
];

$curr_day = date("j");
$curr_month = date("n");
$curr_year = date("Y");

if (isset($_GET["year"])) {
    $year = $_GET["year"];
} else {
    $year = $curr_year;
}
if (isset($_GET["month"])) {
    $month = $_GET["month"];
    while ($month <= 0) {
        $month += 12;
        $year -= 1;
    }
    while ($month > 12) {
        $month -= 12;
        $year += 1;
    }
} else {
    $month = $curr_month;
}
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
    <table class="table">
        <thead>
        <tr>
            <th/>
            <th>
                <a class="btn btn-dark" href="calendar.php?month=<?php echo $month ?>&year=<?php echo $year - 1 ?>">Vorheriges
                    Jahr</a>
            </th>
            <th>
                <a class="btn btn-dark" href="calendar.php?month=<?php echo $month - 1 ?>&year=<?php echo $year ?>">Vorheriger
                    Monat</a>
            </th>
            <th>
                <a class="btn btn-dark" href="calendar.php">Aktueller Monat</a>
            </th>
            <th>
                <a class="btn btn-dark" href="calendar.php?month=<?php echo $month + 1 ?>&year=<?php echo $year ?>">Nächster
                    Monat</a>
            </th>
            <th>
                <a class="btn btn-dark" href="calendar.php?month=<?php echo $month ?>&year=<?php echo $year + 1 ?>">Nächstes
                    Jahr</a>
            </th>
            </th>
        </tr>
        </thead>
    </table>
    <table class="w-100 table table-bordered">
        <thead>
        <tr>
            <th>Montag</th>
            <th>Dienstag</th>
            <th>Mittwoch</th>
            <th>Donnerstag</th>
            <th>Freitag</th>
            <th>Samstag</th>
            <th>Sonntag</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <?php
            $offset = date("N", mktime(null, null, null, $month, 1, $year)) - 1;
            $day = 1;
            $i = 1;
            $has_offset = false;
            while ($day <= $days[$month - 1]) {
                echo "<td";
                if($day == $curr_day && $month == $curr_month && $year == $curr_year){
                    echo " class='bg-primary text-white'";
                }
                echo ">";
                if ($i > $offset || $has_offset) {
                    $has_offset = true;
                    echo $day;
                    $day++;
                }
                echo "</td>";
                $i++;
                if ($i > 7) {
                    echo "</tr><tr>";
                    $i = 1;
                }
            }
            for (; $i <= 7; $i++) {
                echo "<td></td>";
            }
            ?>
        </tr>
        </tbody>
    </table>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</html>