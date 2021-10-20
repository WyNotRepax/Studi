<!DOCTYPE html>
<html>
<head>
    <?php
    $file = fopen("files/concap.csv", "r");
    $headline = fgetcsv($file);
    ?>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<div class="container">

    <form action="<?php echo $_GET["action"] ?>.php" method="get">
        <?php
        foreach ($headline as $item) {
            echo "<div class='mb-3'>";
            echo "<label for='" . $item . "' class='form-label'>" . $item . "</label>";
            echo "<input class='form-control' type='text' id='" . $item . "' name='" . $item . "' value='" . $_GET[$item] . "'/>";
            echo "</div>";
        }
        ?>
        <input type="hidden" name="index" value="<?php echo $_GET["index"] ?>">
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>

</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4"
        crossorigin="anonymous"></script>
</html>