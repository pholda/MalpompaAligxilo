<?php

//error_reporting(E_ALL);
//ini_set('display_errors', 1);

echo "AligxiloTest<br>";

//print_r($_POST);

//print_r(json_encode($_POST));
if (!array_key_exists('send', $_POST)) {
    echo "Eraro";
    exit;
}
if (!file_put_contents("data", json_encode($_POST). "\n", FILE_APPEND)) {
    echo "Eraro #2";
    exit;
}
?>
<html>
<head>
    <meta charset="utf-8" />
</head>
<body>
<h1>Dankon por via aliĝo al JES en Eger, Hungario!</h1>
<h2>Viaj datumoj - bv. kontroli ĉu ĉio enordas</h2>
<h3>Personaj datumoj</h3>
<strong>Persona nomo:</strong> <?= $_POST["personaNomo"] ?><br />
<strong>Familia nomo:</strong> <?= $_POST["familiaNomo"] ?><br />
<strong>Kromnomo:</strong> <?= $_POST["kromnomo"] ?><br />
<strong>Naskiĝdato:</strong> <?= $_POST["naskigxdato"] ?><br />
<strong>Genro:</strong> <?= $_POST["genro"] ?><br />
<strong>Retpoŝtadreso:</strong> <?= $_POST["retposxtadreso"] ?><br />
<strong>Tujmesaĝilo:</strong> <?= $_POST["tujmesagxilo"] ?><br />
<strong>Telefonnumero:</strong> <?= $_POST["telefonnumero"] ?><br />
<strong>Lando:</strong> <?= $_POST["lando"] ?><br />
<strong>Invitilo:</strong> <?= $_POST["invitilo"] ?><br />
<strong>Pasportnumero:</strong> <?= $_POST["pasportnumero"] ?><br />
<strong>Valideco de pasporto:</strong> <?= $_POST["pasportovalideco"] ?><br />
<strong>Adreso por invitilo:</strong> <?= $_POST["invitilo-adreso"] ?><br />
<strong>Adresaro:</strong> <?= implode(", ", $_POST["adresaro"]) ?><br />
<h3>Loĝado</h3>
<strong>Loĝado:</strong> <?= implode(", ", $_POST["logxado"]) ?><br />
<strong>Ĉu via ĉambro rajtas esti gea?</strong> <?= implode(", ", $_POST["logxado-gea"]) ?><br />
<strong>Mi preferas loĝi … ĉambro</strong> <?= implode(", ", $_POST["logxado-prefero"]) ?><br />
<strong>Ĉu via ĉambro rajtas esti gea?</strong> <?= implode(", ", $_POST["logxado-gea"]) ?><br />
<strong>Mi preferus loĝi kun…?</strong> <?= implode(", ", $_POST["logxado-kun"]) ?><br />
<strong>Ĉeesto:</strong> <?php
    if ($_POST['cxeesto'] == "cxiun") {
        echo "ĉiun tagon";
    } else {
        echo "elektitaj tagoj: ".implode(", ", $_POST["cxeesto"]);
    }
?><br />
<h3>Manĝado</h3>
<strong>Mi mendas:</strong> <?php
if ($_POST['mangxado'] == "cxio") {
    echo "ĉiun manĝon";
} elseif ($_POST['mangxado'] == "nenio") {
    echo "neniun manĝon";
} else {
    echo "unuopajn manĝojn: ".implode(", ", implode(", ", $_POST["mangxado-elekto[]"]));
}
?><br />
<?php if ($_POST['mangxado'] != "nenio") {
    ?>
<strong>Mi preferas matenmanĝi</strong> <?= $_POST["matenmangx-prefero"] ?><br />
<strong>Mi volas manĝi…</strong> <?= $_POST["mangxtipo"] ?><?=
    $_POST["mangxtipo"] == "viande" ? ", ".$_POST['mangxtipo2'] : ""
    ?><?= $_POST['mangxtipo'] == "speciala" ? ", ".$_POST['mangxtipo-klarigo'] : ""?>
<?php
}
?>
<h3>Aranĝo</h3>
<strong>Programkontribuo:</strong> <?= $_POST["programkontribuo"] ?><br />
<strong>Helpopropono:</strong> <?= $_POST["helpopropono"] ?><br />
<h3>Pagado</h3>
<strong>Pagado:</strong> <?= $_POST["pagado"] ?><br />
<strong>Mi ŝatus donaci al JES 2015…:</strong> <?= $_POST["donaco-kvoto"] ?><br />
<strong>Mi ŝatus donaci por:</strong> <?= $_POST["donaco-kialo"] ?><?=
    $_POST["donaco-kialo"] == "alia" ? " - ".$_POST["donaco-kialo-klarigo"] : ""
?><br />
<strong>Kalkulita kotizo: </strong> ankoraŭ ne funkcias :(
<strong>Mi pagos ĝis:</strong> <?= $_POST["mi-pagos-gxis"] ?><br />
<strong>Mi volas pagi:</strong> <?= $_POST["mi-volas-pagi"] ?><br />
<h3>Etoso</h3>
<strong>Ĉu vi kantas, aŭ ludas iun muzikilon, kiun vi volonte kunportus?:</strong> <?= $_POST["ludi-muzikilon"] ?><br />
<strong>Mi volas pagi:</strong> <?= $_POST["mi-volas-pagi"] ?><br />
<strong>Mi volas pagi:</strong> <?= $_POST["mi-volas-pagi"] ?><br />
</body>
</html>