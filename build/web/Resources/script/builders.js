function appendCheckbox(block, data) {
    let mainContainer;
    let checkBox;
    if (block == 1) {
        mainContainer = document.getElementById("genres");
        checkBox = "genre";
    } else {
        mainContainer = document.getElementById("authors");
        checkBox = "author";
    }

    for (var i in data) {
//            console.log(data[i][0].id);
//            console.log(data[i][1].name);
//            console.log(data[i][2].name);
        let size = data[i].length;
        for (let j = 0; j < size; j++) {

            var container = document.createElement('div');
            container.setAttribute('class', 'form-check col-2');
            var chk = document.createElement('input'); // CREATE CHECK BOX.
            chk.setAttribute('type', 'checkbox'); // SPECIFY THE TYPE OF ELEMENT.
            chk.setAttribute('id', checkBox + data[i][j].id); // SET UNIQUE ID.
            chk.setAttribute('name', checkBox + data[i][j].id);
            chk.setAttribute('class', "form-check-input");
            var lbl = document.createElement('label'); // CREATE LABEL.
            lbl.setAttribute('for', checkBox + j);
            lbl.setAttribute('class', "form-check-label");
            // CREATE A TEXT NODE AND APPEND IT TO THE LABEL.
            lbl.appendChild(document.createTextNode(data[i][j].name));
            // APPEND THE NEWLY CREATED CHECKBOX AND LABEL TO THE <p> ELEMENT.
            container.appendChild(chk);
            container.appendChild(lbl);
//                var div = document.createElement("div");
//                div.innerHTML =  ;

            mainContainer.appendChild(container);
        }

    }

    return;
}
function generateQRCode(hash) {
    new QRious({
        element: document.getElementById('qr-code'),
        size: 200,
        value: hash
    });
    return;
}

function renderCheck(data) {
    if (data === true) {
        return "<i class='fas fa-check-circle' id='green'></i>";
    } else {
        return "<i class='fa-solid fa-circle-xmark' id='red'></i>";
    }
}

function formFillBook(data) {

    $('#book-id').val(data['id']);
    $('#name').val(data['name']);
    $('#isbn').val(data['isbn']);
    $('#hash').val(data['hash']);

    $('#qty').val(data['qty']);
    for (let i = 0; i < Object.keys(data.genre).length; i++) {
        console.log(Object.keys(data.genre)[i]);
        document.getElementById(Object.keys(data.genre)[i]).checked = true;
    }

    for (let i = 0; i < Object.keys(data.author).length; i++) {
        console.log(Object.keys(data.author).length);
        console.log(Object.keys(data.author)[i]);
        document.getElementById(Object.keys(data.author)[i]).checked = true;
    }
    document.getElementById('ava').checked = data['available'];
    document.getElementById('req').checked = data['return'];
    $("#selectPublisher option[value=" + data['publisher'] + "]").attr('selected', 'selected');

    return;
}

function appendSelect(data, id) {
    for (var i in data) {
        console.log(data[i][0].id);
        console.log(data[i][1].name);
        console.log(data[i][2].name);
        let size = data[i].length;
        for (let j = 0; j < size; j++) {


            let optionText = data[i][j].name;
            let optionValue = data[i][j].id;

            $(id).append('<option value=\"' + optionValue + '\">' + optionText + '</option>');
        }

    }
    return;
}

function isValidPassword(pswd,cpswd) {
    let msg = "";
    let send = false;
    if (pswd.length < 8) {
        msg = "Password too short! (8 to 12 characters)";
    } else if (pswd.length > 12) {
        msg = "Password too long! (8 to 12 characters) "
    } else if (pswd != cpswd) {
        msg = "Password and Confirm password do not match!";
    } else {
        send = true;
    }
    if (!send) {
        $('.custom-message').show();
        $('.custom-message').html(msg);
        return false;
    } else {
        $('.custom-message').hide();
        $('.custom-message').html("");
        return true;
    }
}

function isValidEmail(input) {

    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;

    if (input.match(validRegex)) {
        $('.custom-message').hide();
        $('.custom-message').html("");
        return true;
    } else {
        $('.custom-message').show();
        $('.custom-message').html("Invalid Email!");
        return false;
    }

}


function isValidContact(input) {
    var validRegex = /^[0-9]+$/;
    input = input.toString();
    if (input.length !== 10 && input.match(validRegex)) {
        $('.custom-message').show();
        $('.custom-message').html("Invalid Contact (10 digits)!");
        return false;
    } else {
        $('.custom-message').hide();
        $('.custom-message').html("");
        return true;
    }

}