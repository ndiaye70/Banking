function envoyerDonnees() {
    // Construire l'objet DTO avec les quantités saisies
    var caisseDto = {
        "quantitesParTypeBillet": {
            "BILLET_CINQ": parseInt(document.getElementById("quantiteBilletCinq").value),
            "BILLET_DIX": parseInt(document.getElementById("quantiteBilletDix").value),
            "BILLET_VINGT": parseInt(document.getElementById("quantiteBilletVingt").value),
            "BILLET_CINQUANTE": parseInt(document.getElementById("quantiteBilletCinquante").value),
            "BILLET_CENT": parseInt(document.getElementById("quantiteBilletCent").value),
            "BILLET_DEUX_CENTS": parseInt(document.getElementById("quantiteBilletDeuxCents").value),
            "BILLET_CINQ_CENTS": parseInt(document.getElementById("quantiteBilletCinqCents").value),
        }
    };

    // Envoi de l'objet JSON au backend via une requête fetch
    fetch('/api/caisse', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(caisseDto),
    })
    .then(response => response.json())
    .then(data => {
        console.log('Réponse du serveur:', data);
        if (data && data.quantitesParTypeBillet) {
            afficherResume(data.quantitesParTypeBillet);
        } else {
            console.error('Réponse du serveur malformée ou données manquantes.');
        }
    })

function afficherResume(data) {
    // Masquer le formulaire
    document.getElementById("caisseForm").style.display = "none";

    // Afficher la section de résumé avec les données du serveur
    document.getElementById("resumeSection").style.display = "block";

    // Mettre à jour les éléments du résumé avec les données du serveur
    document.getElementById("resumeBilletCinq").textContent = "Quantité Billet 5 euros: " + data.quantitesParTypeBillet.BILLET_CINQ;
    document.getElementById("resumeBilletDix").textContent = "Quantité Billet 10 euros: " + data.quantitesParTypeBillet.BILLET_DIX;
    // Ajoutez d'autres lignes de résumé en fonction de vos champs

    document.getElementById("montantTotal").textContent = "Montant Total: " + data.montantTotal + " euros";
}

// Ajoutez ici d'autres fonctions au besoin
