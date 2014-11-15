var REST_URL = "http://localhost:8080/app/users";
var currentUser;

findAll();

// Register listeners
$('#btnSearch').click(function () {
    search($('#searchKey').val());
    return false;
});

$('#btnAdd').click(function () {
    newUser();
    return false;
});

$('#userList').on('click', 'a', function () {
    findById($(this).data('identity'));
});

$('#btnRem').click(function () {
    removeUser();
    return false;
});

$('#btnSave').click(function () {
    if ($('#userId').val() == '')
        addUser();
    else
        updateUser();
    return false;
});

function addUser() {
    console.log('addUser');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: REST_URL,
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('User created successfully');
            $('#userId').val(data.userId);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addUser error: ' + textStatus);
        }
    });
}

function removeUser() {
    console.log('remUser');
    $.ajax({
        type: 'DELETE',
        contentType: 'application/json',
        url: REST_URL + '/' + $('#userId').val(),
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('User removed successfully');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('addUser error: ' + textStatus);
        }
    });
}

function updateUser() {
    console.log('updateUser');
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: REST_URL,
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('User updated successfully');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateUser error: ' + textStatus);
        }
    });
}

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: REST_URL,
        dataType: "json", // data type of response
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function findById(id) {
    console.log('findById: ' + id);
    $.ajax({
        type: 'GET',
        url: REST_URL + '/' + id,
        contentType: 'application/json',
        dataType: "json",
        success: function (data) {
            console.log('findById success: ' + data.userName);
            currentUser = data;
            renderDetails(currentUser);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findById: ' + textStatus);
        }
    });
}

function renderList(data) {
// JAX-RS serializes an empty list as null, and a 'collection of one' as an object (not an 'array of one')
    var list = data == null ? [] : (data instanceof Array ? data : [data]);
    $('#userList li').remove();
    $.each(list, function (index, user) {
        $('#userList').append('<li><a href="#" data-identity="' + user.userId + '">' + user.login + "  "+ user.userName + '</a></li>');
    });
}

function newUser() {
    currentUser = {};
    renderDetails(currentUser); // Display empty form
}

function renderDetails(user) {
    $('#userId').val(user.userId);
    $('#login').val(user.login);
    $('#userName').val(user.userName);
}

function search(searchKey) {
    if (searchKey == '') {
        findAll();
    } else {
        findByLogin(searchKey);
    }
}

function findByLogin(login) {
    console.log('findBylogin: ' + login);
    $.ajax({
        type: 'GET',
        url: REST_URL + '/login/' + login,
        dataType: "json",
        success: function (data) {
            console.log('findByLogin success: ' + data.userName);
            currentUser = data;
            renderDetails(currentUser);
        },
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findByLogin: ' + textStatus);
        }
    });
}

// Helper function to serialize all the form fields into a JSON string
function formToJSON() {
    var userId = $('#userId').val();
    return JSON.stringify({
        "userId": userId == "" ? null : userId,
        "login": $('#login').val(),
        "userName": $('#userName').val()
    });
}