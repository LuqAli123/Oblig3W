
function feilMelding(){
    let valid = true;
    $("#feil1").html("");
    $("#feil2").html("");
    $("#feil3").html("");
    $("#feil4").html("");
    $("#feil5").html("");
    $("#feil6").html("");
    $("#feil7").html("");
}

function kjopBillett(){
    feilMelding();
    let valid = true;
    let validTicket = true;
    let film = $("#film").val();
    let antall =$("#antall").val();
    let fornavn = $("#fornavn").val();
    let etternavn = $("#etternavn").val();
    let adresse = $("#adresse").val();

    if (film === "") {
        valid  = false;
        $("#feil1").html("Velg film");
    }else {
        $("#feil1").html("")
    }
    if (antall === "") {
        valid = false
        $("#feil2").html("Velg antall");
    }else {
        $("#feil2").html("")
    }
    if (fornavn === "") {
        valid = false;
        $("#feil3").html("Fornavn feltet er påkrevet");
    }else {

        $("#feil3").html("")
    }
    if (etternavn === "") {
        valid = false
        $("#feil4").html("Etternavn feltet er påkrevet");
    }else {
        $("#feil4").html("")
    }
    if (adresse === "") {
        valid = false;
        $("#feil5").html("Adresse feltet er påkrevet");
    }else {
        $("#feil5").html("")
    }

    // Mobilnummer validation
    const mobilnummer = $("#mobilnummer").val();
    if (mobilnummer === "") {
        valid = false;
        $("#feil6").text("Mobilnummer feltet er påkrevet");
    } else if (valMobilnummer(mobilnummer)) {
        valid = false;
        settFeil("feil6", "Ugyldig mobilnummer, maks 8 siffer.");
    }else {
        $("#feil6").html("");
    }

    // Epost validation
    const epost = $("#epost").val();
    if (epost === "") {
        valid = false;
        $("#feil7").text("E-post feltet er påkrevet.");
    } else if (valEpost(epost)) {
        valid = false;
        settFeil("feil7", "Vennligst skriv inn en gyldig e-post.");
    }else {
        $("#feil7").html("");
    }
    if(valid){
        const billett={
            film : $("#film").val(),
            antall : $("#antall").val(),
            fornavn : $("#fornavn").val(),
            etternavn : $("#etternavn").val(),
            adresse : $("#adresse").val(),
            mobilnummer : $("#mobilnummer").val(),
            epost : $("#epost").val(),
        };
        $.ajax({
            url: '/lagreKunde',
            type: 'POST',
            data: JSON.stringify(billett), // gjør om billett-objektet til en JSON-streng
            contentType: 'application/json', // setter Content-Type til application/json
            success: function() {
                hentAlle();
                // kode som kjører når forespørselen er vellykket
            },
            error: function(error) {
                // kode som kjører når det er en feil
                console.log(error);
            }
        });
        $("#film").val("Velg film");
        $("#antall").val("");
        $("#fornavn").val("");
        $("#etternavn").val("");
        $("#adresse").val("");
        $("#mobilnummer").val("");
        $("#epost").val("");
    }
}
function hentAlle(){
    $.get("/hentAlt", function (data){
        hentData(data);
    });
}

function hentData(format){

    let ut = "<table class='table table-striped'>" +
        "<tr><th>Film</th><th>Antall</th><th>Fornavn</th><th>Etternavn</th><th>Adresse</th>" +
        "<th>Mobilnummer</th><th>Epost</th></tr>";

    for (let billett of format) {
        ut += "<tr><td>" + billett.film + "</td><td>" + billett.antall +
            "</td><td>" + billett.fornavn + "</td><td>"
            + billett.etternavn + "</td><td>" + billett.adresse + "</td><td>" + billett.mobilnummer +
            "</td><td>" + billett.epost + "</td>"
        ut += "</tr>";
    }
    ut += "</table>";
    $("#alt").html(ut);
}

function slettBillett(){
    $.get("/slettBillett", function (){
        hentAlle();
    });
    feilMelding();
}

function settFeil(elementId, melding){
    document.getElementById(elementId).innerText = melding;
}

//Mobilnummer validering
function valMobilnummer(mobilnummer){
    let regx =/^[0-9]{8}$/;
    return !regx.test(mobilnummer)
}

//E-post validering
function valEpost(epost){
    let regx = /^[a-z A-Z0-9.-]+@[a-z]+\.[a-zA-Z]{2,}$/;
    return !regx.test(epost);
}
