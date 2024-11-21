    <?php
    include_once($_SERVER['DOCUMENT_ROOT'] . '/InterdisciplinarPW/config/globals.php');
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <script src="<?= $BASE_URL?>javaScript/header.js" defer></script>

    <?php 
    if (isset($pageJS) && is_array($pageJS)) {
        foreach ($pageJS as $jsFile) {
            echo '<script src="' . htmlspecialchars($BASE_URL . 'javaScript/' . $jsFile) . '" defer></script>';
        }
    }
    ?>
    <link rel="stylesheet" href="<?= $BASE_URL?>css/header3.css">

    <?php 
    if (isset($pageCSS) && is_array($pageCSS)) {
        foreach ($pageCSS as $cssFile) {
            echo '<link rel="stylesheet" href="' . htmlspecialchars($BASE_URL . 'css/' . $cssFile) . '">';
        }
    }
    ?>
    <title>CineEstrela</title>


</head>

 <body>
    <div class="logo">
<a href="<?= $BASE_URL ?>index.php"><img src="<?= $BASE_URL ?>imagens/Logo_Cinestrela 1.png" alt="Item 1"></a>
    <!-- Ícone de Menu Hamburguer -->
     
    <div class="hamburger-menu" onclick="toggleMenu()">
        <span></span>
        <span></span>
        <span></span>
    </div>
    </div>
    <!-- Menu Lateral -->
    <nav class="sidebar" id="sidebar">
        <ul>
            <li><a href="#"><img src="<?= $BASE_URL?>imagens/Sessoes.png " alt="Item 1"></a></li>
            <li><a href="<?= $BASE_URL ?>catalogo.php"><img src="<?= $BASE_URL ?>imagens/Filmes.png" alt="Item 2"></a></li>
            <li><a href="#"><img src="<?= $BASE_URL?>imagens/estrela.png" alt="Item 3"></a></li>
            <li><a href="#"><img src="<?= $BASE_URL?>imagens/perfil.png"  alt="Item 4"></a></li>
        </ul>
    </nav>
