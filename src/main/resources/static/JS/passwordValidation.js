// Fonction pour vérifier si les mots de passe correspondent
function checkPasswordMatch() {
    var password = document.getElementById("password").value;
    var confirmPassword = document.getElementById("confirmPassword").value;

    // Vérifie si les deux mots de passe sont identiques
    if (password != confirmPassword) {
        // Affiche un message d'erreur
        document.getElementById("passwordError").innerHTML = "Passwords do not match";
        return false;
    } else {
        // Efface le message d'erreur s'il existe
        document.getElementById("passwordError").innerHTML = "";
        return true;
    }
}
