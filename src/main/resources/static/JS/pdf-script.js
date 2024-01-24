// pdf-script.js

$(document).ready(function () {
    $('#imprimerPdf').click(function () {
    console.log('Clic sur le bouton Imprimer PDF');
        // Récupérez le contenu HTML de la page
        var content = $('#pdfContent').html();

        // Convertissez le HTML en PDF
        html2pdf(content, {
            margin: 10,
            filename: 'details_caisse.pdf',
            image: { type: 'jpeg', quality: 0.98 },
            html2canvas: { scale: 2 },
            jsPDF: { unit: 'mm', format: 'a4', orientation: 'portrait' }
        });
    });
});
